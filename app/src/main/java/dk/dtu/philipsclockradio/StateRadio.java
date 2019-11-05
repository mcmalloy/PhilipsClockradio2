package dk.dtu.philipsclockradio;

import java.util.concurrent.ThreadLocalRandom;

public class StateRadio extends StateAdapter {
    static double freq=90;
    static int radioToggle=0;
    // TODO: Kunne skifte mellem FM/AM radio
    @Override
    public void onClick_Power(ContextClockradio context) {
        context.setState(new StateRadio());
        if(radioToggle==0){
            freq=90;
            context.ui.setDisplayText(""+freq);
            radioToggle=1;
        }
        else if(radioToggle==1){
            freq=100.0;
            context.ui.setDisplayText(""+freq);
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
        context.ui.toggleRadioPlaying();
        context.ui.setDisplayText(""+90.0);
        // context.ui.turnOnLED(1);
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.toggleRadioPlaying();
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {

        if(freq>=87.6){
            freq-=0.1;
            context.ui.setDisplayText(""+freq);
        }
    }

    @Override
    public void onClick_Min(ContextClockradio context) {

        if(freq<=107.9){
            freq+=0.1;
            context.ui.setDisplayText(""+freq);
        }
    }

    @Override
    public void onLongClick_Hour(ContextClockradio context) {
        double rand = ThreadLocalRandom.current().nextDouble(-1.2,-0.1);

        if(freq+rand>=87.5 && freq+rand<=108.0){
            freq+=rand;
            context.ui.setDisplayText(""+freq);
        }
    }

    @Override
    public void onLongClick_Min(ContextClockradio context) {
        double rand = ThreadLocalRandom.current().nextDouble(0.1,1.2);

        if(freq+rand>=87.5 && freq+rand<=108.0){
            freq+=rand;
            context.ui.setDisplayText(""+freq);
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        // Save channel
    }

}
