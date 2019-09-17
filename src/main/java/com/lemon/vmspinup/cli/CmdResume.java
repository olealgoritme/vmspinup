package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.cli.commands.vm.VMResume;
import picocli.CommandLine;

@CommandLine.Command(name = "resume", description = "-- Resumes an object (vm, pool, storage, network, interface)",
        subcommands = {
                VMResume.class
        })

public class CmdResume implements Runnable {

    @Override
    public void run() {
        System.out.println("Missing parameter [suspend vm/pool/storage/network/interface");
    }

}
