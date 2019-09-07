package com.lemon.vmspinup.cli;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.commands.VMCreate;
import com.lemon.vmspinup.model.listeners.VMStateListener;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import picocli.CommandLine;


@CommandLine.Command(name = "create", mixinStandardHelpOptions = true, description = "-- Creates a VM Instance")

public class CmdVmCreate implements Runnable, VMCreate {

    @CommandLine.ParentCommand

    private CliCommands parent = null;


    @Override
    public void run() {
            VirtualMachine vm = new VirtualMachine("ubuntu-1", 2097152, 2, null);
            vm.setVMStateListener(new VMStateListener() {
                @Override
                public void onCreated(VirtualMachine vm) {
                    System.out.println("STATE: CREATED");
                }

                @Override
                public void onStarted(VirtualMachine vm) {
                    System.out.println("STATE: STARTED");
                }

                @Override
                public void onResumed(VirtualMachine vm) {
                    System.out.println("STATE: RESUMED");
                }

                @Override
                public void onSuspended(VirtualMachine vm) {
                    System.out.println("STATE: SUSPENDED");
                }

                @Override
                public void onShutdown(VirtualMachine vm) {
                    System.out.println("STATE: SHUTDOWN");
                }

                @Override
                public void onCrashed(VirtualMachine vm) {
                    System.out.println("STATE: CRASHED");
                }
            });

            this.vmCreate(vm);
    }

    @Override
    public void vmCreate(VirtualMachine vm) {

        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        vmSpinUp.vmCreate(vm);

    }
}