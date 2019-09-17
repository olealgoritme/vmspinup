package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.cli.commands.vm.VMDestroy;
import picocli.CommandLine;

@CommandLine.Command(name = "destroy", description = "-- Destroys an Object (vm, pool, storage, network, interface)",
        subcommands = {
                VMDestroy.class
        })

public class CmdDestroy implements Runnable {

    @Override
    public void run() {
        System.out.println("Missing parameter [destroy vm/pool/storage/network/interface");
    }

}