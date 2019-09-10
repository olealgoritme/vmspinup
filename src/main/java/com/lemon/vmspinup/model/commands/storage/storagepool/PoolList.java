package com.lemon.vmspinup.model.commands.storage.storagepool;

import com.lemon.vmspinup.model.storage.VMStoragePool;
import java.util.ArrayList;

public interface PoolList {
    ArrayList<VMStoragePool> poolList(VMStoragePool vmStoragePool);
}
