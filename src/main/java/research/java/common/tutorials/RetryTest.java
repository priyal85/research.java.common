package research.java.common.tutorials;

import java.util.Date;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class RetryTest
{
  
  private static int count=0;

  public static void main(String[] args) throws Throwable
  {
    RetryTemplate retryTemplate = new RetryTemplate();
    ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
    backOffPolicy.setInitialInterval(200);
    retryTemplate.setBackOffPolicy(backOffPolicy);
    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(10);
    retryTemplate.setRetryPolicy(retryPolicy);
    retryTemplate.setThrowLastExceptionOnExhausted(true);
    long startTime = System.currentTimeMillis();
    try
    {
      retryTemplate.execute(context -> {
        
        System.out.println("Attempt "+ (count+1) + " at "+ (System.currentTimeMillis()- startTime));
        
        if (count==10)
        {
          System.out.println("Task completed at "+ (System.currentTimeMillis() - startTime));
        } else {
          count++;
          throw new IllegalStateException("Task is not ready");
        }
        return true;
      }, new RecoveryCallback<Boolean>(){

        @Override
        public Boolean recover(RetryContext context) throws Exception
        {
          System.out.println("Task Recovered at "+ (System.currentTimeMillis() - startTime));
          return true;
        }
        
      });
    }
    catch (IllegalStateException e)
    {
      System.out.println("Task Failed at "+ (System.currentTimeMillis() - startTime));
    }
  }

}
