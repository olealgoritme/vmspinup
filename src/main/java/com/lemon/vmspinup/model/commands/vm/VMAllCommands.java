package com.lemon.vmspinup.model.commands.vm;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.listeners.VMStateListener;
import com.lemon.vmspinup.model.storage.VMStoragePool;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;
import org.libvirt.event.DomainEvent;
import org.libvirt.event.DomainEventType;
import org.libvirt.event.LifecycleListener;

import java.util.ArrayList;
import java.util.UUID;

public interface VMAllCommands {

    default VirtualMachine createVirtualMachine(Domain domain) throws LibvirtException {

        VirtualMachine vm = new VirtualMachine();
        vm.setVMState(domain.getEventStatus());
        vm.setHyperVisor(VMSpinUp.DEFAULT_HYPERVISOR);
        vm.setUUID(UUID.fromString(domain.getUUIDString()));
        vm.setID(domain.getID());
        vm.setName(domain.getName());
        vm.setvCPU(domain.getMaxVcpus());
        vm.setRamAmount(domain.getMaxMemory());
        return vm;
    }



    default void addListener(final VirtualMachine virtualMachine, final Domain domain) {

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

    default boolean vmCreate(VirtualMachine vm) {
            try {
            VMSpinUp.connect.domainCreateXML(vm.toXML(), 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return false;
        }
            return true;
    }
    default void vmReboot(VirtualMachine vm) {
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByUUID(vm.getUUID());
            VMSpinUp.domain.reboot(0);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }
    default void vmCreateFromXML(String xml) {
        try {
            VMSpinUp.connect.domainCreateXML(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

    default void vmDefine(VirtualMachine vm) {
        try {
            VMSpinUp.connect.domainDefineXML(vm.toXML());
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }
    default void vmDestroy(VirtualMachine vm) {
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByUUID(vm.getUUID());
            VMSpinUp.domain.destroy();
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }
    default void vmResume(VirtualMachine vm) {
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByUUID(vm.getUUID());
            VMSpinUp.domain.resume();
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }
    default void vmShutdown(VirtualMachine vm) {
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByUUID(vm.getUUID());
            VMSpinUp.domain.shutdown();
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }
    default void vmStart(VirtualMachine vm) {
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByUUID(vm.getUUID());
            VMSpinUp.connect.domainCreateXML(vm.toXML(), 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }
    default void vmSuspend(VirtualMachine vm) {
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByUUID(vm.getUUID());
            VMSpinUp.domain.suspend();
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }
    default void vmUndefine(VirtualMachine vm) {
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByUUID(vm.getUUID());
            VMSpinUp.domain.undefine();
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }
    default ArrayList<VirtualMachine> vmList() {
        int[] ids;
        ArrayList<VirtualMachine> vmList = new ArrayList<VirtualMachine>();
        VirtualMachine vm;
        try {

            ids = VMSpinUp.connect.listDomains();
            for (int i : ids) {
                vm = this.vmLookupByID((i));
                vmList.add(vm);
            }

        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
        return vmList;
    }

    default VirtualMachine vmLookupByUUID(UUID uuid) {
        VirtualMachine vm;
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByUUID(uuid);
            vm = createVirtualMachine(VMSpinUp.domain);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
        return vm;
    }

    default VirtualMachine vmLookupByID(int id) {
        VirtualMachine vm;
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByID(id);
            vm = createVirtualMachine(VMSpinUp.domain);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
        return vm;
    }
    default VirtualMachine vmLookupByName(String vmName) {
        VirtualMachine vm;
        try {
            VMSpinUp.domain = VMSpinUp.connect.domainLookupByName(vmName);
            vm = createVirtualMachine(VMSpinUp.domain);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
        return vm;
    }

}
