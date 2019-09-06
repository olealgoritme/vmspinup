package model.vm;

import app.VMSpinUp;
import model.hypervisors.HyperVisor;
import model.kvm.StorageVolume;
import org.libvirt.Domain;
import org.libvirt.StorageVol;

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

    private int storageAmount;              // storage amount (for creating!)
    private StorageVolume storageVolume;       // storage volume image path
    private HyperVisor hyperVisor;          // HyperVisor type

    public VirtualMachine() {
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

    public VirtualMachine withCPUs(int vCPU) {
        this.vCPU = vCPU;
        return this;
    }

    public VirtualMachine withName(String name) {
        this.name = name;
        return this;
    }

    public VirtualMachine withId(int id) {
        this.id = id;
        return this;
    }

    public VirtualMachine withUUID(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public VirtualMachine withHyperVisor(HyperVisor hyperVisor) {
        this.hyperVisor = hyperVisor;
        return this;
    }

    public VirtualMachine withStorageVolume(StorageVolume storageVolume) {
        this.storageVolume = storageVolume;
        return this;
    }
    public VirtualMachine withRamAmount(long ramAmount) {
        this.ramAmount = ramAmount;
        return this;
    }

    public String toXML() {
            String vmName        = this.name;
            long ramAmount       = this.ramAmount;
            int vCPU             = this.vCPU;
            StorageVolume volume = new StorageVolume("/home/xuw/servers/test_bare_metal.qcow2");

            String XML =
                    "<domain type='kvm'>"+ "\n" +
                            "<name>%%s</name>"+  "\n" +
                            "<memory unit='KiB'>%s</memory>"+ "\n" +
                            "<vcpu>%s</vcpu>"+ "\n" +
                            "<os>"+ "\n" +
                            "<type arch='x86_64'>hvm</type>"+ "\n" +
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

            return String.format(XML, vmName, ramAmount, vCPU, volume);
    }

    public VirtualMachine build() {
        if(this.uuid == null) uuid = UUID.randomUUID();
        return new VirtualMachine();
    }

}
