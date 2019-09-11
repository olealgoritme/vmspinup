package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "destroy", description = "-- Destroys an Object (vm, pool, storage, network, interface)",
        subcommands = {
                CmdVmDestroy.CmdVmDestroyVM.class
        })

public class CmdVmDestroy implements Runnable {

    @Override
    public void run() {
        System.out.println("Missing parameter [destroy vm/pool/storage/network/interface");
    }

    @CommandLine.Command(name = "vm")
    static class CmdVmDestroyVM implements Runnable {
        @CommandLine.Option(names = {"--name"}, required = true)
        String name;

        @Override
        public void run() {
            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm = vmSpinUp.vmLookupByName(name);
            vmSpinUp.vmDestroy(vm);
        }
    }
}