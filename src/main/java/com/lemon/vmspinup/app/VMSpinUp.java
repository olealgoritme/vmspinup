package com.lemon.vmspinup.app;

import com.lemon.vmspinup.error.VMSpinUpException;
import com.lemon.vmspinup.model.commands.*;
import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.model.hypervisor.HyperVisorType;
import com.lemon.vmspinup.model.hypervisor.KVM;
import com.lemon.vmspinup.model.hypervisor.LXC;
import com.lemon.vmspinup.model.listeners.VMStateListener;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.*;
import org.libvirt.event.DomainEvent;
import org.libvirt.event.DomainEventType;
import org.libvirt.event.LifecycleListener;

import java.util.ArrayList;
import java.util.UUID;

import static org.libvirt.Library.initEventLoop;
import static org.libvirt.Library.runEventLoop;

public class VMSpinUp implements VMCreate, VMDestroy,
                                 VMList, VMResume,
                                 VMShutdown, VMStart,
                                 VMSuspend, LookupCommands {

    private static VMSpinUp VMSpinUp;
    public static HyperVisor DEFAULT_HYPERVISOR = KVM.getInstance();
    private VirtualMachine vm;

    // LV
    public static Connect lvConn;
    private static ConnectAuth lvCa;
    private Domain domain;
    protected Thread eventThread;

    private VMSpinUp() throws VMSpinUpException {
        lvCa = new ConnectAuthDefault();
        try {
            lvConn = new Connect(DEFAULT_HYPERVISOR.getUriString(), lvCa, 0);
            eventThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   initEventLoop();
                } catch (LibvirtException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("starting event loop");
                }
            }
        });

            eventThread.start();

        } catch (LibvirtException e) {
            e.printStackTrace();
            throw new VMSpinUpException("Can't connect to HyperVisor" + DEFAULT_HYPERVISOR.getName());
        }

    }

    @Override
    public VirtualMachine vmLookupByName(String vmName) {
        try {
            getInstance();
            domain = lvConn.domainLookupByName(vmName);
            createVirtualMachine(domain);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
            return vm;
    }

    @Override
    public VirtualMachine vmLookupByID(int id) {
        try {
            getInstance();
            domain = lvConn.domainLookupByID(id);
            createVirtualMachine(domain);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
            return vm;
    }

    private VirtualMachine createVirtualMachine(Domain domain) throws LibvirtException {
        vm = new VirtualMachine();
        vm.setVMState(domain.getEventStatus());
        vm.setHyperVisor(DEFAULT_HYPERVISOR);
        vm.setUUID(UUID.fromString(domain.getUUIDString()));
        vm.setID(domain.getID());
        vm.setName(domain.getName());
        vm.setvCPU(domain.getMaxVcpus());
        vm.setRamAmount(domain.getMaxMemory());
        return vm;
    }

    @Override
    public VirtualMachine vmLookupByUUID(UUID uuid) {
        try {
            getInstance();
            domain = lvConn.domainLookupByUUID(uuid);
            vm = createVirtualMachine(domain);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
            return vm;
    }

    @Override
    public void vmDestroy(VirtualMachine vm) {
        try {
            getInstance();
            domain = lvConn.domainLookupByUUID(vm.getUUID());
            domain.destroy();
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void vmCreate(VirtualMachine vm) {
        try {
            getInstance();
            String xml = vm.toXML();
            domain = lvConn.domainCreateXML(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
        addListener(vm,domain);
    }


    private void addListener(final VirtualMachine virtualMachine, final Domain domain) {

            VMStateListener listener = virtualMachine.getVMStateListener();
            LifecycleListener lifecycleListener = new LifecycleListener() {
                                        @Override
                                            public void onLifecycleChange(Domain d, DomainEvent event) {

                                                    System.out.println("DOMAINEVENT: " + event);
                                                if (event.getType() == DomainEventType.DEFINED) {
                                                    virtualMachine.setVMState(DomainInfo.DomainState.VIR_DOMAIN_NOSTATE);
                                                    listener.onCreated(virtualMachine);

                                            } else if (event.getType() == DomainEventType.STARTED) {
                                                virtualMachine.setVMState(DomainInfo.DomainState.VIR_DOMAIN_RUNNING);
                                                listener.onStarted(virtualMachine);

                                            } else if (event.getType() == DomainEventType.SUSPENDED) {
                                                virtualMachine.setVMState(DomainInfo.DomainState.VIR_DOMAIN_PAUSED);
                                                listener.onSuspended(virtualMachine);

                                                } else if (event.getType() == DomainEventType.RESUMED) {
                                                    virtualMachine.setVMState(DomainInfo.DomainState.VIR_DOMAIN_RUNNING);
                                                    listener.onResumed(virtualMachine);

                                                } else if (event.getType() == DomainEventType.SHUTDOWN) {
                                                    virtualMachine.setVMState(DomainInfo.DomainState.VIR_DOMAIN_SHUTDOWN);
                                                    listener.onShutdown(virtualMachine);

                                            } else if (event.getType() == DomainEventType.CRASHED) {
                                                virtualMachine.setVMState(DomainInfo.DomainState.VIR_DOMAIN_NOSTATE);
                                                listener.onCrashed(virtualMachine);
                                            }
                                        }
                                        };

        try {
            domain.addLifecycleListener(lifecycleListener);
            virtualMachine.setVMStateListener(listener);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void vmStart(VirtualMachine vm) {
        try {
            getInstance();
            String xml = vm.toXML();
            lvConn.domainCreateLinux(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void vmShutdown(VirtualMachine vm) {
        try {
            getInstance();
            domain = lvConn.domainLookupByUUID(vm.getUUID());
            domain.shutdown();
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void vmSuspend(VirtualMachine vm) {
        try {
            getInstance();
            domain = lvConn.domainLookupByUUID(vm.getUUID());
            domain.suspend();
          } catch (LibvirtException e) {
             e.printStackTrace();
        }
    }

    @Override
    public void vmResume(VirtualMachine vm) {
        try {
            getInstance();
            domain = lvConn.domainLookupByUUID(vm.getUUID());
            domain.resume();
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<VirtualMachine> vmList() {
        int[] ids;
        ArrayList<VirtualMachine> vmList = new ArrayList<VirtualMachine>();
        try {
            getInstance();
            ids = lvConn.listDomains();
            for (int i : ids) {
                this.vm = this.vmLookupByID((i));
                vmList.add(vm);
            }

        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
        return vmList;
    }

    public static HyperVisor getHyperVisor() {
        return DEFAULT_HYPERVISOR;
    }

    private static void reset() {
        // reset
        try {
            lvConn.close();
        } catch (LibvirtException e) {
            e.printStackTrace();
            lvConn = null;
            lvCa = null;
            VMSpinUp = null;
        }

    }
    public static void disconnect() {
        try {
            if(lvConn != null && lvCa != null && lvConn.isConnected()) {
                reset();
            }
        } catch (LibvirtException e) {
            e.printStackTrace();
            reset();
        }
    }

    public static void setHyperVisor(HyperVisorType type) {
        switch (type) {
            case KVM:
                DEFAULT_HYPERVISOR = KVM.getInstance();
            case LXC:
                DEFAULT_HYPERVISOR = LXC.getInstance();
                break;
            default:
                DEFAULT_HYPERVISOR = KVM.getInstance();
        }
    }

    public static VMSpinUp getInstance() {
        try {
            if(VMSpinUp == null || lvConn == null || !lvConn.isConnected())
                VMSpinUp = new VMSpinUp();
        } catch (LibvirtException e) {
            e.printStackTrace();
            try {
                VMSpinUp = new VMSpinUp();
            } catch (VMSpinUpException ex) {
                ex.printStackTrace();
            }
        } catch (VMSpinUpException e) {
            e.printStackTrace();
        }
        return VMSpinUp;
    }

}
