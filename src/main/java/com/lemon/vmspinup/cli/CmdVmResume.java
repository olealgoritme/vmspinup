package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "resume", mixinStandardHelpOptions = true, description = "-- Resumes a VM Instance")
//@CommandLine.Option()

public class CmdVmResume implements Runnable {

    @Override
    public void run() {

        VMSpinUp spinUp = VMSpinUp.getInstance();
        VirtualMachine vm = spinUp.vmLookupByName("ubuntu-1");
        spinUp.vmResume(vm);

    }
}