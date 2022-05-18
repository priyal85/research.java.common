package com.test;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJSON
{
  public static void main(String[] args)
  {
    FluidBalancePrescriptionDTO dto = new FluidBalancePrescriptionDTO();
    dto.setCareProviderId("");
    dto.setComment("");
    dto.setContactId("");
    dto.setGoalMagnitude(1000.5);
    dto.setGoalPrecision(2);
    dto.setGoalTime(new Date());
    dto.setPatientId("");
    dto.setRegisteredTime(new Date());
    dto.setUnitOfMeasure("");
    dto.setWardId("");
    ObjectMapper mapper = new ObjectMapper();
    try
    {
      mapper.writeValue(System.out, dto);
    }
    catch (JsonGenerationException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (JsonMappingException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

 static class  FluidBalancePrescriptionDTO
  {

    public void setCareProviderId(String string)
    {
      // TODO Auto-generated method stub
      
    }

    public void setRegisteredTime(Date date)
    {
      // TODO Auto-generated method stub
      
    }

    public void setGoalTime(Date date)
    {
      // TODO Auto-generated method stub
      
    }

    public void setWardId(String string)
    {
      // TODO Auto-generated method stub
      
    }

    public void setUnitOfMeasure(String string)
    {
      // TODO Auto-generated method stub
      
    }

    public void setPatientId(String string)
    {
      // TODO Auto-generated method stub
      
    }

    public void setGoalPrecision(int i)
    {
      // TODO Auto-generated method stub
      
    }

    public void setGoalMagnitude(double d)
    {
      // TODO Auto-generated method stub
      
    }

    public void setContactId(String string)
    {
      // TODO Auto-generated method stub
      
    }

    public void setComment(String string)
    {
      // TODO Auto-generated method stub
      
    }

  }
}
