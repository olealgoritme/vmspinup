package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.cli.commands.vm.VMShutdown;
import picocli.CommandLine;

@CommandLine.Command(name = "shutdown", description = "-- Shuts down an Object (vm, pool, storage, network, interface)",
        subcommands = {
                VMShutdown.class
        })

public class CmdShutdown implements Runnable {

    @Override
    public void run() {
        System.out.println("Missing parameter [suspend vm/pool/storage/network/interface");
    }
}