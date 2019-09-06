package app;

import app.cmd.CliCommands;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import webservice.ResponseController;


@SpringBootApplication
@ComponentScan(basePackageClasses= ResponseController.class)
public class TestingGround {


        public static void main(String[] args) {

            SpringApplication.run(TestingGround.class, args);

            CliCommands.CommandLine();


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

            /*
            VirtualMachine vm = new VirtualMachine();
            vm.setHyperVisor(KVM.getInstance());
            vm.setName("heisann");
            vm.setvCPU(2); */

        }
}
