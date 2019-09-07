package com.lemon.vmspinup.model.listeners;

import com.lemon.vmspinup.model.vm.VirtualMachine;

public interface VMStateListener {

        void onCreated(VirtualMachine vm);
        void onStarting(VirtualMachine vm);
        void onRunning(VirtualMachine vm);
        void onSuspended(VirtualMachine vm);
        void onShuttingDown(VirtualMachine vm);
        void onShutdown(VirtualMachine vm);

}
