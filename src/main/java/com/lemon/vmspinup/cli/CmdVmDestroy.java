package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "destroy", mixinStandardHelpOptions = true, description = "-- Destroys a VM Instance")

public class CmdVmDestroy implements Runnable {

    @CommandLine.ParentCommand

    private CliCommands parent = null;

    @Override
    public void run() {
            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm = vmSpinUp.vmLookupByName("ubuntu5");
            vmSpinUp.vmDestroy(vm);
    }
}