/**
 * BarrierHandler repeatedly waits in TollStation first for a car approaching,
 * then for identification or timeout
 * to occur and handles the traffic light and barrier according to specifications
 */
public class BarrierHandler extends Thread {
    private TollStation logic;
    private BarrierControlInterface bC;
    private SensorInterface sensorInterface;

    public BarrierHandler( TollStation logic, BarrierControlInterface bC, SensorInterface sI) {
        this.bC= bC;
        this.logic=logic;
        this.sensorInterface= sI;
    }

    public void run() {
        boolean success = false;

            try {
                while( !isInterrupted()) {
                logic.waitForCarDetected();
                success= logic.waitForIdentification();
                if (success){

                    bC.setLightGreen();
                    bC.openBarrier();
                    sensorInterface.waitForLightBeamBroken();
                    bC.setLightYellow();
                    sensorInterface.waitForLightBeamClear();
                    bC.setLightRed();
                    logic.reset();
                    sensorInterface.waitForBarrierPassed();
                    bC.closeBarrier();
                }
                 else {
                    bC.startAlarm();
                    bC.waitForManualReset();
                    logic.reset();
                }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}