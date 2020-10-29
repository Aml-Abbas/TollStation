public interface IdentificationServiceInterface {
    /**
     * run the identification process, return status
     * 0: process was aborted
     */
    public int runIdentification();
    /**
     * abort the identification process
     */
    public void abortIDProcess();
}
