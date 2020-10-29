/**
 * Supervise one lane of traffic
 */
public class TollStation {

    boolean carDetected;
    long timer;
    boolean tPSuccess;
    int lPSuccess = LicensePlateIDService.ABORTED;
    boolean lPReported;

    TransponderIDService tS;
    LicensePlateIDService lPS;

    public TollStation( TransponderIDService tS, LicensePlateIDService lPS) {
        this.tS = tS;
        this.lPS = lPS;
    }
    /**
     * Register a vehicle’s approach and set timer
     */
    public synchronized void carDetected() throws InterruptedException {
        while (carDetected){
            wait();
        }
        carDetected=true;
        timer= System.currentTimeMillis()+ 2000;
        notifyAll();
    }
    /**
     * Wait until a car has been detected to trigger the id process
     */
    public synchronized void waitForCarDetected() throws InterruptedException {
        while (!carDetected){
            wait();
        }
    }
    /**
     * Handle successful identification by transponder unit
     */
    public synchronized void transponderUnitIdentified() {
        tPSuccess= true;
        lPS.abortIDProcess();
        notifyAll();
    }
    /**
     * Handle result of identification attempt by license plate
     */
    public synchronized void licensePlateIdentified( int success) {
        lPReported= true;
        lPSuccess= success;
        if (lPSuccess== LicensePlateIDService.LP_SUCCESS){
            tS.abortIDProcess();
        }
        notifyAll();
    }
    /**
     * Handle identification or timeout
     */
    public synchronized boolean waitForIdentification() throws InterruptedException {
        long now= System.currentTimeMillis();
        while (!tPSuccess && lPSuccess!= LicensePlateIDService.LP_SUCCESS && timer>now){
            wait(timer-now);
        }
        if( !tPSuccess) tS.abortIDProcess();
        if( !lPReported ) lPS.abortIDProcess();

        return( tPSuccess || (lPSuccess == lPS.LP_SUCCESS));
    }
    /**
     * Reset state
     */
    public synchronized void reset() {
        lPReported = false;
        tPSuccess = false;
        carDetected = false;
        notifyAll();

    }
}