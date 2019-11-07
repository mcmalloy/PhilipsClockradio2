package dk.dtu.philipsclockradio;

import java.util.Date;

public class AlarmSaveSingleton {
    private static AlarmSaveSingleton ourInstance = new AlarmSaveSingleton();

    public static AlarmSaveSingleton getInstance() {
        if(ourInstance == null) return ourInstance = new AlarmSaveSingleton();
        else return ourInstance;
    }

    private static Date AL1;
    private static Date AL2;

    public static Date getAL1() {
        return AL1;
    }

    public static void setAL1(Date AL1) {
        AlarmSaveSingleton.AL1 = AL1;
    }

    public static Date getAL2() {
        return AL2;
    }

    public static void setAL2(Date AL2) {
        AlarmSaveSingleton.AL2 = AL2;
    }





    AlarmSaveSingleton() {

    }
}
