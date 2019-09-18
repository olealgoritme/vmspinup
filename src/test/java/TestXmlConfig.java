import com.lemon.vmspinup.app.Config;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.exception.VMSpinUpException;
import com.lemon.vmspinup.xml.storage.Disk;
import com.lemon.vmspinup.xml.vm.*;
import com.lemon.vmspinup.xml.storage.Volume;
import com.lemon.vmspinup.xml.storage.Target;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TestXmlConfig {




    public void caps() throws VMSpinUpException {

        // Create Storage Pools
        // TODO: define storagePools on Initial startup
        // TODO: build storagePools on Initial startup
        // TODO: start storagePools on Initial startup
        // TODO: autostart storagePools on Initial startup
//        getClass().getResource("/textfiles/myfile.txt")
        //System.out.println("File exists: " + new File(String.valueOf(getClass().getResource("/xml-templates/pool/").getFile())).exists());

        try (Stream<Path> walk = Files.walk(Paths.get(String.valueOf(getClass().getResource("/xml-templates/pool/").getFile())))){

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".xml")).collect(Collectors.toList());

            for (String file : result) {
                String xmlPool = new String(Files.readAllBytes(Paths.get(file)));
                StoragePool pool = VMSpinUp.getInstance().connect.storagePoolDefineXML(xmlPool, 0);
                pool.setAutostart(1);
                pool.build(0);
                pool.create(0);
               // System.out.println(xmlPool);
            }
            //result.forEach(System.out::println);


        } catch (IOException | LibvirtException e) {
                //throw new VMSpinUpException("Couldn't create Storage Pools. Either you already have pools initialized, or check your permissions.");
        }
    }

    public void testCaps() throws JAXBException, javax.xml.bind.JAXBException {

        DomainXML config = new DomainXML();
        config.setType("kvm")
                .setName("debian1")
                .setMemory(24242424)
                .setMemoryUnit("bytes")
                .setVcpu(2);

        config.getOs()
                .setType("hvm")
                .setTypeArch("x86_64")
                .setTypeMachine("pc-0.12")
                .setBootDev("hd");

        config.getFeatures()
                .enableAcpi()
                .enableApic();


        Volume volume = new Volume();
        volume.setAllocation(10)
                .setAllocationUnit("G")
                .setCapacity(10)
                .setCapacityUnit("G")
                .setTarget(new Target().setPath(Config.VM_INSTANCES_PATH + "/" + "fucku.img"));


        Disk disk = new Disk();
        disk.setDriverName("qemu")
                .setDriverType("qcow2")
                .setSourceFile("/home/test.img")
                .setTargetBus("virtio")
                .setTargetDev("vda");
        config.addDisk(disk);

        disk = new Disk();
        disk.setDriverName("qemu")
                .setDriverType("raw")
                .setSourceFile("/home/test.img")
                .setTargetBus("virtio")
                .setTargetDev("vdb");
        config.addDisk(disk);

        Interface iFace = new Interface();
            iFace
                .setType(Interface.TYPE.NETWORK.getType())
                .setSourceNetwork("default");

        config.addInterface(iFace);

        SerialConsole console = new SerialConsole();
        console.setType("pty")
                .setTargetPort("0")
                .setTargetType("serial");
        config.addConsole(console);

        Graphics graphics = new Graphics();
        graphics.setType(Graphics.TYPE.VNC.getType())
                .setListen("0.0.0.0")
                .setPort("0");

        config.addGraphics(graphics);

        // marshalling
        JAXBContext context = JAXBContextFactory.createContext(new Class[]{DomainXML.class}, null);
        //JAXBContext jaxbContext = (JAXBContext) JAXBContext.newInstance(LibvirtConfigDomain.class);
        Marshaller jaxbMarshaller = context.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(config, System.out);

    }
}
