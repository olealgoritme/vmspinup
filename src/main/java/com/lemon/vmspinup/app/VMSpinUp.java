package com.lemon.vmspinup.app;

import com.lemon.vmspinup.cli.commands.storage.storagepool.PoolAllCommands;
import com.lemon.vmspinup.cli.commands.storage.storagevolume.StorageAllCommands;
import com.lemon.vmspinup.cli.commands.vm.*;
import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.model.hypervisor.HyperVisorType;
import com.lemon.vmspinup.model.hypervisor.KVM;
import com.lemon.vmspinup.model.hypervisor.LXC;
import org.libvirt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.libvirt.Library.initEventLoop;

public class VMSpinUp implements VMAllCommands, StorageAllCommands, PoolAllCommands {


    private static Logger LOG = LoggerFactory.getLogger(App.class);
    private static VMSpinUp instance;
    public static HyperVisor DEFAULT_HYPERVISOR = KVM.getInstance();

    // LV
    public static Connect connect;
    private static ConnectAuth connectAuth;
    public static Domain domain;
    private Thread eventThread;

    private VMSpinUp() {

        connectAuth = new ConnectAuthDefault();
        String URIString = null;

        try {
            URIString = DEFAULT_HYPERVISOR.getUriString();
            connect = new Connect(URIString, connectAuth, 0);
            eventThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   initEventLoop();
                } catch (LibvirtException e) {
                    LOG.error("Can't start EventLoop");
                } finally {
                    LOG.info("Starting EventLoop");
                }
            }
        });

            eventThread.start();

        } catch (LibvirtException e) {
            e.printStackTrace();
            LOG.error("Can't connect to HyperVisor: " + DEFAULT_HYPERVISOR.getName() + "@ (" + URIString + ")");
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
        } catch (LibvirtException e) {
            LOG.error("Can't get the virtual daemon");
        }
        return instance;
    }


}
