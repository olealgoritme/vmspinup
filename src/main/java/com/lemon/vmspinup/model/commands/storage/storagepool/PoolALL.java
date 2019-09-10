package com.lemon.vmspinup.model.commands.storage.storagepool;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.storage.VMStoragePool;
import org.libvirt.LibvirtException;

import java.util.ArrayList;
import java.util.UUID;

/**
 *     remove: destroy, then undefine
 * */

public interface PoolALL {
    VMSpinUp vmSpinUp = VMSpinUp.getInstance();

    default void poolCreate(VMStoragePool vmStoragePool) {
        String xml = vmStoragePool.toString();
        try {
            vmSpinUp.connect.storagePoolCreateXML(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

    default void poolDefineXML(VMStoragePool vmStoragePool) {
    }
    default ArrayList<VMStoragePool> poolList(VMStoragePool vmStoragePool) {
        return null;
    }
    default VMStoragePool storagePoolLookupByUUID(UUID uuid) {
        return null;
    }
    default void poolStart(VMStoragePool vmStoragePool) {
    }
    default void poolUndefine(VMStoragePool vmStoragePool) {
    }
    default VMStoragePool poolVolumeLookupID(int ID) {
        return null;
    }
    default VMStoragePool poolVolumeLookupByName(String name) {
        return null;
    }
    default void poolDestroy(VMStoragePool vmStoragePool) {
    }
}
