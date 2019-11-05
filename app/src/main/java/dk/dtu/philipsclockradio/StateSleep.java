package dk.dtu.philipsclockradio;

import android.os.Handler;

import java.util.Date;

public class StateSleep extends StateAdapter {

    static int sleepTimerCounter = 0;
    private Date mTime;
    private ContextClockradio mContext;
    private static Handler mHandler = new Handler();

    // Todo: shit doesnt work lol

    @Override
    public void onClick_Sleep(ContextClockradio context) {

        if(sleepTimerCounter==0){
            context.ui.setDisplayText(""+120);
            sleepTimerCounter++;
        }
        else if(sleepTimerCounter==1){
            context.ui.setDisplayText(""+90);
            sleepTimerCounter++;
        }
        else if(sleepTimerCounter==2){
            context.ui.setDisplayText(""+60);
            sleepTimerCounter++;
        }
        else if(sleepTimerCounter==3){
            context.ui.setDisplayText(""+30);
            sleepTimerCounter++;
        }
        else if(sleepTimerCounter==4){
            context.ui.setDisplayText(""+15);
            sleepTimerCounter=0;
        }
        else{
            context.setState(new StateStandby(context.getTime())); // Go back to standby if >5sec
        }
    }
//
    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(""+120);
        context.ui.turnOnLED(3);
        sleepTimerCounter++;
    }

    Runnable trackTime = new Runnable() {
        @Override
        public void run() {
            mContext.setState(new StateStandby(mTime));
        }
    };

    void startTime(){
        mHandler.postDelayed(trackTime,5000);
    }

    void stopTime(){
        mHandler.removeCallbacks(trackTime);
    }

}
