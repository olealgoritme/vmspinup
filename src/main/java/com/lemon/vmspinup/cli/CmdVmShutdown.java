package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.commands.VMShutdown;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "shutdown", mixinStandardHelpOptions = true, description = "-- Shuts down a VM Instance")
//@CommandLine.Option()

public class CmdVmShutdown implements Runnable, VMShutdown {

    @CommandLine.ParentCommand

    private CliCommands parent = null;

    @Override
    public void run() {
            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm;
            vm = vmSpinUp.vmLookupByName("ubuntu-1");
            this.vmShutdown(vm);
    }

    @Override
    public void vmShutdown(VirtualMachine vm) {
        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        vmSpinUp.vmShutdown(vm);
    }
}