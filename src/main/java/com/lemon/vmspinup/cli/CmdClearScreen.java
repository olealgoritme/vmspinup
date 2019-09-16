package com.lemon.vmspinup.cli;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "clear", description = "-- Clears screen")

public class CmdClearScreen implements Callable<Void> {

    @CommandLine.ParentCommand

    CliCommands parent;

    public Void call() {
        parent.reader.clearScreen();
        return null;
    }
}

