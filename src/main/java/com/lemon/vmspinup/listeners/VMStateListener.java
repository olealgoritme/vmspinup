package com.lemon.vmspinup.listeners;

import com.lemon.vmspinup.model.vm.VirtualMachine;

public interface VMStateListener {

        void onCreated(VirtualMachine vm);
        void onStarted(VirtualMachine vm);
        void onResumed(VirtualMachine vm);
        void onSuspended(VirtualMachine vm);
        void onShutdown(VirtualMachine vm);
        void onCrashed(VirtualMachine vm);
}
