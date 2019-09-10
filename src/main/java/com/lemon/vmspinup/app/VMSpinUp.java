package com.lemon.vmspinup.app;

import com.lemon.vmspinup.error.VMSpinUpException;
import com.lemon.vmspinup.model.commands.storage.storagevolume.StorageAllCommands;
import com.lemon.vmspinup.model.commands.vm.*;
import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.model.hypervisor.HyperVisorType;
import com.lemon.vmspinup.model.hypervisor.KVM;
import com.lemon.vmspinup.model.hypervisor.LXC;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.*;

import static org.libvirt.Library.initEventLoop;

public class VMSpinUp implements VMAllCommands, StorageAllCommands {
        //PoolCreate, PoolDefineXML, PoolDestroy, PoolList, PoolLookup, PoolStart, PoolUndefine,
        //StorageCreate, StorageDefineXML, StorageDestroy, StorageList, StorageLookup, StorageStart, StorageUndefine {

    private static VMSpinUp instance;
    public static HyperVisor DEFAULT_HYPERVISOR = KVM.getInstance();
    private static VirtualMachine vm;

    // LV
    public static Connect connect;
    private static ConnectAuth connectAuth;
    public static Domain domain;
    private Thread eventThread;

    private VMSpinUp() throws VMSpinUpException {

        connectAuth = new ConnectAuthDefault();
        try {
            connect = new Connect(DEFAULT_HYPERVISOR.getUriString(), connectAuth, 0);
            eventThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   initEventLoop();
                } catch (LibvirtException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("starting event loop");
                }
            }
        });

            eventThread.start();

        } catch (LibvirtException e) {
            e.printStackTrace();
            throw new VMSpinUpException("Can't connect to HyperVisor" + DEFAULT_HYPERVISOR.getName());
        }

    }

    public static HyperVisor getHyperVisor() {
        return DEFAULT_HYPERVISOR;
    }

    private static void reset() {
        // reset
        try {
            connect.close();
        } catch (LibvirtException e) {
            e.printStackTrace();
            connect = null;
            connectAuth = null;
            instance = null;
        }

    }
    public static void disconnect() {
        try {
            if(connect != null && connectAuth != null && connect.isConnected()) {
                reset();
            }
        } catch (LibvirtException e) {
            e.printStackTrace();
            reset();
        }
    }

    public static void setHyperVisor(HyperVisorType type) {
        switch (type) {
            case KVM:
                DEFAULT_HYPERVISOR = KVM.getInstance();
            case LXC:
                DEFAULT_HYPERVISOR = LXC.getInstance();
                break;
            default:
                DEFAULT_HYPERVISOR = KVM.getInstance();
        }
    }

    public static VMSpinUp getInstance() {
        try {
            if(instance == null || connect == null || !connect.isConnected())
                instance = new VMSpinUp();
        } catch (LibvirtException | VMSpinUpException e) {
            e.printStackTrace();
            try {
                instance = new VMSpinUp();
            } catch (VMSpinUpException ex) {
                ex.printStackTrace();
            }
        }
        return instance;
    }


}
