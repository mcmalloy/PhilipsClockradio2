package dk.dtu.philipsclockradio;

import java.util.Date;

public class StateAlarm extends StateAdapter {
    static boolean AlarmSettingMode=false;
    Date mTime;


    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        if(AlarmSettingMode=true){
            // Adjust and set alarm
        }
        else if(AlarmSettingMode=false){
            // Confirm and activate alarm

        }

    }

    @Override
    public void onEnterState(ContextClockradio context) {
        AlarmSettingMode = true; // So we know that we are setting alarm and not confirming
        context.ui.turnOnLED(2);
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        mTime = context.getTime();
        mTime.setTime(mTime.getTime()+3600000);
        context.setTime(mTime);
        context.updateDisplayTime();
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        mTime = context.getTime();
        mTime.setTime(mTime.getTime()+60000);
        context.setTime(mTime);
        context.updateDisplayTime();
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        // Activate alarm
        AlarmSaveSingleton.setAL1(context.getTime()); // Stores the alarm in my singleton
        context.setState(new StateStandby(context.getTime()));
    }
    @Override
    public void onClick_AL2(ContextClockradio context) {
        AlarmSaveSingleton.setAL2(context.getTime());
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime())); // Return to standby with current time
    }
}
