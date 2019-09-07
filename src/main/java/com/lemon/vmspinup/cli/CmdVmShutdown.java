package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "shutdown", mixinStandardHelpOptions = true, description = "-- Shuts down a VM Instance")
//@CommandLine.Option()

public class CmdVmShutdown implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    @Override
    public void run() {

            VMSpinUp spinUp = VMSpinUp.getInstance();
            VirtualMachine vm = spinUp.vmLookupByName("ubuntu-1");
            spinUp.vmShutdown(vm);

    }
}