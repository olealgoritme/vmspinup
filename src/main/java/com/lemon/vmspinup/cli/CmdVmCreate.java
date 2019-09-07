package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.commands.VMCreate;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;


@CommandLine.Command(name = "create", mixinStandardHelpOptions = true, description = "-- Creates a VM Instance")

public class CmdVmCreate implements Runnable, VMCreate {

    @CommandLine.ParentCommand

    private CliCommands parent = null;


    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            VirtualMachine vm = new VirtualMachine("ubuntu-1", 2097152, 2, null);
            this.vmCreate(vm);
        }
    }

    @Override
    public void vmCreate(VirtualMachine vm) {
        VMSpinUp.getInstance().vmCreate(vm);
    }
}