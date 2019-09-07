package com.lemon.vmspinup.app;

import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.model.hypervisor.HyperVisorType;
import com.lemon.vmspinup.model.hypervisor.KVM;
import com.lemon.vmspinup.model.hypervisor.LXC;
import com.lemon.vmspinup.model.commands.VMCommands;
import com.lemon.vmspinup.model.commands.VirtCommands;
import com.lemon.vmspinup.model.listeners.VMStateListener;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.*;
import org.libvirt.event.DomainEvent;
import org.libvirt.event.DomainEventType;
import org.libvirt.event.LifecycleListener;

import java.util.ArrayList;
import java.util.UUID;

import static org.libvirt.Library.runEventLoop;

public class VMSpinUp implements VMCommands, VirtCommands {

    private static VMSpinUp VMSpinUp;
    private static HyperVisor DEFAULT_HYPERVISOR = KVM.getInstance();
    private static VirtualMachine vm;

    // LV
    private static Connect lvConn;
    private static ConnectAuth lvCa;
    private static Domain domain;


    private VMSpinUp() {
        lvCa = new ConnectAuthDefault();
        try {
            lvConn = new Connect(DEFAULT_HYPERVISOR.getUriString(), lvCa, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

    @Override
    public VirtualMachine vmLookupByName(String vmName) {
        try {
            getInstance();
            domain = lvConn.domainLookupByName(vmName);
            vm = new VirtualMachine();
            vm.setVmState(domain.getEventStatus());
            vm.setHyperVisor(DEFAULT_HYPERVISOR);
            vm.setUUID(UUID.fromString(domain.getUUIDString()));
            vm.setID(domain.getID());
            vm.setName(domain.getName());
            vm.setvCPU(domain.getMaxVcpus());
            vm.setRamAmount(domain.getMaxMemory());

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
            vm = new VirtualMachine();
            vm.setVmState(domain.getEventStatus());
            vm.setHyperVisor(DEFAULT_HYPERVISOR);
            vm.setUUID(UUID.fromString(domain.getUUIDString()));
            vm.setID(domain.getID());
            vm.setName(domain.getName());
            vm.setvCPU(domain.getMaxVcpus());
            vm.setRamAmount(domain.getMaxMemory());
        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
            return vm;
    }

    @Override
    public VirtualMachine vmLookupByUUID(UUID uuid) {
        try {
            getInstance();
            domain = lvConn.domainLookupByUUID(uuid);
            vm = new VirtualMachine();
            vm.setVmState(domain.getEventStatus());
            vm.setHyperVisor(DEFAULT_HYPERVISOR);
            vm.setUUID(UUID.fromString(domain.getUUIDString()));
            vm.setID(domain.getID());
            vm.setName(domain.getName());
            vm.setvCPU(domain.getMaxVcpus());
            vm.setRamAmount(domain.getMaxMemory());

        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
            return vm;
    }

    @Override
    public boolean vmDestroy(VirtualMachine vm) {
        try {
            getInstance();
            lvConn.domainLookupByName(vm.getName()).destroy();
        } catch (LibvirtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean vmCreateFromXML(String xml) {
        try {
            getInstance();
            lvConn.domainCreateXML(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean vmCreate(VirtualMachine vm) {
        try {
            getInstance();
            String xml = vm.toXML();
            domain = lvConn.domainCreateXML(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return false;
        } finally {
            addListener(vm,domain);
        }
        return true;
    }


    private void addListener(final VirtualMachine virtualMachine, Domain domain) {
        if(virtualMachine.getVMStateListener() != null) {
            VMStateListener listener = virtualMachine.getVMStateListener();
            try {
                domain.addLifecycleListener(new LifecycleListener() {
                    @Override
                    public void onLifecycleChange(Domain domain1, DomainEvent event) {

                        System.out.println("DOMAINEVENT: " + event);
                        if (event.getType() == DomainEventType.DEFINED) {
                            virtualMachine.setVmState(DomainInfo.DomainState.VIR_DOMAIN_NOSTATE);
                            listener.onCreated(virtualMachine);

                        } else if (event.getType() == DomainEventType.STARTED) {
                            virtualMachine.setVmState(DomainInfo.DomainState.VIR_DOMAIN_RUNNING);
                            listener.onStarted(virtualMachine);

                        } else if (event.getType() == DomainEventType.SUSPENDED) {
                            virtualMachine.setVmState(DomainInfo.DomainState.VIR_DOMAIN_PAUSED);
                            listener.onSuspended(virtualMachine);

                        } else if (event.getType() == DomainEventType.RESUMED) {
                            virtualMachine.setVmState(DomainInfo.DomainState.VIR_DOMAIN_RUNNING);
                            listener.onResumed(virtualMachine);

                        } else if (event.getType() == DomainEventType.SHUTDOWN) {
                            virtualMachine.setVmState(DomainInfo.DomainState.VIR_DOMAIN_SHUTDOWN);
                            listener.onShutdown(virtualMachine);

                        } else if (event.getType() == DomainEventType.CRASHED) {
                            virtualMachine.setVmState(DomainInfo.DomainState.VIR_DOMAIN_NOSTATE);
                            listener.onCrashed(virtualMachine);
                        }
                    }
                });

            } catch (LibvirtException ex) {
                ex.printStackTrace();
            } finally {

                new Thread() {
                    public void run() {
                        try {
                            Library.runEventLoop();
                        } catch (InterruptedException | LibvirtException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }


        }
    }

    @Override
    public boolean vmDefinePersistent(VirtualMachine vm) {
        try {
            getInstance();
            String xml = vm.toXML();
            lvConn.domainDefineXML(xml);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean vmStart(VirtualMachine vm) {
        try {
            getInstance();
            String xml = vm.toXML();
            lvConn.domainCreateLinux(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean vmShutdown(VirtualMachine vm) {
        return false;
    }

    @Override
    public boolean vmSuspend(VirtualMachine vm) {
        try {
            getInstance();
            lvConn.domainLookupByName(vm.getName()).suspend();
        } catch (LibvirtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean vmResume(VirtualMachine vm) {
        try {
            getInstance();
            lvConn.domainLookupByName(vm.getName()).resume();
        } catch (LibvirtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<VirtualMachine> listVMs() {
        int[] ids;
        ArrayList<VirtualMachine> vmList = new ArrayList<VirtualMachine>();
        try {
            getInstance();
            ids = lvConn.listDomains();
            for (int i : ids) {
                vm = VMSpinUp.vmLookupByID((i));
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
            VMSpinUp = new VMSpinUp();
        }
        return VMSpinUp;
    }

}
