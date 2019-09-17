package com.lemon.vmspinup.model.vm;

import com.lemon.vmspinup.app.JAXBConvert;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.cloudinit.CloudInit;
import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.listeners.VMStateListener;
import com.lemon.vmspinup.model.storage.VMStorageVolume;
import com.lemon.vmspinup.xml.storage.Disk;
import com.lemon.vmspinup.xml.vm.*;
import org.libvirt.DomainInfo;

import javax.xml.bind.JAXBException;
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
    private CloudInit cloudInitConfig;                                                  //
    private HyperVisor hyperVisor = VMSpinUp.DEFAULT_HYPERVISOR;                        // default HyperVisor type

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

    public String toXML() throws JAXBException {

        DomainXML config = new DomainXML();
        config.setType( this.hyperVisor.getType())
                .setName( this.getName() )
                .setMemory( this.getRamAmount())
                .setMemoryUnit("bytes")
                .setVcpu( this.getvCPU() );

        config.getOs()
                .setType("hvm")
                .setTypeArch("x86_64")
                .setTypeMachine("q35");

        config.getFeatures()
                .enableAcpi()
                .enableApic();


        Disk disk1 = new Disk();
        disk1.setDriverName( this.storageVolumes.get(0).getDriver().name())
                .setBootOrder("1")
                .setDriverType("qcow2")
                .setSourceFile(this.storageVolumes.get(0).getPath())
                .setTargetBus("virtio")
                .setTargetDev("vda");
        config.addDisk(disk1);


        Disk disk2 = new Disk();
        disk2.setSourceFile(this.storageVolumes.get(1).getPath())
                .setBootOrder("2")
                .setTargetBus("virtio")
                .setTargetDev("vdb");
        config.addDisk(disk2);

        Interface iFace = new Interface();
        iFace
                .setType(Interface.TYPE.NETWORK)
                .setSourceNetwork("default");

        config.addInterface(iFace);

        SerialConsole console = new SerialConsole();
        console.setType("pty")
                .setTargetPort("0");
        config.addConsole(console);

        Graphics graphics = new Graphics();
        graphics.setType(Graphics.TYPE.VNC)
                .setListen("0.0.0.0")
                .setPort("-1");

        config.addGraphics(graphics);

        // marshalling
        return new JAXBConvert(new Class[]{DomainXML.class}).objectToXML(config);

        /*
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
         */

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

    public CloudInit getCloudInitConfig() {
        return cloudInitConfig;
    }

    public void setCloudInitConfig(CloudInit cloudInitConfig) {
        this.cloudInitConfig = cloudInitConfig;
    }
}
