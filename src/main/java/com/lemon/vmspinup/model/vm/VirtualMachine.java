package com.lemon.vmspinup.model.vm;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.model.listeners.VMStateListener;
import com.lemon.vmspinup.model.states.VMState;
import com.lemon.vmspinup.model.storage.StorageVolume;
import org.libvirt.DomainInfo;
import org.libvirt.event.DomainEventType;

import java.util.UUID;

/*
*  Layer between the horrendous libVirt XML domain object
*  and our VM instance object
*
 */

/*

TODO: NEED STATES + MORE Node information
 */
public class VirtualMachine {

    private int id;                         // unique ID when instance is running
    private String name;                    // name
    private UUID uuid;                      // 128 bit long value (A UUID is made of up of hex digits  (4 chars each) along with 4 “-” symbols which make its length equal to 36 characters.)

    private int vCPU;                       // vCPU count
    private long ramAmount;                 // delegated ram usage

    private String os = "Linux";            // OS name (e.g hvm)
    private String arch = "x86_64";         // OS arch
    private int storageAmount;              // storage amount (for creating!)
    private StorageVolume storageVolume;       // storage volume image path
    private HyperVisor hyperVisor;          // HyperVisor type

    private VMStateListener vmStateListener;
    private DomainInfo.DomainState vmState = DomainInfo.DomainState.VIR_DOMAIN_NOSTATE;

    public VirtualMachine() {
    }

    // minimum configuration
    public VirtualMachine(String name, long ramAmount, int vCPU, StorageVolume storageVolume) {
        this.name = name;
        this.ramAmount = ramAmount;
        this.vCPU = vCPU;
        this.storageVolume = storageVolume;
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

    public void setStorageAmount(int storageAmount) {
        this.storageAmount = storageAmount;
    }

    public void setStorageVolume(StorageVolume storageVolume) {
        this.storageVolume = storageVolume;
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

    public String getUUIDString() {
        return this.uuid.toString();
    }

    public String getName() {
        return this.name;
    }
    public int getID() {
        return this.id;
    }

    public StorageVolume getStorageVolume() {
        return this.storageVolume;
    }

    public String toXML(HyperVisor hv, String name, long memory, int vcpu, String os, String arch) {
        // TODO: create builder from class
        return null;
    }

    public String toXML() {

            storageVolume = new StorageVolume("/home/xuw/servers/test_bare_metal.qcow2");

            String XML =    "<domain type='%s'>"+ "\n" +
                            "<name>%s</name>"+  "\n" +
                            "<timer name='kvmclock' present='yes'/>" + "\n" +
                            "<memory unit='KiB'>%s</memory>"+ "\n" +
                            "<vcpu>%s</vcpu>"+ "\n" +
                            "<os>"+ "\n" +
                            "<type arch='%s'>%s</type>"+ "\n" +
                            "</os>"+ "\n" +
                            "<clock sync='localtime'/>"+ "\n" +
                            "<devices>"+ "\n" +
                            "<emulator>/usr/bin/qemu-system-x86_64</emulator>"+ "\n" +
                            "<disk type='file' device='disk'>"+ "\n" +
                            "<driver name='qemu' type='qcow2'/>"+ "\n" +
                            "<source file='%s'/>"+ "\n" +
                            "<target dev='vda' bus='virtio'/>"+ "\n" +
                            "<boot order='1'/>"+ "\n" +
                            "</disk>"+ "\n" +
                            "<interface type='network'>"+ "\n" +
                            "<source network='default'/>"+ "\n" +
                            "<mac address='24:42:53:21:52:45'/>"+ "\n" +
                            "</interface>"+ "\n" +
                            "<graphics type='vnc' port='-1' autoport='yes'/>"+ "\n" +
                            "</devices>"+ "\n" +
                            "</domain>";

            return String.format(XML, this.hyperVisor.getName(), this.name, this.ramAmount, this.vCPU, this.arch, this.os, this.storageVolume.getPath());
    }


    public VMStateListener getVMStateListener() {
        return vmStateListener;
    }

    public void setVMStateListener(VMStateListener vmStateListener) {
        this.vmStateListener = vmStateListener;
    }

    public DomainInfo.DomainState getVmState() {
        return vmState;
    }

    public void setVmState(DomainInfo.DomainState vmState) {
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
}
