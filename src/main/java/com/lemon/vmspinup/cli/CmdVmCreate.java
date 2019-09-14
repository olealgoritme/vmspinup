package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.*;
import com.lemon.vmspinup.cloudinit.CloudInit;
import com.lemon.vmspinup.cloudinit.CloudInitConfig;
import com.lemon.vmspinup.cloudinit.CloudInitMetaData;
import com.lemon.vmspinup.model.storage.VMStorageVolume;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import com.lemon.vmspinup.xml.storage.Volume;
import com.lemon.vmspinup.xml.storage.BackingStore;
import com.lemon.vmspinup.xml.storage.Target;
import picocli.CommandLine;

import java.io.File;

import static com.lemon.vmspinup.model.storage.VMStorageVolume.*;


@CommandLine.Command(name = "create", description = "-- Creates an Object (vm, pool, storage, network, interface)",
        subcommands = {
            CmdVmCreate.CmdVmCreateVM.class
        })

public class CmdVmCreate implements Runnable {

    @Override
    public void run() {
        System.out.println("Missing parameter [create vm/pool/storage/network/interface");
    }


    @CommandLine.Command(name = "vm")
    static class CmdVmCreateVM implements Runnable {
        @CommandLine.Option(names = {"--name"}, required = true)
        String name;
        @CommandLine.Option(names = {"--cpu"}, required = true)
        int cpu;
        @CommandLine.Option(names = {"--ram"}, required = true)
        long ram;
        @CommandLine.Option(names = {"--disk"}, required = true)
        long disk = 10;
        @CommandLine.Option(names = {"--file"})
        File file;

        @Override
        public void run() {
            System.out.println(
                    "Created VM instance" + "\n" +
                            "Name: " + name + "\n" +
                            "cpu: " + cpu + "\n" +
                            "ram: " + ram + "\n" +
                            "disk: " + disk + "\n");
            long bytesRam = new ByteCalc().sizeOf(ram).to(ByteCalc.SIZE.GIGABYTE);
            long bytesDisk = new ByteCalc().sizeOf(disk).to(ByteCalc.SIZE.GIGABYTE);

            VMSpinUp vmSpinUp = VMSpinUp.getInstance();

            VirtualMachine vm = new VirtualMachine();
            vm.setName(name);
            vm.setRamAmount(bytesRam);
            vm.setvCPU(cpu);

            String userDataFile = name + "-user-data.img";
            String userDataImg = Config.VM_USER_DATA_PATH + "/" + userDataFile;

            String vmInstanceFile = name + "-instance.img";
            String vmInstanceImg = Config.VM_INSTANCES_PATH + "/" + vmInstanceFile;

            String backingFile = "bionic-server-cloudimg-amd64.img";
            String backingImg = Config.CLOUD_IMG_PATH + "/" + backingFile;



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
            VMSpinUp.getInstance().poolCreateVolume("vm-userdata", xmlVolume2);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CloudInitConfig config = new CloudInitConfig();
            config.setHostname(name);
            config.setPassword("2222");

            CloudInitMetaData metaData = new CloudInitMetaData();
            metaData.setInstanceId("instance-" + name);
            metaData.setLocalHostname(name + "local");

            CloudInit cloudInit = new CloudInit(config, metaData, userDataImg);
            if(cloudInit.buildFiles()) {
                System.out.println("Built all cloud-user-data files successfully");
            }

                    VMStorageVolume vmStorageVolume = new VMStorageVolume();
                    vmStorageVolume.setPath(vmInstanceImg);
                    vmStorageVolume.setFormat(Format.QCOW2);
                    vm.addStorageVolume(vmStorageVolume);

                    vmStorageVolume = new VMStorageVolume();
                    vmStorageVolume.setPath(userDataImg);
                    vmStorageVolume.setFormat(Format.RAW);
                    vm.addStorageVolume(vmStorageVolume);

                    vmSpinUp.vmCreate(vm);
                }

    }
}