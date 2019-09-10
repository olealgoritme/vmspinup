package com.lemon.vmspinup.model.commands.storage.storagevolume;

import com.lemon.vmspinup.model.storage.VMStorageVolume;
import java.util.UUID;

public interface StorageLookup {

    /* standard libvirt/volume lookup commands */
    VMStorageVolume storageVolumeLookupByUUID(UUID uuid);
    VMStorageVolume storageVolumeLookupID(int ID);
    VMStorageVolume storageVolumeLookupByName(String name);

}
