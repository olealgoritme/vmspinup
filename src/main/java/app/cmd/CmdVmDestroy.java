package app.cmd;

import app.VMSpinUp;
import model.vm.VirtualMachine;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import picocli.CommandLine;

@CommandLine.Command(name = "destroy", mixinStandardHelpOptions = true, description = "-- Destroys a VM Instance")
//@CommandLine.Option()

public class CmdVmDestroy implements Runnable {

    @Override
    public void run() {

        VMSpinUp spinUp = VMSpinUp.getInstance();
        VirtualMachine vm = spinUp.vmLookupByName("ubuntu-1");
        spinUp.vmDestroy(vm);

    }
}