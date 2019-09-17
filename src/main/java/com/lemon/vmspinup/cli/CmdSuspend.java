package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.cli.commands.vm.VMSuspend;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

@CommandLine.Command(name = "suspend", description = "-- Suspends an object (vm, pool, storage, network, interface)",
        subcommands = {
                VMSuspend.class
        })

public class CmdSuspend implements Runnable {

    @Override
    public void run() {
        System.out.println("Missing parameter [suspend vm/pool/storage/network/interface");
    }

}
