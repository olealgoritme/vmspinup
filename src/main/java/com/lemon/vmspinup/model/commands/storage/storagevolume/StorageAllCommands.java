package com.lemon.vmspinup.model.commands.storage.storagevolume;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.storage.VMStoragePool;
import com.lemon.vmspinup.model.storage.VMStorageVolume;
import org.libvirt.LibvirtException;

import java.util.ArrayList;
import java.util.UUID;

public interface StorageAllCommands {

    default void storageCreate(VMStorageVolume vmStorageVolume) {
        // TODO: create Storage (e.g copy imgcloud.img to /mnt/instances/vm/uuid-name.img
    }

    default void storageDefineXML(VMStorageVolume vmStorageVolume) {
    }
    default ArrayList<VMStorageVolume> storageList(VMStorageVolume vmStorageVolume) {
        return null;
    }
    default VMStorageVolume storageVolumeLookupByUUID(UUID uuid) {
        return null;
    }
    default void storageStart(VMStorageVolume vmStorageVolume) {
    }
    default void storageUndefine(VMStorageVolume vmStorageVolume) {
    }
    default VMStorageVolume storageVolumeLookupID(int ID) {
        return null;
    }
    default VMStorageVolume storageVolumeLookupByName(String name) {
        return null;
    }
    default void storageDestroy(VMStorageVolume vmStorageVolume) {
    }
}
