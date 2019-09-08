package com.lemon.vmspinup.cli;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

import java.util.ArrayList;

@CommandLine.Command(name = "list", mixinStandardHelpOptions = true, description = "-- Lists VM Instances")

public class CmdVmList implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    @Override
    public void run() {
        ArrayList<VirtualMachine> vmList = VMSpinUp.getInstance().vmList();

        // parent.out.println(String.format("%25s %3s %5s %3s %5s %3s %5s %5s %3s %3s %3s %3s %3s", "VM Instance", "|", "ID", "|", "OS", "|", "ARCH", "|", "RAM", "|", "vCPUs", "|", "STATE"));
        // parent.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------------"));

        for (VirtualMachine vm : vmList) {
            // parent.out.println(String.format("%25s %3s %5s %3s %5s %3s %5s %5s %3s %3s %3s %3s %3s", vm.getName(), "|", vm.getID(), "|", vm.getOs(), "|", vm.getArch(), "|", (vm.getRamAmount() / 1024 / 1024) + " GB", "|", vm.getvCPU() + " cores", "|", vm.getVmState()));

                /*parent.out.println("UUID = " + d.getUUIDString());
                parent.out.println("Active = " + (d.isActive()));
                parent.out.println("Persistent = " + d.isPersistent());

                DomainInfo df = d.getInfo();
                parent.out.println("CPUTime = " + df.cpuTime);
                parent.out.println("state = " + df.state); */
        }

        // String[] headers = { "VM Instance", "ID", "OS", "ARCH", "RAM", "vCPU", "STATE"};

        System.out.println(FlipTableConverters.fromIterable(vmList, VirtualMachine.class));


        // parent.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------------"));
        parent.out.println("\nTotal instances: " + vmList.size() + "\n");
    }
}
