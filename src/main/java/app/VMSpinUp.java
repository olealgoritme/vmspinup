package app;

import model.hypervisors.HyperVisor;
import model.hypervisors.KVM;
import model.vm.VMCommands;
import model.vm.VirtCommands;
import model.vm.VirtualMachine;
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
        return new VirtualMachine()
                .withHyperVisor(DEFAULT_HYPERVISOR)
                .withUUID(UUID.fromString(domain.getUUIDString()))
                .withId(domain.getID())
                .withName(domain.getName())
                .withCPUs(domain.getMaxVcpus())
                .withRamAmount(domain.getMaxMemory())
                .build();

    } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VirtualMachine vmLookupByID(int id) {
        try {
            getInstance();
            domain = lvConn.domainLookupByID(id);
            return new VirtualMachine()
                    .withHyperVisor(DEFAULT_HYPERVISOR)
                    .withUUID(UUID.fromString(domain.getUUIDString()))
                    .withId(domain.getID())
                    .withName(domain.getName())
                    .withCPUs(domain.getMaxVcpus())
                    .withRamAmount(domain.getMaxMemory())
                    .build();
        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VirtualMachine vmLookupByUUID(UUID uuid) {
        try {
            getInstance();
            domain = lvConn.domainLookupByUUID(uuid);
            return new VirtualMachine()
                    .withHyperVisor(DEFAULT_HYPERVISOR)
                    .withUUID(UUID.fromString(domain.getUUIDString()))
                    .withId(domain.getID())
                    .withName(domain.getName())
                    .withCPUs(domain.getMaxVcpus())
                    .withRamAmount(domain.getMaxMemory())
                    .build();
        } catch (LibvirtException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean vmDestroy(VirtualMachine vm) {
        try {
            getInstance();
            lvConn.domainLookupByUUID(vm.getUUID());
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
            lvConn.domainCreateXML(vm.toXML(), 0);
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
    public boolean vmPause(VirtualMachine vm) {
        return false;
    }

    @Override
    public boolean vmResume(VirtualMachine vm) {
        return false;
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
