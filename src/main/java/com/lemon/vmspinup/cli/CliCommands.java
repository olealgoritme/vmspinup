package com.lemon.vmspinup.cli;

import org.jline.reader.*;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import picocli.CommandLine;
import picocli.shell.jline3.PicocliJLineCompleter;

import java.io.PrintWriter;


@CommandLine.Command(
        name = "VMSpinUp by xuw",
        description = "",
        version = "0.1",
        footer = {"", "run 'exit' to quit CLI."},
        subcommands = {
                CmdClearScreen.class,
                CmdExit.class,
                CmdVmCreate.class,
                CmdVmDestroy.class,
                CmdVmListAllVMs.class,
                CmdVmResume.class,
                CmdVmShutdown.class,
                CmdVmStart.class,
                CmdVmSuspend.class})



public class CliCommands implements Runnable {
    LineReaderImpl reader;
    PrintWriter out;

    private CliCommands() {}

    private void setReader(LineReader reader){

        this.reader = (LineReaderImpl)reader;
        out = reader.getTerminal().writer();
    }

    public void run() {
        out.println(new CommandLine(this).getUsageMessage());
    }

    public static void CommandLine() {
        try {
            // set up the completion
            CliCommands commands = new CliCommands();
            CommandLine cmd = new CommandLine(commands);
//                        cmd.setColorScheme(CommandLine.Help.defaultColorScheme(CommandLine.Help.Ansi.AUTO));

            Terminal terminal = TerminalBuilder.builder().build();
            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(new PicocliJLineCompleter(cmd.getCommandSpec()))
                    .parser(new DefaultParser())
                    .build();
            commands.setReader(reader);
            String prompt = "vmSpinUp> ";

            // start the shell and process input until the user quits with Ctrl-D
            String line;
            while (true) {
                try {
                    line = reader.readLine(prompt, null, (MaskingCallback) null, null);
                    ParsedLine pl = reader.getParser().parse(line, 0);
                    String[] arguments = pl.words().toArray(new String[0]);
                    cmd.execute(arguments);
                } catch (UserInterruptException e) {
                    // Ignore
                } catch (EndOfFileException e) {
                    return;
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}