package com.lemon.vmspinup.model.storage;

public class StorageVolume {

    private String path;


    public StorageVolume(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}