package com.lemon.vmspinup.model.vm;

import com.lemon.vmspinup.model.listeners.VMStateListener;

public class VirtualMachineCallback implements VMStateListener {

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
}
