package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.App;
import com.lemon.vmspinup.app.ByteCalc;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.storage.VMStorageVolume;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

import static com.lemon.vmspinup.model.storage.VMStorageVolume.*;


@CommandLine.Command(name = "create", description = "create -- Creates an Object (vm, pool, storage, network, interface)",
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
        @CommandLine.Option(names = {"--name"}, required = true) String name;
        @CommandLine.Option(names = {"--cpu"}, required = true) int cpu;
        @CommandLine.Option(names = {"--ram"}, required = true) long ram;
        @CommandLine.Option(names = {"--disk"}) long disk = 10;

        @Override
        public void run() {
            System.out.println(
                    "Created VM instance" + "\n" +
                    "Name: " + name + "\n" +
                    "cpu: " + cpu + "\n" +
                    "ram: " + ram + "\n" +
                    "disk: " + disk + "\n");
            long bytesRam = new ByteCalc().size(ram).to(ByteCalc.SIZE.GIGABYTE);
            long bytesDisk = new ByteCalc().size(disk).to(ByteCalc.SIZE.GIGABYTE);

            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm = new VirtualMachine();

            vm.setName(name);
            vm.setRamAmount(bytesRam);
            vm.setvCPU(cpu);

            //ArrayList<VMStoragePool> pools = new ArrayList<>();
            //vmSpinUp.poolList(pools);
            VMStorageVolume vmStorageVolume = new VMStorageVolume();
            vmStorageVolume.setPath(App.VM_INSTANCES_PATH + "/" + name + "-instance.img");
            vmStorageVolume.setFormat(Format.QCOW2);
            vm.addStorageVolume(vmStorageVolume);

            vmStorageVolume = new VMStorageVolume();
            vmStorageVolume.setPath(App.VM_INSTANCES_PATH + "/" + name + "-user-data.img");
            vmStorageVolume.setFormat(Format.RAW);
            vm.addStorageVolume(vmStorageVolume);

            vmSpinUp.vmCreate(vm);
        }

    }
}