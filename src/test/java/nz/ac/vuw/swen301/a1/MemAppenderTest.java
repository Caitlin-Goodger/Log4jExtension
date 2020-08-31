package nz.ac.vuw.swen301.a1;


import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.json.JSONObject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for MemAppender.
 */
public class MemAppenderTest 
    extends TestCase
{
  Logger DEFAULT_LOGGER = Logger.getLogger(JSONLayout.class);
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MemAppenderTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MemAppenderTest.class );
    }

    /**
     * Test to ensure that the getName method works
     */
    public void testTheGetNameFunction()
    {
        MemAppender m = new MemAppender();
        m.setName("Test");
        assertEquals("nz.ac.vuw.ecs.swen301.a1:type=MemAppender, name=Test",m.getName());
    }
    
    /**
     * Test to ensure that the getMaxSize method works
     */
    public void testTheGetMaxSizeFunction()
    {
        MemAppender m = new MemAppender();
        assertEquals(1000,m.getMaxSize());
    }
    
    /**
     * Test to ensure that the setMaxSize method works
     */
    public void testTheSetMaxSizeFunction()
    {
        MemAppender m = new MemAppender();
        m.setMaxSize(10);
        assertEquals(10,m.getMaxSize());
    }
    
    /**
     * Test to ensure that the getCurrentLogs works as expected
     */
    public void testTheGetLogsFunction()
    {
        MemAppender m = new MemAppender();
        assertEquals(0,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the addLogs works as expected with Level.INFO
     */
    public void testTheAddInfoLogsFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.INFO, "message",null);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the addLogs works as expected with Level.OFf
     */
    public void testTheAddOffLogsFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.OFF, "message",null);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the addLogs works as expected with Level.FATAL
     */
    public void testTheAddFatalLogsFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.FATAL, "message",null);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the addLogs works as expected with Level.ERROR
     */
    public void testTheAddErrorLogsFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.ERROR, "message",null);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the addLogs works as expected with Level.WARN
     */
    public void testTheAddWarnLogsFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.WARN, "message",null);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the addLogs works as expected with Level.DEBUG
     */
    public void testTheAddDebugLogsFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.DEBUG, "message",null);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the addLogs works as expected with Level.TRACE
     */
    public void testTheAddTraceLogsFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.TRACE, "message",null);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the addLogs works as expected with Level.ALL
     */
    public void testTheAddAllLogsFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.ALL, "message",null);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the addLogs works as expected and removes logs when about to reach max
     */
    public void testTheRemoveLogFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.ALL, "message",null);
        m.setMaxSize(1);
        m.addLoggingEvent(logEvent);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getCurrentLogs().size());
    }
    
    /**
     * Test to ensure that the discardedLogs method counts appropriately
     */
    public void testTheDiscardedLogFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.ALL, "message",null);
        m.setMaxSize(1);
        m.addLoggingEvent(logEvent);
        m.addLoggingEvent(logEvent);
        assertEquals(1,m.getDiscardedLogCount(0));
    }
    
    /**
     * Test to ensure that the ExportToJSON method works appropriately
     */
    public void testTheExportJSONFunction()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.ALL, "message",null);
        m.addLoggingEvent(logEvent);
        LoggingEvent logEvent2 = new LoggingEvent("",logger,Level.ERROR, "message",null);
        m.addLoggingEvent(logEvent2);
        m.exportToJSON("filename.json");
        File f = new File("filename.json"); 
        assertTrue(f.exists());
    }
    
    
    /**
     * Test to ensure that the ExportToJSON method works appropriately with two different loggers
     */
    public void testTheExportJSONFunction2()
    {
        MemAppender m = new MemAppender();
        JSONLayout jlayout = new JSONLayout();
        Logger logger = Logger.getLogger(JSONLayout.class);
        LoggingEvent logEvent = new LoggingEvent("",logger,Level.ALL, "message",null);
        m.addLoggingEvent(logEvent);
        Logger logger2 = Logger.getLogger(MemAppender.class);
        LoggingEvent logEvent2 = new LoggingEvent("",logger2,Level.ERROR, "message",null);
        m.addLoggingEvent(logEvent2);
        m.exportToJSON("filename.json");
        File f = new File("filename.json"); 
        assertTrue(f.exists());
    }
    
    /**
     * Test to ensure that the getLogCount method works appropriately with two different loggers
     */
    public void testLogCount()
    {
      MemAppender m = new MemAppender();
      JSONLayout jlayout = new JSONLayout();
      Logger logger = Logger.getLogger(JSONLayout.class);
      LoggingEvent logEvent = new LoggingEvent("",logger,Level.ALL, "message",null);
      m.addLoggingEvent(logEvent);
      Logger logger2 = Logger.getLogger(MemAppender.class);
      LoggingEvent logEvent2 = new LoggingEvent("",logger2,Level.ERROR, "message",null);
      m.addLoggingEvent(logEvent2);
      assertEquals(2,m.getLogCount(0));
    }
    
    /**
     * Test to ensure that the getLogs method works appropriately with two different loggers
     */
    public void testGetLogs()
    {
      MemAppender m = new MemAppender();
      JSONLayout jlayout = new JSONLayout();
      Logger logger = Logger.getLogger(JSONLayout.class);
      LoggingEvent logEvent = new LoggingEvent("",logger,Level.ALL, "all",null);
      m.addLoggingEvent(logEvent);
      Logger logger2 = Logger.getLogger(MemAppender.class);
      LoggingEvent logEvent2 = new LoggingEvent("",logger2,Level.ERROR, "error",null);
      m.addLoggingEvent(logEvent2);
      String[] logs = m.getLogs(0);
      String[] expected = new String[2];
      PatternLayout p = new PatternLayout();
      expected[0] = p.format(logEvent);
      expected[1] = p.format(logEvent2);
      assertArrayEquals(logs,expected);
    }
    

}
