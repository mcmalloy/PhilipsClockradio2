package dk.dtu.philipsclockradio;

import android.os.Handler;

import java.util.Date;

public class StateSleep extends StateAdapter {
    private ContextClockradio mContext;
    static int sleepTimerCounter = 0;
    private Date mTime;
    private static Handler mHandler = new Handler();
    private long previousClick;
    static int[] sleepTimer = {120,90,60,30,15};
    // Todo: shit doesnt work lol


    @Override
    public void onClick_Sleep(ContextClockradio context) {
        stopClock();
        System.out.println("SLEEP COUNTER: "+sleepTimerCounter);
            if(sleepTimerCounter>=0 && sleepTimerCounter<5){
                sleepTimerCounter++;
                context.ui.setDisplayText(""+sleepTimer[sleepTimerCounter]);
                if(sleepTimerCounter==5){
                    sleepTimerCounter=0; // reset to cycle time
                }
            }
            else{

            }
            startClock();
    }
//

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(""+120);
        context.ui.turnOnLED(3);
        sleepTimerCounter=0;
        mContext = context;
        startClock();
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffLED(3);
    }


    Runnable mSetTime = new Runnable() {
        @Override
        public void run() {
            mContext.setState(new StateStandby(mContext.getTime())); // Return to standby with current time
        }
    };
    void startClock() {
        mHandler.postDelayed(mSetTime,5000);
    }
    void stopClock() {
        mHandler.removeCallbacks(mSetTime);
    }
}
