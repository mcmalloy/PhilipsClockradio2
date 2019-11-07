package dk.dtu.philipsclockradio;

public class ChannelSaveSingleton {

    private static ChannelSaveSingleton ourInstance = new ChannelSaveSingleton();

    public static ChannelSaveSingleton getInstance() {
        if(ourInstance == null) return ourInstance = new ChannelSaveSingleton();
        else return ourInstance;
    }

    private static float[] FMChannelPresets = new float[20];
    private static float[] AMChannelPresets = new float[20];


    public static float getFMChannelPresets(int i) {
        return FMChannelPresets[i];
    }

    public static void setFMChannelPresets(float frequency,int location) {
        ChannelSaveSingleton.FMChannelPresets = FMChannelPresets;
    }

    public static float getAMChannelPresets(int i) {
        return AMChannelPresets[i];
    }

    public static void setAMChannelPresets(float frequency, int location) {
        ChannelSaveSingleton.AMChannelPresets = AMChannelPresets;
    }
    public ChannelSaveSingleton() {

    }


}
