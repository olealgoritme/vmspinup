package com.lemon.vmspinup.cli.commands.storage.storagepool;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.storage.VMStoragePool;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;

import java.util.ArrayList;
import java.util.UUID;

/**
 *     create: define, start, autostart
 *     remove: destroy, then undefine
 * */

public interface PoolAllCommands {

    default void poolCreate(VMStoragePool vmStoragePool) {
        String xml = vmStoragePool.toString();
        try {
            VMSpinUp.connect.storagePoolCreateXML(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

    default void poolCreateVolume(String poolName, String xml) {

        try {
            StoragePool pool = VMSpinUp.connect.storagePoolLookupByName(poolName);
            pool.storageVolCreateXML(xml, 0);
        } catch (LibvirtException e) {
            e.printStackTrace();
        }

    }

    default void poolDefineXML(VMStoragePool vmStoragePool) {
    }
    default ArrayList<VMStoragePool> poolList(ArrayList<VMStoragePool> vmStoragePool) {
        String[] pools = null;
        ArrayList<VMStoragePool> poolList = new ArrayList<>();
        VMStoragePool storagePool = new VMStoragePool();
        try {
            pools = VMSpinUp.connect.listStoragePools();
           for(String pool : pools) {
               VMSpinUp.connect.storagePoolLookupByName(pool);

           }
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
        return poolList;
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
    default VMStoragePool poolLookupByName(String name) {
        StoragePool pool = null;
        VMStoragePool vmStoragePool = null;
        try {
            pool = VMSpinUp.connect.storagePoolLookupByName(name);
            vmStoragePool = new VMStoragePool();
            vmStoragePool.setName(pool.getName());
            vmStoragePool.setUuid(UUID.fromString(pool.getUUIDString()));
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
        return vmStoragePool;
    }
    default void poolDestroy(VMStoragePool vmStoragePool) {
    }
}
