public interface SensorInterface {

    /**
     * Blocks the calling thread until a car
     * triggers the approach sensor
     */
    public void waitForCar();
    /**
     * Blocks the calling thread until the light beam at the stop line
     * is interrupted
     */
    public void waitForLightBeamBroken();
    /**
     * Blocks the calling thread until the broken light beam at the stop line
     * becomes clear ("closed") again
     */
    public void waitForLightBeamClear();
    /**
     * Blocks the calling thread until the passage of a vehicle has been detected
     * (a light beam has been broken and cleared again)
     */
    public void waitForBarrierPassed();

}

