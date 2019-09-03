import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import model.hypervisors.HyperVisor;
import model.vm.VirtualMachine;
import model.vm.templates.StandardTemplate;
import org.libvirt.*;

import org.apache.log4j.Logger;

public class TestingGround {

        public static void main(String[] args) throws LibvirtException {


            Logger log;

            Connect conn = null;
            ConnectAuth ca = null;
            try {

                ca = new ConnectAuthDefault();
                conn = new Connect("qemu:///system", ca, 0);
                NodeInfo ni = conn.nodeInfo();

                System.out.println("model: " + ni.model + " mem(kb):" + ni.memory);

                String cap = conn.getCapabilities();
                System.out.println("cap: " + cap);

            } catch (LibvirtException le) {
                le.printStackTrace();
            }

            VirtualMachine vm = new VirtualMachine();
            vm.setHyperVisor(HyperVisor.KVM);
            vm.setHostname("heisann");
            vm.setvCPU(2);
            vm.setRam(2);



            StandardTemplate template = new StandardTemplate();
            String tmp = template.buildBareMetalInstallation();
            System.out.println(tmp);


            Domain domain;

            domain = conn.domainCreateXML(tmp, 0);
            //domain.create();
            conn.close();

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

        }
}
