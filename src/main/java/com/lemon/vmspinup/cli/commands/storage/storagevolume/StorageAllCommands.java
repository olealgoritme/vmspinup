package com.lemon.vmspinup.cli.commands.storage.storagevolume;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.storage.VMStorageVolume;
import org.libvirt.LibvirtException;
import org.libvirt.StorageVol;

import java.util.ArrayList;
import java.util.UUID;

public interface StorageAllCommands {

    default void storageCreate(VMStorageVolume vmStorageVolume) {
        // TODO: create Storage (e.g copy imgcloud.img to /mnt/instances/vm/uuid-name.img
        // TODO: VMUserData object connected? need user-data for cloud-init images
    }

    default void storageDefineXML(VMStorageVolume vmStorageVolume) {
    }
    default ArrayList<VMStorageVolume> storageList(VMStorageVolume vmStorageVolume) {
        return null;
    }
    default VMStorageVolume storageVolumeLookupByUUID(UUID uuid) {
        return null;
    }

    default VMStorageVolume storageVolumeLookupByKey(String key) {
        VMStorageVolume vmStorageVolume = new VMStorageVolume();
        StorageVol libvirtVolume;
        try {
             libvirtVolume = VMSpinUp.connect.storageVolLookupByKey(key);
             vmStorageVolume.setPath(libvirtVolume.getPath());
             vmStorageVolume.setKey(libvirtVolume.getKey());
             vmStorageVolume.setName(libvirtVolume.getName());
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
        return vmStorageVolume;
    }
    default void storageSetAutoStart(VMStorageVolume vmStorageVolume, boolean autostart) {
        vmStorageVolume.setAutostart(true);
        // TODO: Not finished
        //storageVolumeLookupByKey(vmStorageVolume.getKey());
    }
    default void storageStart(VMStorageVolume vmStorageVolume) {
    }
    default void storageUndefine(VMStorageVolume vmStorageVolume) {
    }
    default VMStorageVolume storageVolumeLookupByName(String name) {
        return null;
    }
    default void storageDestroy(VMStorageVolume vmStorageVolume) {
    }
}
