public interface BarrierControlInterface {
    /**
     * Opens the associated barrier. Non-blocking.
     */
    public void openBarrier();
    /**
     * Closes the barrier. Non-blocking.
     */
    public void closeBarrier();
    /**
     * Set the lamp of the traffic light to green. Non-blocking.
     */
    public void setLightGreen();
    /**
     * Set the lamp of the traffic light to yellow. Non-blocking.
     */
    public void setLightYellow();
    /**
     * Set the lamp of the traffic light to red. Non-blocking.
     */
    public void setLightRed();
    /**
     * Start the alarm. Non-blocking!
     */
    public void startAlarm();
    /**
     * Set the barriers and lights back to "normal". Blocking!
     */
    public void waitForManualReset();
}
