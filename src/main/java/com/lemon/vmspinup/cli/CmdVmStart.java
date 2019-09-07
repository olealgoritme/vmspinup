package com.lemon.vmspinup.cli;

import picocli.CommandLine;

@CommandLine.Command(name = "start", mixinStandardHelpOptions = true, description = "-- Starts a VM Instance")
//@CommandLine.Option()

public class CmdVmStart implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    @Override
    public void run() {
    }
}