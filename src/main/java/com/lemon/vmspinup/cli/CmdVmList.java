package com.lemon.vmspinup.cli;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

import java.util.ArrayList;

@CommandLine.Command(name = "list",
        aliases = {"ls"},
        description = "-- Lists VM Instances")
        /*header = {
                "@|green       _ _      _             |@",
                "@|green  __ _(_) |_ __| |_ __ _ _ _  |@",
                "@|green / _` | |  _(_-<  _/ _` | '_| |@",
                "@|green \\__, |_|\\__/__/\\__\\__,_|_|   |@",
                "@|green |___/                        |@"})
*/
public class CmdVmList implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    @Override
    public void run() {

        /*parent.reader.clearScreen();

        String[] banner = new CommandLine(new CmdVmList()).getCommandSpec().usageMessage().header();
        for (String line : banner) {
            parent.out.println(CommandLine.Help.Ansi.AUTO.string(line));
        }
*/
        ArrayList<VirtualMachine> vmList = VMSpinUp.getInstance().vmList();


        /* DomainInfo df = d.getInfo();
        parent.out.println("CPUTime = " + df.cpuTime);
        parent.out.println("state = " + df.state); */
        parent.out.println(FlipTableConverters.fromIterable(vmList, VirtualMachine.class));

        parent.out.println("\nTotal instances: " + vmList.size() + "\n");
    }
}
