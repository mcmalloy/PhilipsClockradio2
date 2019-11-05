package dk.dtu.philipsclockradio;

public class StateSleep extends StateAdapter {

    static int sleepTimerCounter = 0;
    static long sleepTime;
    static long startTime;
    // Todo: shit doesnt work lol

    @Override
    public void onClick_Sleep(ContextClockradio context) {

        if(sleepTimerCounter==1){
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
            sleepTimerCounter++;
        }
    }
//
    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(""+120);
        context.ui.turnOnLED(3);
        sleepTimerCounter++;


    }
}
