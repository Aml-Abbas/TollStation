/**
 * ApproachHandler repeatedly waits for the sensor wire to report a
 * vehicle approaching the station, then registers the detection in
 * the TollStation logic
 */
public class ApproachHandler extends Thread {
    private TollStation logic;
    private SensorInterface sI;
    public ApproachHandler( TollStation logic, SensorInterface sI) {
        this.logic = logic;
        this.sI = sI;
    }
    public void run() {

            try {
                while( !isInterrupted()) {
                    sI.waitForCar();
                logic.carDetected();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}