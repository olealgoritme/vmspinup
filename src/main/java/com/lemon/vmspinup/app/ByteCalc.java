package com.lemon.vmspinup.app;

public class ByteCalc {

    private static int SI = 1000;
    private long kB = 0;

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }


    public enum SIZE {
        MEGABYTE(1000 * SI),
        GIGABYTE(1000 * 1000 * SI),
        TERABYTE(1000 * 1000 * 1000 * SI);

        int bytes;
        SIZE(int bytes) {
            this.bytes = bytes;
        }
        private int getBytes() {
            return this.bytes;
        }
    }

    public ByteCalc size(long size) {
        if(size == 0) throw new IllegalArgumentException("Needs size!");
        this.kB = size;
        return ByteCalc.this;
    }

    public long to(SIZE size) {
        return (this.kB * size.getBytes());
    }

    public long from(SIZE size) {
        return (this.kB / size.getBytes());
    }
}
