package app.cmd;

import app.VMSpinUp;
import model.hypervisors.KVM;
import model.vm.VirtualMachine;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import picocli.CommandLine;

import java.util.ArrayList;

@CommandLine.Command(name = "list", mixinStandardHelpOptions = true, description = "-- Lists VM Instances")

public class CmdVmList implements Runnable {


        @CommandLine.ParentCommand
        private CliCommands parent = null;

        public void run() {

            parent.out.println();

            ArrayList<VirtualMachine> vmList;
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
