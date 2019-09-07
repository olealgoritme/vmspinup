package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.commands.VMResume;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "resume", mixinStandardHelpOptions = true, description = "-- Resumes a VM Instance")
//@CommandLine.Option()

public class CmdVmResume implements Runnable, VMResume {

    @CommandLine.ParentCommand

    private CliCommands parent = null;

    @Override
    public void run() {
        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        VirtualMachine vm;
        vm = vmSpinUp.vmLookupByName("ubuntu-1");
        this.vmResume(vm);
    }

    @Override
    public void vmResume(VirtualMachine vm) {
        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        vmSpinUp.vmResume(vm);

    }
}