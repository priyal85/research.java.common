package com.test.interviews;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import com.google.common.base.Strings;

public class TvRealityShow
{
  public static void main(String[] args)
  {
    String sms1 = "ITN    ABC     1";
    List<String> codesList = Arrays.stream(sms1.split(" ")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    String[] codes = sms1.split(" ");
    System.out.println(codesList.size());
 
    System.out.println(Integer.parseInt("1.0"));
    
  }

}

class SMSValidator
{
  private ProgramCondition programCondition;

  public SMSValidator(ProgramCondition programCondition)
  {
    super();
    this.programCondition = programCondition;
  }

  public void validateSms(String sms)
  {
    if (!Strings.isNullOrEmpty(sms))
    {
      List<String> codesList = Arrays.stream(sms.split(" ")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
      if (codesList.size() == 3)
      {
        if (!codesList.get(0).equalsIgnoreCase(programCondition.channelCode()))
        {
          throw new IllegalArgumentException("Invalid message format");
        }

        if (!codesList.get(1).equalsIgnoreCase(programCondition.channelCode()))
        {
          throw new IllegalArgumentException("Invalid message format");
        }
        try
        {
          if (Integer.parseInt(codesList.get(2)) > programCondition.maxVote())
          {
            throw new IllegalArgumentException("Invalid message format");
          }
        }
        catch (NumberFormatException e)
        {
          throw new IllegalArgumentException("Invalid message format");
        }

      }
      else
      {
        throw new IllegalArgumentException("Invalid message format");
      }
    }
    else
    {
      throw new IllegalArgumentException("Message should be non empty");
    }

  }
}

interface ProgramCondition
{

  String channelCode();

  String programCode();

  int maxVote();

}