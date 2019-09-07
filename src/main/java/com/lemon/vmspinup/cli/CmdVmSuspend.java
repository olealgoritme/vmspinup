package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "suspend", mixinStandardHelpOptions = true, description = "-- Suspends a VM Instance")
//@CommandLine.Option()

public class CmdVmSuspend implements Runnable {

    @Override
    public void run() {

        VMSpinUp spinUp = VMSpinUp.getInstance();
        VirtualMachine vm = spinUp.vmLookupByName("ubuntu-1");
        spinUp.vmSuspend(vm);

    }
}