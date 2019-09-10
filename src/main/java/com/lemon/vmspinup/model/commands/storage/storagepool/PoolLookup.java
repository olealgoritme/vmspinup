package com.lemon.vmspinup.model.commands.storage.storagepool;

import com.lemon.vmspinup.model.storage.VMStoragePool;
import java.util.UUID;

public interface PoolLookup {

    /* standard libvirt/pool lookup commands */
    VMStoragePool storagePoolLookupByUUID(UUID uuid);
    VMStoragePool storagePoolLookupID(int ID);
    VMStoragePool storagePoolLookupByName(String name);

}
