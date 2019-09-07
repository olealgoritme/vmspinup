package com.lemon.vmspinup.model.commands;

import com.lemon.vmspinup.model.vm.VirtualMachine;

import java.util.UUID;

public interface VirtCommands {

    /* standard libvirt/VM lookup commands */
    VirtualMachine vmLookupByUUID(UUID uuid);
    VirtualMachine vmLookupByID(int id);
    VirtualMachine vmLookupByName(String vmName);
}
