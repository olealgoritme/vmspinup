package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.storage.VMStorageVolume;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;


@CommandLine.Command(name = "reboot", mixinStandardHelpOptions = true, description = "-- Reboots a VM Instance")

public class CmdVmReboot implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;


    @Override
    public void run() {
           VMSpinUp vmSpinUp = VMSpinUp.getInstance();
           vmSpinUp.vmReboot(vmSpinUp.vmLookupByName("ubuntu5"));
    }
}