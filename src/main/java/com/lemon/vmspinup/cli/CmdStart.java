package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.cli.commands.vm.VMStart;
import picocli.CommandLine;

@CommandLine.Command(name = "start", description = "-- Starts an Object (vm, pool, storage, network, interface)",
    subcommands = {
            VMStart.class
    })

public class CmdStart implements Runnable {

        @Override
        public void run() {
            System.out.println("Missing parameter [start vm/pool/storage/network/interface");
        }

}