package app.cmd;

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