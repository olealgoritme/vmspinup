package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "resume", description = "-- Resumes an object",
        subcommands = {
                CmdVmResume.CmdVmResumeVm.class
        })

public class CmdVmResume implements Runnable {

    @Override
    public void run() {
        System.out.println("Missing parameter [suspend vm/pool/storage/network/interface");
    }

    @CommandLine.Command(name = "vm")
    static class CmdVmResumeVm implements Runnable {
        @CommandLine.Option(names = {"--name"}, required = true)
        String name;

        @Override
        public void run() {
            VMSpinUp vmSpinUp = VMSpinUp.getInstance();
            VirtualMachine vm = vmSpinUp.vmLookupByName(name);
            vmSpinUp.vmResume(vm);
        }
    }
}
