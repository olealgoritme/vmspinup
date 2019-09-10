package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.storage.VMStorageVolume;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;


@CommandLine.Command(name = "create", mixinStandardHelpOptions = true, description = "-- Creates a VM Instance")

public class CmdVmCreate implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;


    @Override
    public void run() {

            VMSpinUp vmSpinUp = VMSpinUp.getInstance();

            VirtualMachine vm = new VirtualMachine();
            vm.setName("ubuntu5");
            vm.setRamAmount(2097152);
            vm.setvCPU(2);
            vm.setVMStorageVolume(new VMStorageVolume("/mnt/instances/vm/ole1.qcow2"));

            vmSpinUp.vmCreate(vm);
    }
}