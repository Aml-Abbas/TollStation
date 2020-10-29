/**
 * TransponderIDHandler waits in the logic for a car to be detected
 * and handles the transponder unit identification attempt. It
 * then reports the result (if not aborted)
 */
public class TransponderIDHandler extends Thread {
    private TollStation logic;
    private TransponderIDService tS;
    public TransponderIDHandler( TollStation logic,
                                 TransponderIDService tS) {
        this.logic= logic;
        this.tS = tS;
    }
    public void run() {
        int success;

            try {
                while( !isInterrupted()) {
                logic.waitForCarDetected();
                    success = tS.runIdentification();
                    if( success != 0)
                        logic.transponderUnitIdentified();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}