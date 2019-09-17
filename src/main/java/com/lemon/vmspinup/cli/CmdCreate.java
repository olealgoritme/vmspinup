package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.cli.commands.vm.VMCreate;
import picocli.CommandLine;


@CommandLine.Command(name = "create", description = "-- Creates an Object (vm, pool, storage, network, interface)",
        subcommands = {
            VMCreate.class
        })

public class CmdCreate implements Runnable {

    @Override
    public void run() {
        System.out.println("Missing parameter [create vm/pool/storage/network/interface");
    }


}
