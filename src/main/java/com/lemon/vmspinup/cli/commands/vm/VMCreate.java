package com.lemon.vmspinup.cli.commands.vm;

import com.lemon.vmspinup.app.ByteCalc;
import com.lemon.vmspinup.app.Config;
import com.lemon.vmspinup.app.JAXBConvert;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.builder.DomainBuilder;
import com.lemon.vmspinup.cloudinit.CloudInit;
import com.lemon.vmspinup.cloudinit.CloudInitConfig;
import com.lemon.vmspinup.cloudinit.CloudInitMetaData;
import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.xml.storage.BackingStore;
import com.lemon.vmspinup.xml.storage.Disk;
import com.lemon.vmspinup.xml.storage.Target;
import com.lemon.vmspinup.xml.storage.Volume;
import com.lemon.vmspinup.xml.vm.DomainXML;
import com.lemon.vmspinup.xml.vm.Features;
import com.lemon.vmspinup.xml.vm.Graphics;
import com.lemon.vmspinup.xml.vm.Interface;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import picocli.CommandLine;

@CommandLine.Command(name = "vm")
public class VMCreate implements Runnable {

    @CommandLine.Option(names = {"--name"}, required = true)
    private String name;
    @CommandLine.Option(names = {"--cpus"}, required = true)
    private int cpus;
    @CommandLine.Option(names = {"--ram"}, required = true)
    private long ram;
    @CommandLine.Option(names = {"--disk"}, required = true)
    private long disk = 10;

    @Override
    public void run() {
        System.out.println(
                "Created VM instance" + "\n" +
                        "Name: " + name + "\n" +
                        "cpu: " + cpus + "\n" +
                        "ram: " + ram + "\n" +
                        "disk: " + disk + "\n");
        long bytesRam = new ByteCalc().sizeOf(ram).to(ByteCalc.SIZE.GIGABYTE);
        long bytesDisk = new ByteCalc().sizeOf(disk).to(ByteCalc.SIZE.GIGABYTE);

        VMSpinUp vmSpinUp = VMSpinUp.getInstance();

        String userDataFile = name + "-user-data.img";
        String userDataImg = Config.VM_USER_DATA_PATH + "/" + userDataFile;

        String vmInstanceFile = name + "-instance.img";
        String vmInstanceImg = Config.VM_INSTANCES_PATH + "/" + vmInstanceFile;

        String backingFile = "bionic-server-cloudimg-amd64.img";
        String backingImg = Config.CLOUD_IMG_PATH + "/" + backingFile;

        Disk disk1 = new Disk();
        disk1.setDriverName( "qemu" )
                .setBootOrder("1")
                .setDriverType("qcow2")
                .setSourceFile(vmInstanceImg)
                .setTargetBus("virtio")
                .setTargetDev("vda");

        Disk disk2 = new Disk();
        disk2.setSourceFile(userDataImg)
                .setBootOrder("2")
                .setTargetBus("virtio")
                .setTargetDev("vdb");

        DomainXML config = DomainBuilder.Builder()
                .withHyperVisor(HyperVisor.TYPE.KVM)
                .withName(name)
                .withCPU(cpus)
                .withMemory(bytesRam).withUnits("bytes")
                .withFeature(Features.TYPE.ACPI).andFeature(Features.TYPE.APIC)
                .withDisk(disk1).andDisk(disk2)
                .withInterface(Interface.TYPE.NETWORK)
                .withConsole(true)
                .withGraphics(Graphics.TYPE.VNC);

        // marshalling
        String domainXML = new JAXBConvert(new Class[]{DomainXML.class}).objectToXML(config);

        System.out.println(domainXML);

        // create volumes in storage pool, so we have usable disks
        Volume volume1 = new Volume();
        volume1.setType("file")
                .setName(vmInstanceFile)
                .setKey(vmInstanceImg)
                .setTarget(new Target().setPath(vmInstanceImg).setFormatType("qcow2"))
                .setBackingStore(new BackingStore().setPath(backingImg).setFormatType("qcow2"))
                .setAllocation(0)
                .setAllocationUnit("B")
                .setCapacity(bytesDisk)
                .setCapacityUnit("B");
        // Add Volume "vm-instances" pool
        String xmlVolume1 = new JAXBConvert(new Class[]{Volume.class}).objectToXML(volume1);
        VMSpinUp.getInstance().poolCreateVolume("vm-instances", xmlVolume1);


        Volume volume2 = new Volume();
        volume2.setType("file")
                .setName(userDataFile)
                .setKey(userDataImg)
                .setTarget(new Target().setPath(userDataImg).setFormatType("raw"))
                .setAllocation(0)
                .setAllocationUnit("MB")
                .setCapacity(20)
                .setCapacityUnit("MB");
        // Add Volume to "vm-userdata" pool
        String xmlVolume2 = new JAXBConvert(new Class[]{Volume.class}).objectToXML(volume2);
        vmSpinUp.poolCreateVolume("vm-userdata", xmlVolume2);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // cloud-init config + meta-data
        CloudInitConfig cloudInitConfig = new CloudInitConfig();
        cloudInitConfig.setHostname(name);
        cloudInitConfig.setPassword("2222");

        CloudInitMetaData metaData = new CloudInitMetaData();
        metaData.setInstanceId("instance-" + name);
        metaData.setLocalHostname(name + ".local");

        CloudInit cloudInit = new CloudInit(cloudInitConfig, metaData, userDataImg);
        if(cloudInit.buildFiles()) {
            System.out.println("Built all cloud-user-data files successfully");
        }


        Domain domain = vmSpinUp.vmCreateAndStart(domainXML);
        try {
            domain.addLifecycleListener((d, event) -> System.out.println("DOMAINEVENT: " + event));
        } catch (LibvirtException e) {
            System.out.println("Could not add lifecyclelistener");


        }
    }

}
