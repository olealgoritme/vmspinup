package com.lemon.vmspinup.cli;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.VMSpinUp;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;
import org.libvirt.StorageVol;
import picocli.CommandLine;

import java.util.ArrayList;


@CommandLine.Command(name = "volumes", mixinStandardHelpOptions = true, description = "-- Lists Storage Volumes")

public class CmdStorageVolumeList implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;


    @Override
    public void run() {

        try {
            StoragePool pool = VMSpinUp.getInstance().lvConn.storagePoolLookupByName("virtimages");
            ArrayList<StorageVol> storageVolumes = new ArrayList<>();

            String[] volumes = pool.listVolumes();

            for(String volume : volumes) {
                StorageVol storageVol = pool.storageVolLookupByName(volume);
                storageVolumes.add(storageVol);
            }

            System.out.println(FlipTableConverters.fromIterable(storageVolumes, StorageVol.class));
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

}