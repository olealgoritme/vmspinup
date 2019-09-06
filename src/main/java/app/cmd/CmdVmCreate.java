package app.cmd;

import app.VMSpinUp;
import model.hypervisors.HyperVisor;
import model.hypervisors.KVM;
import model.vm.VirtualMachine;
import model.vm.templates.StandardTemplate;
import org.libvirt.*;
import picocli.CommandLine;


@CommandLine.Command(name = "create", mixinStandardHelpOptions = true, description = "-- Creates a VM Instance")

public class CmdVmCreate implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    @Override
    public void run() {


            VirtualMachine vm = new VirtualMachine()
                                .withHyperVisor(KVM.getInstance())
                                .withName("ubuntu-1")
                                .withRamAmount(2097152)
                                .withCPUs(2)
                                .build();
            VMSpinUp.getInstance().vmCreate(vm);

    }
}