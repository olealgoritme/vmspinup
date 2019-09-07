package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;


@CommandLine.Command(name = "create", mixinStandardHelpOptions = true, description = "-- Creates a VM Instance")

public class CmdVmCreate implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    @Override
    public void run() {

            VirtualMachine vm = new VirtualMachine("ubuntu-1", 2097152, 2, null);
            VMSpinUp.getInstance().vmCreate(vm);

    }
}