package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "suspend", description = "-- Suspends an object",
        subcommands = {
                CmdVmSuspend.CmdVmSuspendVm.class
        })

public class CmdVmSuspend implements Runnable {

    @Override
    public void run() {
        System.out.println("Missing parameter [suspend vm/pool/storage/network/interface");
    }

    @CommandLine.Command(name = "vm")
    static class CmdVmSuspendVm implements Runnable {
        @CommandLine.Option(names = {"--name"}, required = true)
        String name;

        @Override
        public void run() {
            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm = vmSpinUp.vmLookupByName(name);
            vmSpinUp.vmSuspend(vm);
        }
    }
}
