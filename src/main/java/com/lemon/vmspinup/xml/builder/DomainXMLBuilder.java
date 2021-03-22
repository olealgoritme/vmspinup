package com.lemon.vmspinup.xml.builder;

import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.xml.storage.Disk;
import com.lemon.vmspinup.xml.vm.*;

import static com.lemon.vmspinup.xml.vm.Features.*;

public final class DomainXMLBuilder {

        private DomainXMLBuilder() {
        }

        public static HyperVisorStep Builder() {
            return new DomainSteps();
        }

        public interface HyperVisorStep {
            NameStep withHyperVisor(HyperVisor.TYPE hyperVisor);
        }

        public interface NameStep {
            CPUStep withName(String name);
        }

        public interface CPUStep {
            MemoryStep withCPU(int vCPUs);
        }

        public interface MemoryStep {
            MemoryUnitsStep withMemory(long memory);
        }

        public interface MemoryUnitsStep {
            FeaturesStep withUnits(String memoryUnits);
        }

        public interface FeaturesStep {
            AddMoreFeaturesStep withFeature(Features.TYPE feature);
            AddDiskStep withoutFeatures();
        }

        public interface AddMoreFeaturesStep extends AddDiskStep {
            AddMoreFeaturesStep andFeature(Features.TYPE feature);
        }

        public interface AddDiskStep {
            AddMoreDisksStep withDisk(Disk disk);
        }

        public interface AddMoreDisksStep {
            AddMoreDisksStep andDisk(Disk disk);
            SerialConsoleStep withInterface(Interface.TYPE type);
        }

         public interface SerialConsoleStep {
            GraphicsStep withConsole(boolean console);
        }

        public interface GraphicsStep {
            GraphicsWebsocketStep withVNCGraphics();
            BuildStep withSpiceGraphics();
        }

        public interface GraphicsWebsocketStep {
            BuildStep enableWebsocket(boolean websocket);
        }

        public interface BuildStep {
            DomainXML build();
        }

    private static class DomainSteps implements HyperVisorStep, NameStep, CPUStep, MemoryStep, MemoryUnitsStep, FeaturesStep,
            AddMoreFeaturesStep, AddDiskStep, AddMoreDisksStep, SerialConsoleStep, GraphicsStep, GraphicsWebsocketStep, BuildStep  {

        private DomainXML domainXML;
        private Graphics graphics;

        private DomainSteps() {
            // Standard for every domain
            domainXML = new DomainXML();
            domainXML.getOs()
                    .setType("hvm")
                    .setTypeArch("x86_64")
                    .setTypeMachine("q35");
        }

        @Override
        public NameStep withHyperVisor(HyperVisor.TYPE hyperVisor) {
            domainXML.setType(hyperVisor.getType());
            return this;
        }

        @Override
        public CPUStep withName(String name) {
            domainXML.setName(name);
            return this;
        }

        @Override
        public MemoryStep withCPU(int vCPUs) {
            domainXML.setVcpu(vCPUs);
            return this;
        }

        @Override
        public MemoryUnitsStep withMemory(long memory) {
            domainXML.setMemory(memory);
            return this;
        }

        @Override
        public FeaturesStep withUnits(String memoryUnits) {
            domainXML.setMemoryUnit(memoryUnits);
            return this;
        }

        @Override
        public AddMoreFeaturesStep withFeature(TYPE feature) {
            addFeature(feature);
            return this;
        }

        @Override
        public AddMoreFeaturesStep andFeature(TYPE feature) {
            addFeature(feature);
            return this;
        }

        @Override
        public AddDiskStep withoutFeatures() {
            return this;
        }

        @Override
        public AddMoreDisksStep withDisk(Disk disk) {
            domainXML.addDisk(disk);
            return this;
        }

        @Override
        public AddMoreDisksStep andDisk(Disk disk) {
            domainXML.addDisk(disk);
            return this;
        }

        @Override
        public SerialConsoleStep withInterface(Interface.TYPE type) {

            Interface iFace = new Interface();
            iFace.setType(type.getType());

                switch (type) {
                    case NETWORK:
                        iFace.setSourceNetwork("default");
                    break;

                    case BRIDGE:
                        iFace.setSourceBridge("virbr0");
                    break;
                }
            domainXML.addInterface(iFace);
            return this;
        }

        @Override
        public GraphicsStep withConsole(boolean wantConsole) {
            if(wantConsole) {
                SerialConsole console = new SerialConsole();
                console.setType("pty")
                        .setTargetPort("0");
                domainXML.addConsole(console);
            }
            return this;
        }

        private void addFeature(TYPE feature) {
            switch(feature) {
                case ACPI:
                    domainXML.getFeatures().enableAcpi();
                    break;
                case APIC:
                    domainXML.getFeatures().enableApic();
                    break;
                case PAE:
                    domainXML.getFeatures().enablePae();
                    break;
            }
        }

        @Override
        public GraphicsWebsocketStep withVNCGraphics() {
            graphics = new Graphics();
            graphics.setListen("0.0.0.0");
            graphics.setType(Graphics.TYPE.VNC.getType());
            graphics.setPort("-1");
            return this;
        }

        @Override
        public BuildStep enableWebsocket(boolean wantWebsocket) {
            graphics.setWebsocket("-1"); // autoport on websocket
            domainXML.addGraphics(graphics);
            return this;
        }


        @Override
        public BuildStep withSpiceGraphics() {
            graphics = new Graphics();
            graphics.setListen("0.0.0.0");
            graphics.setType(Graphics.TYPE.SPICE.getType());
            graphics.setPort("-1");
            return this;
        }

        @Override
        public DomainXML build() {
            return domainXML;
        }
    }
}
