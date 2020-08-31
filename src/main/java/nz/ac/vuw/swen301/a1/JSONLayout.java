package nz.ac.vuw.swen301.a1;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.json.JSONObject;

/**
 * JSON Layout Class
 * @author Caitlin
 *
 */
public class JSONLayout extends Layout {

    public void activateOptions() {
    }

    /**
     * Format method, to format the LoggingEvent. 
     */
    @Override
    public String format(LoggingEvent event) {
      String formatting = "";
      if(event == null) {
        return formatting;
      }
      JSONObject obj = new JSONObject();
      obj.put("logger", event.getLoggerName());
      obj.put("level", event.getLevel());
      obj.put("starttime", event.getStartTime());
      obj.put("thread",event.getThreadName());
      obj.put("message", event.getMessage());
      formatting = obj.toString();
      return formatting;
    }

    @Override
    public boolean ignoresThrowable() {
      return false;
    }
}