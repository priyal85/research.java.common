package research.java.common.tutorials.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExample
{
  private static final Logger logger = LoggerFactory.getLogger(LogExample.class);

  public static void main(String[] args)
  {
    logger.info("Example log from {}", LogExample.class.getSimpleName());

  }

}
