package model.vm;

import model.hypervisors.HyperVisor;

public class VirtualMachine {

    private int id;                // unique ID
    private String hostname;       // hostname
    private String uuid;           // 128 bit long value (A UUID is made of up of hex digits  (4 chars each) along with 4 “-” symbols which make its length equal to 36 characters.)

    private int vCPU;              // vCPU count
    private int ram;               // delegated ram usage (GB)

    private int storage;           // storage in GB
    private String storage_path;   // path to storage image
    private String cdRom_path;     // path to CD-ROM
    private HyperVisor hyperVisor; // HyperVisor type


    public VirtualMachine() {
    }

    public String getStorage_path() {
        return storage_path;
    }

    public void setStorage_path(String storage_path) {
        this.storage_path = storage_path;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getvCPU() {
        return vCPU;
    }

    public void setvCPU(int vCPU) {
        this.vCPU = vCPU;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HyperVisor getHyperVisor() {
        return hyperVisor;
    }

    public void setHyperVisor(HyperVisor hyperVisor) {
        this.hyperVisor = hyperVisor;
    }

    public String getCdRom_path() {
        return cdRom_path;
    }

    public void setCdRom_path(String cdRom_path) {
        this.cdRom_path = cdRom_path;
    }
}
