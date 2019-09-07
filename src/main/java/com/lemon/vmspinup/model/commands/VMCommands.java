package com.lemon.vmspinup.model.commands;

import com.lemon.vmspinup.model.vm.VirtualMachine;

import java.util.ArrayList;

public interface VMCommands {

    /* standard libvirt/VM commands */
    boolean vmDestroy(VirtualMachine vm);
    boolean vmDefinePersistent(VirtualMachine vm);
    boolean vmCreate(VirtualMachine vm);
    boolean vmCreateFromXML(String xml);
    boolean vmShutdown(VirtualMachine vm);
    boolean vmSuspend(VirtualMachine vm);
    boolean vmResume(VirtualMachine vm);
    boolean vmStart(VirtualMachine vm);



    ArrayList<VirtualMachine> listVMs();
}
