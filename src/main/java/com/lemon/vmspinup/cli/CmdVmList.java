package com.lemon.vmspinup.cli;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

import java.util.ArrayList;

@CommandLine.Command(name = "list", aliases = {"ls"}, mixinStandardHelpOptions = true, description = "-- Lists VM Instances")

public class CmdVmList implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    @Override
    public void run() {
        ArrayList<VirtualMachine> vmList = VMSpinUp.getInstance().vmList();


        /* DomainInfo df = d.getInfo();
        parent.out.println("CPUTime = " + df.cpuTime);
        parent.out.println("state = " + df.state); */


        System.out.println(FlipTableConverters.fromIterable(vmList, VirtualMachine.class));

        parent.out.println("\nTotal instances: " + vmList.size() + "\n");
    }
}
