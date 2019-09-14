import com.lemon.vmspinup.app.Config;
import com.lemon.vmspinup.xml.storage.Disk;
import com.lemon.vmspinup.xml.vm.*;
import com.lemon.vmspinup.xml.storage.Volume;
import com.lemon.vmspinup.xml.storage.Target;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

public class TestXmlConfig {



    private void caps() {

    }
    @Test
    public void testCaps() throws JAXBException, javax.xml.bind.JAXBException {

        Domain config = new Domain();
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
                .setType(Interface.INTERFACE_TYPE.network)
                .setSourceNetwork("default");

        config.addInterface(iFace);

        SerialConsole console = new SerialConsole();
        console.setType("pty")
                .setTargetPort("0")
                .setTargetType("serial");
        config.addConsole(console);

        Graphics graphics = new Graphics();
        graphics.setType("vnc")
                .setListen("0.0.0.0")
                .setPort("0");

        config.addGraphics(graphics);

        // marshalling
        JAXBContext context = JAXBContextFactory.createContext(new Class[]{Domain.class}, null);
        //JAXBContext jaxbContext = (JAXBContext) JAXBContext.newInstance(LibvirtConfigDomain.class);
        Marshaller jaxbMarshaller = context.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(config, System.out);

    }
}
