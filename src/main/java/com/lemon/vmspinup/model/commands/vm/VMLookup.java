package com.lemon.vmspinup.model.commands.vm;

import com.lemon.vmspinup.model.vm.VirtualMachine;

import java.util.ArrayList;
import java.util.UUID;

public interface VMLookup {

    default void vmCreate(VirtualMachine vm) {
    }
    default void vmCreateFromXML(String xml) {
    }
    default void vmDefine(VirtualMachine vm) {
    }
    default void vmDestroy(VirtualMachine vm) {
    }
    default void vmResume(VirtualMachine vm) {
    }
    default void vmShutdown(VirtualMachine vm) {
    }
    default void vmStart(VirtualMachine vm) {
    }
    default void vmSuspend(VirtualMachine vm) {
    }
    default void vmUndefine(VirtualMachine vm) {
    }
    default ArrayList<VirtualMachine> vmList() {
        return null;
    }
    default VirtualMachine vmLookupByUUID(UUID uuid) {
        return null;
    }
    default VirtualMachine vmLookupByID(int id) {
        return null;
    }
    default VirtualMachine vmLookupByName(String vmName) {
        return null;
    }

}
