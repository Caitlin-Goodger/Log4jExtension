package nz.ac.vuw.swen301.a1;


import org.apache.log4j.Logger;


import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.json.JSONObject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for JSONLayout.
 */
public class JSONLayoutTest 
    extends TestCase
{
  Logger DEFAULT_LOGGER = Logger.getLogger(JSONLayout.class);
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JSONLayoutTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    
    public static Test suite()
    {
        return new TestSuite( JSONLayoutTest.class );
    }

    /**
     * Test to ensure that the returned String from the format method is as expected. 
     */
    
    public void testReturnedString()
    {
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.INFO, "message",null);
        String expectedReturn = "{\"level\":\"" + logEvent.getLevel() + "\"," + "\"logger\":\"" + logEvent.getLoggerName() + "\"," + "\"starttime\":" + logEvent.getStartTime() + "," + "\"thread\":\"" + logEvent.getThreadName() + "\"," + "\"message\":\"" + logEvent.getMessage() + "\"}";
        assertEquals(jlayout.format(logEvent),expectedReturn);
    }
    
    /**
     * Test to ensure that the format method can deal with null. 
     */
    public void testNull()
    {
        JSONLayout jlayout = new JSONLayout();
        assertEquals(jlayout.format(null),"");
        
    }
    
    /**
     * Test to ensure that the returned String can be parser into JSON. If it reached the assert statement and hasn't failed then it must have
     */
    public void testParsing()
    {
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.INFO, "message",null);
        String returnValue = jlayout.format(logEvent);
        JSONObject obj = new JSONObject(returnValue);
        assertTrue(true);
 
    }
    
    /**
     * Test to ensure that the returned String can be parser into JSON. Then check that the level is right. 
     */
    public void testLevelReturn()
    {
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.INFO, "message",null);
        String returnValue = jlayout.format(logEvent);
        JSONObject obj = new JSONObject(returnValue);
        assertEquals(obj.get("level"),logEvent.getLevel().toString());
 
    }
    
    /**
     * Test to ensure that the returned String can be parser into JSON. Then check that the message is right. 
     */
    public void testMessageReturn()
    {
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.INFO, "message",null);
        String returnValue = jlayout.format(logEvent);
        JSONObject obj = new JSONObject(returnValue);
        assertEquals(obj.get("message"),logEvent.getMessage().toString());
    }
    
    /**
     * Test to ensure that the returned String can be parser into JSON. Then check that the logger is right. 
     */
    public void testLoggerReturn()
    {
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.INFO, "message",null);
        String returnValue = jlayout.format(logEvent);
        JSONObject obj = new JSONObject(returnValue);
        assertEquals(obj.get("logger"),logEvent.getLoggerName().toString());
    }
    
    /**
     * Test to ensure that the returned String can be parser into JSON. Then check that the thread is right. 
     */
    public void testThreadReturn()
    {
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.INFO, "message",null);
        String returnValue = jlayout.format(logEvent);
        JSONObject obj = new JSONObject(returnValue);
        assertEquals(obj.get("thread"),logEvent.getThreadName().toString());
    }
    
    /**
     * Test to ensure that the returned String can be parser into JSON. Then check that the start time is right. 
     */
    public void testStartTimeReturn()
    {
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.INFO, "message",null);
        String returnValue = jlayout.format(logEvent);
        JSONObject obj = new JSONObject(returnValue);
        assertEquals(obj.get("starttime"),logEvent.getStartTime());
    }
    

}
