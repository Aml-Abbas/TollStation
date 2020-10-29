/**
 * LicensePlateIDHandler waits in the logic for a car to be detected
 * and handles the license plate identification attempt. It
 * then reports the result (if not aborted)
 */
public class LicensePlateIDHandler extends Thread {
    private TollStation logic;
    private LicensePlateIDService lPS;
    public LicensePlateIDHandler( TollStation logic,
                                  LicensePlateIDService lPS) {
        this.logic = logic;
        this.lPS = lPS;
    }
    public void run() {
        int success;

            try {
                while( !isInterrupted()) {
                logic.waitForCarDetected();
                    success = lPS.runIdentification();
                    if( success != 0)
                        logic.licensePlateIdentified( success);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}