package app.cmd;

import app.VMSpinUp;
import model.hypervisors.KVM;
import model.vm.VirtualMachine;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import picocli.CommandLine;

@CommandLine.Command(name = "shutdown", mixinStandardHelpOptions = true, description = "-- Shuts down a VM Instance")
//@CommandLine.Option()

public class CmdVmShutdown implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    @Override
    public void run() {

            VMSpinUp spinUp = VMSpinUp.getInstance();
            VirtualMachine vm = spinUp.vmLookupByName("ubuntu-1");
            spinUp.vmShutdown(vm);

    }
}