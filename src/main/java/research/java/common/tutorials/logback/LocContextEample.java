package research.java.common.tutorials.logback;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class LocContextEample
{

  public static void main(String[] args)
  {
    ch.qos.logback.classic.Logger parentLogger = 
        (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.baeldung.logback");
       
      parentLogger.setLevel(Level.INFO);
       
      Logger childlogger = 
        (ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.baeldung.logback.tests");
       
      parentLogger.warn("This message is logged because WARN > INFO.");
      parentLogger.debug("This message is not logged because DEBUG < INFO.");
      childlogger.info("INFO == INFO");
      childlogger.debug("DEBUG < INFO");
      
      //Root logger behavior
      ch.qos.logback.classic.Logger logger = 
          (ch.qos.logback.classic.Logger)LoggerFactory.getLogger("research.java.common.tutorials.logback");
        logger.debug("Hi there!");
         
        Logger rootLogger = 
          (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.debug("This message is logged because DEBUG == DEBUG.");
         
        rootLogger.setLevel(Level.ERROR);
         
        logger.warn("This message is not logged because WARN < ERROR.");
        logger.error("This Error level log is logged.");
        
        // Parameterized log messages
        rootLogger.setLevel(Level.DEBUG);
        String message = "This is a String";
        Integer zero = 0;
         
        try {
            logger.debug("Logging message: {}", message);
            logger.debug("Going to divide {} by {}", 42, zero);
            int result = 42 / zero;
        } catch (Exception e) {
            logger.error("Error dividing {} by {} ", 42, zero, e);
        }
      
  }

}
