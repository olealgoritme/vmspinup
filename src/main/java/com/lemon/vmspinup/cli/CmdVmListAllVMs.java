package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.commands.VMList;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;

import java.util.ArrayList;

@CommandLine.Command(name = "list", mixinStandardHelpOptions = true, description = "-- Lists VM Instances")

public class CmdVmListAllVMs implements Runnable, VMList {

    @CommandLine.ParentCommand

    private ArrayList<VirtualMachine> vmList;
    private CliCommands parent = null;

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            this.vmListAllVMs();
        }
    }

    @Override
    public void vmListAllVMs() {
        vmList = VMSpinUp.getInstance().listVMs();

        parent.out.println(String.format("%15s %3s %3s %3s %3s %3s %3s %3s %3s %3s %3s", "VM Instance", "|", "ID", "|", "OS", "|", "RAM", "|", "vCPUs", "|", "STATE"));
        parent.out.println(String.format("%s", "-----------------------------------------------------------------------"));

        for (VirtualMachine vm : vmList) {
            parent.out.println(String.format("%15s %3s %3s %3s %3s %3s %3s %2.5s %3s %3s", vm.getName(), "|", vm.getID(), "|", "OS=HVM", "|", (vm.getRamAmount() / 1024 / 1024) + " GB", "|", vm.getvCPU(), "--State--"));

                /*parent.out.println("UUID = " + d.getUUIDString());
                parent.out.println("Active = " + (d.isActive()));
                parent.out.println("Persistent = " + d.isPersistent());

                DomainInfo df = d.getInfo();
                parent.out.println("CPUTime = " + df.cpuTime);
                parent.out.println("state = " + df.state); */
        }
        parent.out.println(String.format("%s", "-----------------------------------------------------------------------"));
        parent.out.println("\nTotal instances: " + vmList.size() + "\n");
    }
}
