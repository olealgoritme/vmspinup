package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "exit", aliases = { "quit" }, description = "-- Exits VmSpinUp CLI")

public class CmdExit implements Callable<Void> {

    @CommandLine.ParentCommand

    private CliCommands parent = null;

    public Void call() {
        VMSpinUp.disconnect();
        parent = null;
        System.exit(0);
        return null;
    }
}

