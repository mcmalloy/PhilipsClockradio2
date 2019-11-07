package dk.dtu.philipsclockradio;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class StateRadio extends StateAdapter {
    static float freq=90;
    static int radioToggle=0;
    private static ArrayList<Double> FMChannel = new ArrayList<>();
    private static ArrayList<Double> AMChannel = new ArrayList<>();
    // TODO: Sørg for at autotune virker ligemeget hvad, og kører et nyt random tal hvis det andet er Out-of-bounds
    @Override
    public void onClick_Power(ContextClockradio context) {
        context.setState(new StateRadio());
        if(radioToggle==0){ // FM radio
            freq=90;
            ContextClockradio.ui.setDisplayText(""+freq);
            radioToggle=1;
        }
        else if(radioToggle==1){ // AM radio
            freq=200;
            ContextClockradio.ui.setDisplayText(""+freq);
            radioToggle=0;
        }
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime())); // Return to standby with current time
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        System.out.println("STATE: "+radioToggle);
        ContextClockradio.ui.toggleRadioPlaying();

        // context.ui.turnOnLED(1);
    }


    @Override
    public void onExitState(ContextClockradio context) {
        ContextClockradio.ui.toggleRadioPlaying();
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        if(radioToggle==1){
            if(freq>=87.6){
                freq-=0.1;
                ContextClockradio.ui.setDisplayText(""+freq);
            }
        }
        else if(radioToggle==0){
            if(freq>=110){
                freq-=10;
                ContextClockradio.ui.setDisplayText(""+freq);
            }
        }

    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        if(radioToggle==1){
            if(freq<=107.9){
                freq+=0.1;
                ContextClockradio.ui.setDisplayText(""+freq);
            }
        }
        else if(radioToggle==0){
            if(freq<=1690){
                freq+=10;
                ContextClockradio.ui.setDisplayText(""+freq);
            }
        }
    }

    @Override
    public void onLongClick_Hour(ContextClockradio context) {

        if(radioToggle==1){
            double rand = ThreadLocalRandom.current().nextDouble(-2.2,-0.3);
            if(freq+rand>=87.5 && freq+rand<=108.0){
                freq+=rand;
                ContextClockradio.ui.setDisplayText(""+freq);
            }
        }
        else if(radioToggle==0){
            int rand1 = ThreadLocalRandom.current().nextInt(-60,-10);
            if(freq+rand1>=100 && freq+rand1<=1700){
                freq+=rand1;
                ContextClockradio.ui.setDisplayText(""+freq);
            }
        }
    }

    @Override
    public void onLongClick_Min(ContextClockradio context) {
        double rand = ThreadLocalRandom.current().nextDouble(0.1,2.2);

        if(freq+rand>=87.5 && freq+rand<=108.0){
            freq+=rand;
            ContextClockradio.ui.setDisplayText(""+freq);
        }
    }

    // todo: save channels correctly. This shit aint good :(
    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        // If user can cycle channels, cycle channels followed by normal preset click.
        // If not, then press preset normally to save channel.
        for(int i=0; i<20; i++){
            ContextClockradio.ui.mLongPressed.run();
            if(radioToggle==1 && FMChannel.isEmpty()!=true){
                ContextClockradio.ui.setDisplayText(""+FMChannel.get(i));
            }
            else if(radioToggle==0 && AMChannel.isEmpty()!=true){
                ContextClockradio.ui.setDisplayText(""+AMChannel.get(i));
            }
        }
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        // Save channel
        if(radioToggle==1){
            // FMChannel.add(freq);
            for(int i=0; i<20;i++){
                if(ChannelSaveSingleton.getFMChannelPresets(i)==0){
                    ChannelSaveSingleton.setFMChannelPresets(freq,i);
                }
                break;
            }
        }
        else if(radioToggle==0){
            for(int i=0; i<20;i++){
                if(ChannelSaveSingleton.getAMChannelPresets(i)==0){
                    ChannelSaveSingleton.setAMChannelPresets(freq,i);
                }
                break;
            }
        }
    }

}
