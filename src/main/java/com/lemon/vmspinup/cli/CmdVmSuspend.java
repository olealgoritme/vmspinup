package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "suspend", mixinStandardHelpOptions = true, description = "-- Suspends a VM Instance")

public class CmdVmSuspend implements Runnable {

    @CommandLine.ParentCommand

    private CliCommands parent = null;

    @Override
    public void run() {
            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm = vmSpinUp.vmLookupByName("ubuntu5");
            vmSpinUp.vmSuspend(vm);
    }

}