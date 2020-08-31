package nz.ac.vuw.swen301.a1;

/**
 * Interface for MemAppenderMBean
 * @author Caitlin
 *
 */
public interface MemAppenderMBean {
  
  /**
   * Get all the logs stored as a String array.
   * @param count = useless parameter so that mBean works
   * @return
   */
  public String[] getLogs(int count);
  
  /**
   * Get the number of logs. 
   * @param count = useless parameter so that mBean works
   * @return
   */
  public long getLogCount(int count);
  
  /**
   * Get the number of discarded logs
   * @param count = useless parameter so that mBean works
   * @return
   */
  public long getDiscardedLogCount(int count);
  
  /**
   * Export the logs to a JSON file.
   * @param filename = name of the file. 
   */
  public void exportToJSON(String filename);
}