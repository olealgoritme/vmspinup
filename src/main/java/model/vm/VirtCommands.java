package model.vm;

import java.util.ArrayList;
import java.util.UUID;

public interface VirtCommands {

    /* standard libvirt/VM commands */
    VirtualMachine vmLookupByUUID(UUID uuid);
    VirtualMachine vmLookupByID(int id);
    VirtualMachine vmLookupByName(String vmName);
}
