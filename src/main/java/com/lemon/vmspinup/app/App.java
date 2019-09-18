package com.lemon.vmspinup.app;

import com.lemon.vmspinup.cli.CliCommands;
import com.lemon.vmspinup.ncurses.NCurses;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.lemon.vmspinup.webservice.ResponseController;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan(basePackageClasses= ResponseController.class)

public class App {


    private static Logger LOG = LoggerFactory.getLogger(App.class);
    private final static String LOGO =
                    "                       ______       _               ______  \n" +
                    "                      / _____)     (_)             | ____ \\ \n" +
                    "   _     _ _  _  _   ( (____  ____  _ ____  _     _| ____) )\n" +
                    "  | |   | | ||_|| |   \\____ \\|  _ \\| |  _ \\| |   | |  ____/ \n" +
                    "   \\ \\ / /| |   | |   _____) ) |_| | | | | | |___| | |      \n" +
                    "    \\___/ |_|   |_|  (______/|  __/|_|_| |_|\\_____/|_|      \n" +
                    "                             |_|                            \n" +
                    "                                                " + "v0.1" + " by xuw";


    public static void main(String[] args) {


        // Create Storage Pools
        // TODO: define storagePools on Initial startup
        // TODO: build storagePools on Initial startup
        // TODO: start storagePools on Initial startup
        // TODO: autostart storagePools on Initial startup

        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        boolean havePools = false;
        LOG.info("Checking Storage Pools...");
        try (Stream<Path> walk = Files.walk(Paths.get(String.valueOf(App.class.getResource("/xml-templates/pool/").getFile())))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".xml")).collect(Collectors.toList());

            for (String file : result) {
                String xmlPool = new String (Files.readAllBytes(Paths.get(file)));
                StoragePool pool = vmSpinUp.connect.storagePoolDefineXML(xmlPool, 0);
                pool.build(0);
                pool.create(0);
                pool.setAutostart(1);
                // System.out.println(xmlPool);
            }
            //result.forEach(System.out::println);

        } catch (LibvirtException | IOException e) {
            havePools = true;
            LOG.error(e.getClass().getName());
        } finally {
            if (havePools)
                LOG.info("Storage Pools: OK");
             else
                LOG.info("Storage Pools: Successfully Created");
        }

        // Spring boot, disabled banner screen
        SpringApplication app = new SpringApplication(App.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setLogStartupInfo(false);
        app.setHeadless(true);
        app.run(args);

        // start CLI and display logo
        CliCommands.CommandLine(LOGO);
    }
}
