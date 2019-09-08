package com.lemon.vmspinup.cli;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.storage.VMStorageVolume;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;
import picocli.CommandLine;

import java.util.ArrayList;


@CommandLine.Command(name = "pools", mixinStandardHelpOptions = true, description = "-- Lists Storage pools")

public class CmdStoragePoolList implements Runnable {

    @CommandLine.ParentCommand
    private CliCommands parent = null;


    @Override
    public void run() {

        try {
            String[] poolList = VMSpinUp.getInstance().lvConn.listStoragePools();
            ArrayList<StoragePool> storagePools = new ArrayList<>();

            for(String pool : poolList) {
                StoragePool storagePool = VMSpinUp.getInstance().lvConn.storagePoolLookupByName(pool);
                storagePools.add(storagePool);
            }

            System.out.println(FlipTableConverters.fromIterable(storagePools, StoragePool.class));
        } catch (LibvirtException e) {
            e.printStackTrace();
        }
    }

}