package com.lemon.vmspinup.app;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.cli.CliCommands;
import com.lemon.vmspinup.error.VMSpinUpException;
import com.lemon.vmspinup.model.storage.VMStoragePool;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;
import org.libvirt.StorageVol;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.lemon.vmspinup.webservice.ResponseController;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan(basePackageClasses= ResponseController.class)

public class TestingGround {

    private final static String LOGO =
            "                       ______       _               ______  \n" +
                    "                      / _____)     (_)             | ____ \\ \n" +
                    "   _     _ _  _  _   ( (____  ____  _ ____  _     _| ____) )\n" +
                    "  | |   | | ||_|| |   \\____ \\|  _ \\| |  _ \\| |   | |  ____/ \n" +
                    "   \\ \\ / /| |   | |   _____) ) |_| | | | | | |___| | |      \n" +
                    "    \\___/ |_|   |_|  (______/|  __/|_|_| |_|\\_____/|_|      \n" +
                    "                             |_|                            \n" +
                    "                                                " + "v0.1" + " by xuw";

    public static void main(String[] args) throws VMSpinUpException {


        // Create Storage Pools
        // TODO: define storagePools on Initial startup
        // TODO: build storagePools on Initial startup
        // TODO: start storagePools on Initial startup
        // TODO: autostart storagePools on Initial startup
        try (Stream<Path> walk = Files.walk(Paths.get(String.valueOf(TestingGround.class.getResource("/xml-templates/pool/").getFile())))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".xml")).collect(Collectors.toList());

            for (String file : result) {
                String xmlPool = Files.readString(Paths.get(file));
                StoragePool pool = VMSpinUp.getInstance().connect.storagePoolDefineXML(xmlPool, 0);
                pool.setAutostart(1);
                pool.build(0);
                pool.create(0);
                // System.out.println(xmlPool);
            }
            //result.forEach(System.out::println);

        } catch (IOException | LibvirtException e) {
            throw new VMSpinUpException("Couldn't create Storage Pools. Either you already have pools initialized, or check your permissions.");
        }


    // Spring boot, disabled banner screen
            SpringApplication app = new SpringApplication(TestingGround.class);
            app.setBannerMode(Banner.Mode.OFF);
            app.setLogStartupInfo(false);
            app.run(args);

            System.out.println(LOGO);

            CliCommands.CommandLine();

        }
}
