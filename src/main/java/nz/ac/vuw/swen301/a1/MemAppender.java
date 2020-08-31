package nz.ac.vuw.swen301.a1;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.PatternLayout;
import org.json.JSONObject;

/** 
 * MemAppender Class
 * @author Caitlin
 *
 */
public class MemAppender implements MemAppenderMBean{
  private static String name = "nameString";
  private List<LoggingEvent> loggingEvents= new ArrayList();
  private long maxSize = 1000;
  private long discardedLogs =0;
  
  public MemAppender() {
    int count = loggingEvents.size();
    name =  Integer.toString(count);
  }
  
  /**
   * Get the name 
   * @return
   */
  public String getName() {
    return "nz.ac.vuw.ecs.swen301.a1:type=MemAppender, name=" +name;
  }
  
  /**
   * Set the name
   * @param newName
   */
  public void setName(String newName) {
    name = newName;
  }
  
  /**
   * Get the Max size, max amount of logs that can be stored
   * @return
   */
  public long getMaxSize() {
    return maxSize;
  }
  
  /**
   * Set the max size, max amount of logs that can be stored
   * @param newSize
   */
  public void setMaxSize(int newSize) {
    maxSize = newSize;
  }
  
  /**
   * Get a List of the current logs as an unmodifiable list
   * @return
   */
  public List<LoggingEvent> getCurrentLogs() {
    return Collections.unmodifiableList(loggingEvents);
  }
  
  /**
   * Add a new LoggingEvent to the List
   * If it is going to be larger than the max size, then discard the earliest one. 
   * @param logging
   */
  public void addLoggingEvent(LoggingEvent logging) {
    if(loggingEvents.size()+1>maxSize) {
      loggingEvents.remove(0);
      discardedLogs++;
    }
    loggingEvents.add(logging);
  }
  
  /**
   * Get the amound of discarded logs. 
   */
  public long getDiscardedLogCount(int count) {
    return discardedLogs;
  }
  
  /**
   * Export all the logs to a file
   */
  public void exportToJSON(String filename) {
    try {
        FileWriter fw = new FileWriter(filename);
        fw.write("[");
        for(int i = 0; i< loggingEvents.size();i++) {
          LoggingEvent log = loggingEvents.get(i);
          JSONLayout jLayout = new JSONLayout();
          String format = jLayout.format(log);
          fw.write(format);
          if(i + 1 != loggingEvents.size()) {
            fw.write(",");
          }
        } 
        fw.write("]");
        fw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get the logs in a String array
   */
  public String[] getLogs(int count) {
    String[] logFormats = new String[loggingEvents.size()];
    for(int i =0;i<loggingEvents.size();i++) {
      LoggingEvent l = loggingEvents.get(i);
      PatternLayout p = new PatternLayout();
      logFormats[i] = p.format(l);
    }
    return logFormats;
  }

  /**
   * Get the number of logs. 
   */
  public long getLogCount(int count) {
    return loggingEvents.size();
  }
  
  /**
   * Main Statement. Used for mBeans. 
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) 
      throws Exception { 
   
      MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); 
      ObjectName beanName = new ObjectName("MemAppender:name="+name); 
      MemAppender mbean = new MemAppender(); 
      mbs.registerMBean(mbean, beanName); 

      System.out.println("Waiting forever..."); 
      while (true) {
        
      }
  } 
}