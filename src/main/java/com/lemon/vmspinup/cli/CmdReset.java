package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.cli.commands.vm.VMReset;
import picocli.CommandLine;

@CommandLine.Command(name = "reset", description = "-- Resets an Object (vm, pool, storage, network, interface)",
    subcommands = {
            VMReset.class
    })

public class CmdReset implements Runnable {

        @Override
        public void run() {
            System.out.println("Missing parameter [reset vm/pool/storage/network/interface");
        }

}