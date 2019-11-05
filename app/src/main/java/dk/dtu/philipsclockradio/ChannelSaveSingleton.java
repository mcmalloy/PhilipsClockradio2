package dk.dtu.philipsclockradio;

public class ChannelSaveSingleton {
    static double[] FMChannelsave = new double[20];
    static double[] AMChannelSave = new double[20];
    private static final ChannelSaveSingleton ourInstance = new ChannelSaveSingleton();

    public static ChannelSaveSingleton getInstance() {


        return ourInstance;
    }

    public ChannelSaveSingleton() {

    }

    public static double[] getFMChannelsave() {
        return FMChannelsave;
    }

    public static void setFMChannelsave(double[] FMChannelsave) {
        ChannelSaveSingleton.FMChannelsave = FMChannelsave;
    }

    public static double[] getAMChannelSave() {
        return AMChannelSave;
    }

    public static void setAMChannelSave(double[] AMChannelSave) {
        ChannelSaveSingleton.AMChannelSave = AMChannelSave;
    }
}
