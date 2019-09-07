package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.commands.VMDestroy;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "destroy", mixinStandardHelpOptions = true, description = "-- Destroys a VM Instance")
//@CommandLine.Option()

public class CmdVmDestroy implements Runnable, VMDestroy {

    @CommandLine.ParentCommand

    private CliCommands parent = null;

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm;
            vm = vmSpinUp.vmLookupByName("ubuntu-1");
            this.vmDestroy(vm);
        }
    }

    @Override
    public void vmDestroy(VirtualMachine vm) {
        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        vmSpinUp.vmDestroy(vm);
    }
}