import com.lemon.vmspinup.xml.domain.*;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class TestXmlConfig {



    private void caps() {

    }
    @Test
    public void testCaps() throws JAXBException, javax.xml.bind.JAXBException {

        LibvirtConfigDomain config = new LibvirtConfigDomain();
        config.setType("kvm")
          .setName("debian1");

        config.getOs()
                .setType("hvm")
                .setTypeArch("x86_64")
                .setTypeMachine("pc-0.12")
                .setBootDev("hd");

        config.getFeatures()
                .enableAcpi()
                .enableApic();

        LibvirtConfigDisk disk = new LibvirtConfigDisk();
        disk.setDriverName("qemu")
                .setDriverType("qcow2")
                .setSourceFile("/home/test.img")
                .setTargetBus("virtio")
                .setTargetDev("vda");
        config.addDisk(disk);

        LibvirtConfigInterface interf = new LibvirtConfigInterface();
        interf.setType("bridge")
                .setMacAddress("52:54:00:83:25:2b")
                .setSourceBridge("virbr0");
        config.addInterface(interf);

        LibvirtConfigSerialConsole serial = new LibvirtConfigSerialConsole();
        serial.setType("pty")
                .setTargetPort("0");
        config.addSerial(serial);

        LibvirtConfigSerialConsole console = new LibvirtConfigSerialConsole();
        console.setType("pty")
                .setTargetPort("0")
                .setTargetType("serial");
        config.addConsole(console);

        LibvirtConfigGraphics graphics = new LibvirtConfigGraphics();
        graphics.setType("vnc")
                .setListen("0.0.0.0")
                .setPort("5900");

        config.addGraphics(graphics);

        // marshalling
        JAXBContext jaxbContext = (JAXBContext) JAXBContext.newInstance(LibvirtConfigDomain.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(config, System.out);

    }
}
