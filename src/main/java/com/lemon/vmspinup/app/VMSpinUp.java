package com.lemon.vmspinup.app;

import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.model.hypervisor.HyperVisorType;
import com.lemon.vmspinup.model.hypervisor.KVM;
import com.lemon.vmspinup.model.hypervisor.LXC;
import com.lemon.vmspinup.model.commands.VMCommands;
import com.lemon.vmspinup.model.commands.VirtCommands;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.*;

import java.util.ArrayList;
import java.util.UUID;

public class VMSpinUp implements VMCommands, VirtCommands {

    private static VMSpinUp VMSpinUp;
    public static HyperVisor DEFAULT_HYPERVISOR = KVM.getInstance();
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
            lvConn.domainCreateXML(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean vmStart(VirtualMachine vm) {
        return false;
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
