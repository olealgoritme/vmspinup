package model.vm;

import java.util.ArrayList;

public interface VMCommands {

    /* standard libvirt/VM commands */
    boolean vmDestroy(VirtualMachine vm);
    boolean vmCreate(VirtualMachine vm);
    boolean vmShutdown(VirtualMachine vm);
    boolean vmPause(VirtualMachine vm);
    boolean vmResume(VirtualMachine vm);
    boolean vmStart(VirtualMachine vm);

    ArrayList<VirtualMachine> listVMs();
}
