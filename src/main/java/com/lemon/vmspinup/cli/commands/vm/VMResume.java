package com.lemon.vmspinup.cli.commands.vm;

import com.lemon.vmspinup.app.VMSpinUp;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import picocli.CommandLine;

@CommandLine.Command(name = "vm")
public class VMResume implements Runnable {

    @CommandLine.Option(names = {"--name"}, required = true)
    private String name;

    @Override
    public void run() {
        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        Domain domain = vmSpinUp.vmLookupByName(name);
        try {
            domain.resume();
        } catch (LibvirtException e) {
            e.printStackTrace();
        }

    }
}
