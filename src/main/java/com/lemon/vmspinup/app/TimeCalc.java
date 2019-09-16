package com.lemon.vmspinup.app;

public class TimeCalc {

    public static String getReadableTime(Long nanos){

        long tempSec = nanos/(1000*1000*1000);
        long sec = tempSec % 60;
        long min = (tempSec /60) % 60;
        long hour = (tempSec /(60*60)) % 24;
        long day = (tempSec / (24*60*60)) % 24;
        return String.format("%dd %dh %dm %ds", day,hour,min,sec);

    }
}
