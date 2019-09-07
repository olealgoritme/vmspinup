package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.commands.VMStart;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "start", mixinStandardHelpOptions = true, description = "-- Starts a VM Instance")
//@CommandLine.Option()

public class CmdVmStart implements Runnable, VMStart {

    @CommandLine.ParentCommand

    private CliCommands parent = null;

    @Override
    public void run() {
        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        VirtualMachine vm;
        vm = vmSpinUp.vmLookupByName("ubuntu-1");
        this.vmStart(vm);
    }

    @Override
    public void vmStart(VirtualMachine vm) {
        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        vmSpinUp.vmStart(vm);
    }
}