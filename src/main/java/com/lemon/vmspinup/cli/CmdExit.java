package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import picocli.CommandLine;
import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "exit", mixinStandardHelpOptions = true, description = "-- Exits VmSpinUp CLI")

public class CmdExit implements Callable<Void> {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    public Void call() throws IOException {
        VMSpinUp.disconnect();
        parent = null;
        System.exit(0);
        return null;
    }
}

