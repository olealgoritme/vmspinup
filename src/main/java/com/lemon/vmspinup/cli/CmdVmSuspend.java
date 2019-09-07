package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.commands.VMStart;
import com.lemon.vmspinup.model.commands.VMSuspend;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "suspend", mixinStandardHelpOptions = true, description = "-- Suspends a VM Instance")
//@CommandLine.Option()

public class CmdVmSuspend implements Runnable, VMSuspend {

    @CommandLine.ParentCommand

    private CliCommands parent = null;

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm;
            vm = vmSpinUp.vmLookupByName("ubuntu-1");
            this.vmSuspend(vm);
        }
    }

    @Override
    public void vmSuspend(VirtualMachine vm) {
        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        vmSpinUp.vmSuspend(vm);

    }
}