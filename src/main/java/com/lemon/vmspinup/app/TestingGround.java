package com.lemon.vmspinup.app;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.cli.CliCommands;
import com.lemon.vmspinup.model.storage.VMStoragePool;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;
import org.libvirt.StorageVol;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.lemon.vmspinup.webservice.ResponseController;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.libvirt.Library.runEventLoop;


@SpringBootApplication
@ComponentScan(basePackageClasses= ResponseController.class)
public class TestingGround {


        public static void main(String[] args) {

            // Spring boot, disabled banner screen
            SpringApplication app = new SpringApplication(TestingGround.class);
            app.setBannerMode(Banner.Mode.OFF);
            app.setLogStartupInfo(false);
            app.run(args);



            System.out.println(
                    "                       ______       _               ______  \n" +
                    "                      / _____)     (_)             | ____ \\ \n" +
                    "   _     _ _  _  _   ( (____  ____  _ ____  _     _| ____) )\n" +
                    "  | |   | | ||_|| |   \\____ \\|  _ \\| |  _ \\| |   | |  ____/ \n" +
                    "   \\ \\ / /| |   | |   _____) ) |_| | | | | | |___| | |      \n" +
                    "    \\___/ |_|   |_|  (______/|  __/|_|_| |_|\\_____/|_|      \n" +
                    "                             |_|                            \n" +
                    "                                                " + "v0.1" + " by xuw");


            CliCommands.CommandLine();

            /*
            QNameMap qmap = new QNameMap();
            qmap.setDefaultNamespace("http://libvirt.org/schemas/domain/qemu/1.0");
            qmap.setDefaultPrefix("qemu");
            StaxDriver staxDriver = new StaxDriver(qmap);
            XStream xstream = new XStream(staxDriver);
            xstream.alias("domain", Domain.class);

            Domain d = new Domain("kvm","linux");
            String xml = xstream.toXML(d);
             */

            /*
            VirtualMachine vm = new VirtualMachine();
            vm.setHyperVisor(KVM.getInstance());
            vm.setName("heisann");
            vm.setvCPU(2); */

        }
}
