package app;

import model.hypervisors.HyperVisor;
import model.vm.VirtualMachine;
import model.vm.templates.StandardTemplate;
import org.libvirt.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingGround {

        public static void main(String[] args) {

            SpringApplication.run(TestingGround.class, args);



            Connect conn;
            ConnectAuth ca;
            Domain d;
            try {

                ca = new ConnectAuthDefault();
                conn = new Connect("qemu:///system", ca, 0);
                NodeInfo ni = conn.nodeInfo();

                System.out.println("model: " + ni.model + " mem(kb):" + ni.memory);

              /*  String cap = conn.getCapabilities();
                System.out.println("cap: " + cap); */


                StandardTemplate template = new StandardTemplate();
                String tmp = template.buildBareMetalBoot();
                System.out.println(tmp);
                d = conn.domainCreateXML(tmp, 0);


                System.out.println("Capabilities: " + conn.getCapabilities());
                System.out.println("hostname: " + conn.getHostName());
                System.out.println("version: " + conn.getLibVersion());
                System.out.println("isSecure: " + conn.isSecure());
                System.out.println("isEncrypted: " + conn.isEncrypted());
                System.out.println("numOfDomains: " + conn.numOfDomains());
                System.out.println("active Domains: " + conn.listDomains().length);

                int[] ids = conn.listDomains();

                for (int i : ids) {
                    System.out.println("Domain name = " + d.getName() + " | ID = " + d.getID() + " | OSType = " + d.getOSType());
                    System.out.println("MaxMemory = " + d.getMaxMemory() + " | " + d.getMaxVcpus());
                    System.out.println("UUID = " + d.getUUIDString());
                    System.out.println("Active? = " + d.isActive());
                    System.out.println("Persistent? = " + d.isPersistent());

                    DomainInfo df = d.getInfo();
                    System.out.println("CPUTime = " + df.cpuTime);
                    System.out.println("MaxMem = " + df.maxMem);
                    System.out.println("memory = " + df.memory);
                    System.out.println("nrVirtCpu = " + df.nrVirtCpu);
                    System.out.println("state = " + df.state);
                }

                //d = conn.domainLookupByID(idToDelete);
                //d.destroy();

            } catch (LibvirtException le) {
                le.printStackTrace();
            }

            VirtualMachine vm = new VirtualMachine();
            vm.setHyperVisor(HyperVisor.KVM);
            vm.setName("heisann");
            vm.setvCPU(2);
            vm.setRam(2);


            //domain.create();

            //conn.close();


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
