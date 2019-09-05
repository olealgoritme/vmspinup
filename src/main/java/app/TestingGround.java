package app;

import model.hypervisors.HyperVisor;
import model.vm.VirtualMachine;
import model.vm.templates.StandardTemplate;
import org.jline.reader.*;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.libvirt.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.shell.jline3.PicocliJLineCompleter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TestingGround {

    private static Connect conn;
    private static ConnectAuth ca;
    private static Domain d;

    /**
     * Top-level command that just prints help.
     */
    @CommandLine.Command(name = "VMSpinUp by xuw", description = "",
            footer = {"", "Press Ctrl-D to exit."},
            subcommands = {MyCommand.class, ClearScreen.class})

    static class CliCommands implements Runnable {
        LineReaderImpl reader;
        PrintWriter out;

        CliCommands() {}

        public void setReader(LineReader reader){
            this.reader = (LineReaderImpl)reader;
            out = reader.getTerminal().writer();
        }

        public void run() {
            out.println(new CommandLine(this).getUsageMessage());
        }
    }

    /**
     * A command with some options to demonstrate completion.
     */
    @CommandLine.Command(name = "vmlist", mixinStandardHelpOptions = true, version = "1.0",
            description = "")

    static class MyCommand implements Runnable {

        @CommandLine.ParentCommand
        CliCommands parent;

        public void run() {

                parent.out.println();

            int[] ids = new int[0];
            try {
                ids = conn.listDomains();

                parent.out.println(String.format("%15s %3s %3s %3s %3s %3s %3s %3s %3s", "VM Instance", "|", "ID", "|", "OS", "|", "RAM", "|", "vCPUs"));
                parent.out.println(String.format("%s", "-----------------------------------------------------------------------"));

            for (int i : ids) {
                parent.out.println(String.format("%15s %3s %3s %3s %3s %3s %3s %2.5s %3s", d.getName(), "|", d.getID(), "|", d.getOSType(), "|", (d.getMaxMemory() / 1024 / 1024) + " GB", "|", d.getMaxVcpus()));

                /*parent.out.println("UUID = " + d.getUUIDString());
                parent.out.println("Active = " + (d.isActive()));
                parent.out.println("Persistent = " + d.isPersistent());

                DomainInfo df = d.getInfo();
                parent.out.println("CPUTime = " + df.cpuTime);
                parent.out.println("state = " + df.state); */
            }
                parent.out.println(String.format("%s", "-----------------------------------------------------------------------"));
                parent.out.println("\nTotal instances: " + ids.length + "\n");


            } catch (LibvirtException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Command that clears the screen.
     */
    @CommandLine.Command(name = "clear", aliases = "cls", mixinStandardHelpOptions = true,
            description = "-- Clearing screen", version = "0.0")
    static class ClearScreen implements Callable<Void> {

        @CommandLine.ParentCommand
        CliCommands parent;

        public Void call() throws IOException {
            parent.reader.clearScreen();
            return null;
        }
    }

        public static void CommandLine() {
            try {
                // set up the completion
                CliCommands commands = new CliCommands();
                CommandLine cmd = new CommandLine(commands);
                Terminal terminal = TerminalBuilder.builder().build();
                LineReader reader = LineReaderBuilder.builder()
                        .terminal(terminal)
                        .completer(new PicocliJLineCompleter(cmd.getCommandSpec()))
                        .parser(new DefaultParser())
                        .build();
                commands.setReader(reader);
                String prompt = "vmSpinUp> ";
                String rightPrompt = null;

                // start the shell and process input until the user quits with Ctrl-D
                String line;
                while (true) {
                    try {
                        line = reader.readLine(prompt, rightPrompt, (MaskingCallback) null, null);
                        ParsedLine pl = reader.getParser().parse(line, 0);
                        String[] arguments = pl.words().toArray(new String[0]);
                        CommandLine.run(commands, arguments);
                    } catch (UserInterruptException e) {
                        // Ignore
                    } catch (EndOfFileException e) {
                        return;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        public static void main(String[] args) {

            SpringApplication.run(TestingGround.class, args);

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


            CommandLine();

        }
}