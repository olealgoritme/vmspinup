package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;


@CommandLine.Command(name = "reset", mixinStandardHelpOptions = true, description = "-- Resets an Object (vm, pool, storage, network, interface)",
    subcommands = {
            CmdVmReset.CmdVmResetVM.class
    })

public class CmdVmReset implements Runnable {

        @Override
        public void run() {
            System.out.println("Missing parameter [reset vm/pool/storage/network/interface");
        }

        @CommandLine.Command(name = "vm")
        static class CmdVmResetVM implements Runnable {
            @CommandLine.Option(names = {"--name"}, required = true)
            String name;

            @Override
            public void run() {
                VMSpinUp vmSpinUp = VMSpinUp.getInstance();
                VirtualMachine vm = vmSpinUp.vmLookupByName(name);
                vmSpinUp.vmReset(vm);
            }
        }
}