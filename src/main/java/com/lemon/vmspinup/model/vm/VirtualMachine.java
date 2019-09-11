package com.lemon.vmspinup.model.vm;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.app.VMUserData;
import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.model.listeners.VMStateListener;
import com.lemon.vmspinup.model.storage.VMStoragePool;
import com.lemon.vmspinup.model.storage.VMStorageVolume;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

/*
*  abstraction Layer between the horrendous libvirt XML domain object
*  reference and our VM instance object
*  this is (will be) a rewrite of the libvirt bindings API
 */

public class VirtualMachine {

    private int id;                                                                     // unique ID when instance is running (libvirt backend assigns this value)
    private String name;                                                                // name
    private UUID uuid = UUID.randomUUID();                                              // 128 bit long value (A UUID is made of up of hex digits  (4 chars each) along with 4 “-” symbols which make its length equal to 36 characters.)

    private int vCPU;                                                                   // vCPU count
    private long ramAmount;                                                             // delegated ram usage

    private String os = "hvm";                                                          // OS name (e.g hvm)
    private String arch = "x86_64";                                                     // OS architecture
    private ArrayList<VMStorageVolume> storageVolumes = new ArrayList<>();              // storage volumes
    private VMUserData vmUserData = new VMUserData("/mnt/instances/vm/ole1-user-data.img");  // object for VM user-meta-data (used in conjuction with cloudimages to set hostname, networks, passwords etc)
    private HyperVisor hyperVisor = VMSpinUp.DEFAULT_HYPERVISOR;                           // default HyperVisor type

    private VMStateListener vmStateListener;                                            // VM state / event listener
    private DomainInfo.DomainState vmState = DomainInfo.DomainState.VIR_DOMAIN_NOSTATE; // default VM state

    public VirtualMachine() {
    }

    // minimum configuration
    public VirtualMachine(String name, long ramAmount, int vCPU) {
        this.name = name;
        this.ramAmount = ramAmount;
        this.vCPU = vCPU;
    }

    public void setHyperVisor(HyperVisor hyperVisor) {
        this.hyperVisor = hyperVisor;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRamAmount(long ramAmount) {
        this.ramAmount = ramAmount;
    }

    public void addStorageVolume(VMStorageVolume volume) {
        this.storageVolumes.add(volume);
    }

    public void removeStorageVolumeByKey(String key) {
        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            this.storageVolumes.remove(vmSpinUp.storageVolumeLookupByKey(key));
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public void setvCPU(int vCPU) {
        this.vCPU = vCPU;
    }

    public HyperVisor getHyperVisor() {
        return this.hyperVisor;
    }
    public int getvCPU() {
        return this.vCPU;
    }

    public long getRamAmount() {
        return this.ramAmount;
    }
    public UUID getUUID() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }
    public int getID() {
        return this.id;
    }

    public ArrayList<VMStorageVolume> getVMStorageVolumes() {
        return this.storageVolumes;
    }

    public String toXML(HyperVisor hv, String name, long memory, int vcpu, String os, String arch) {
        // TODO: create builder from class
        return null;
    }

    public String toXML() {

            String XML =    "<domain type='%s'>"+ "\n" +
                            "<name>%s</name>"+  "\n" +
                            "<timer name='kvmclock' present='yes'/>" + "\n" +
                            "<memory unit='bytes'>%s</memory>"+ "\n" +
                            "<vcpu>%s</vcpu>"+ "\n" +
                            "<os>"+ "\n" +
                            "<type arch='%s'>%s</type>"+ "\n" +
                            "</os>"+ "\n" +
                            "<clock sync='localtime'/>"+ "\n" +
                            "<features>" + "\n" +
                            "<acpi/>" + "\n" +
                            "<apic/>" + "\n" +
                            "</features>" + "\n" +


                            "<on_poweroff>destroy</on_poweroff>" + "\n" +
                            "<on_reboot>restart</on_reboot>" + "\n" +
                            "<on_crash>restart</on_crash>" + "\n" +
                            "<devices>"+ "\n" +

                            // quirk workaround, needed serial/console
                            "<serial type='pty'>" + "\n" +          // IMPORTANT TO PUT SERIAL / CONSOLE FIRST IN <DEVICES> !!
                            "<target port='0'/>" + "\n" +
                            "</serial>" + "\n" +
                            "<console type='pty'>" + "\n" +
                            "<target type='serial' port='0'/>" + "\n" +
                            "</console>" + "\n" +

                            // EMULATOR
                            "<emulator>/usr/bin/qemu-system-x86_64</emulator>"+ "\n" +

                            // DISK 1
                            "<disk type='file' device='disk'>"+ "\n" +
                            "<driver name='qemu' type='qcow2'/>"+ "\n" +
                            "<source file='%s'/>"+ "\n" +
                            "<target dev='vda' bus='virtio'/>"+ "\n" +
                            // "<boot order='1'/>"+ "\n" +
                            "</disk>"+ "\n" +

                            // DISK 2
                            "<disk type='file' device='disk'>"+ "\n" +
                            "<source file='%s'/>"+ "\n" +
                            "<target dev='vdb' bus='virtio'/>"+ "\n" +
                            "</disk>"+ "\n" +

                            // Network
                            "<interface type='network'>"+ "\n" +
                            "<source network='default'/>"+ "\n" +
                            "<mac address='24:42:53:21:52:45'/>"+ "\n" +
                            "</interface>"+ "\n" +
                            "<graphics type='vnc' port='-1' autoport='yes'/>"+ "\n" +
                            "</devices>"+ "\n" +

                            "</domain>";


            String xml = String.format(XML, this.hyperVisor.getType(), this.name, this.ramAmount,
                                      this.vCPU, this.arch, this.os,
                                      this.storageVolumes.get(0).getPath(),
                                      this.storageVolumes.get(1).getPath());

        //System.out.println(xml);
        return xml;
    }


    public VMStateListener getVMStateListener() {
        return this.vmStateListener;
    }

    public void setVMStateListener(VMStateListener vmStateListener) {
        this.vmStateListener = vmStateListener;
    }

    public DomainInfo.DomainState getVMState() {
        return vmState;
    }

    public void setVMState(DomainInfo.DomainState vmState) {
        this.vmState = vmState;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public VMUserData getVmUserData() {
        return vmUserData;
    }

    public void setVmUserData(VMUserData vmUserData) {
        this.vmUserData = vmUserData;
    }
}
