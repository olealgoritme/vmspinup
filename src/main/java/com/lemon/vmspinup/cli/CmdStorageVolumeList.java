package com.lemon.vmspinup.cli;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.VMSpinUp;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;
import org.libvirt.StorageVol;
import picocli.CommandLine;

import java.util.ArrayList;


@CommandLine.Command(name = "volumes", description = "-- Lists Storage Volumes")

public class CmdStorageVolumeList implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    private VMSpinUp vmSpinUp = VMSpinUp.getInstance();

    @Override
    public void run() {

        try {
            String[] poolList = vmSpinUp.connect.listStoragePools();
            ArrayList<StorageVol> storageVolumes = new ArrayList<>();

            for(String pool : poolList) {
                StoragePool storagePool = vmSpinUp.connect.storagePoolLookupByName(pool);
                String[] volumes = storagePool.listVolumes();

                    for(String volume : volumes) {
                        StorageVol storageVol = storagePool.storageVolLookupByName(volume);
                        storageVolumes.add(storageVol);
                    }
            }

            System.out.println(FlipTableConverters.fromIterable(storageVolumes, StorageVol.class));
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

}