package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.commands.VMCreate;
import com.lemon.vmspinup.model.commands.VMStart;
import com.lemon.vmspinup.model.listeners.VMStateListener;
import com.lemon.vmspinup.model.storage.StorageVolume;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import com.lemon.vmspinup.model.vm.VirtualMachineCallback;
import picocli.CommandLine;


@CommandLine.Command(name = "create", mixinStandardHelpOptions = true, description = "-- Creates a VM Instance")

public class CmdVmCreate implements Runnable, VMCreate {

    @CommandLine.ParentCommand
    private CliCommands parent = null;


    @Override
    public void run() {

            VirtualMachine vm = new VirtualMachine("ubuntu-1", 2097152, 2, null);
            vm.setName("ubuntu-1");
            vm.setRamAmount(2097152);
            vm.setvCPU(2);
            vm.setStorageVolume(new StorageVolume("/home/xuw/servers/test_bare_metal.qcow2"));
            vm.setVMStateListener(new VirtualMachineCallback());
            this.vmCreate(vm);
    }

    @Override
    public void vmCreate(VirtualMachine vm) {

        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        vmSpinUp.vmCreate(vm);

    }
}