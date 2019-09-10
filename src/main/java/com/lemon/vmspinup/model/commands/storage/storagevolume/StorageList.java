package com.lemon.vmspinup.model.commands.storage.storagevolume;

import com.lemon.vmspinup.model.storage.VMStoragePool;
import java.util.ArrayList;

public interface StorageList {
    ArrayList<VMStoragePool> storageList(VMStoragePool vmStoragePool);
}
