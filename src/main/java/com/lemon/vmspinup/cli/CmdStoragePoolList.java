package com.lemon.vmspinup.cli;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.VMSpinUp;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;
import picocli.CommandLine;

import java.util.ArrayList;


@CommandLine.Command(name = "pools", description = "-- Lists Storage pools")

public class CmdStoragePoolList implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;


    @Override
    public void run() {

        try {
            String[] poolList = VMSpinUp.connect.listStoragePools();
            ArrayList<StoragePool> storagePools = new ArrayList<>();

            for(String pool : poolList) {
                StoragePool storagePool = VMSpinUp.connect.storagePoolLookupByName(pool);
                storagePools.add(storagePool);
            }

            System.out.println(FlipTableConverters.fromIterable(storagePools, StoragePool.class));
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

}