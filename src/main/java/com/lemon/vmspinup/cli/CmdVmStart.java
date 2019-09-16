package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "start", description = "-- Starts a VM Instance")

public class CmdVmStart implements Runnable {

    @CommandLine.ParentCommand

    private CliCommands parent = null;

    @Override
    public void run() {
            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm = vmSpinUp.vmLookupByName("ubuntu5");
            vmSpinUp.vmStart(vm);
    }

}