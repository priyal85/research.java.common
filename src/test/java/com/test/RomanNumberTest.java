package com.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RomanNumberTest
{

  @BeforeMethod
  public void setUp() throws Exception
  {
  }

  @Test
  public void testToString1500() throws Exception
  {
    Assert.assertEquals(new RomanNumber(1500).toString(), "MD");
  }

  @Test
  public void testToString1To10() throws Exception
  {
    Assert.assertEquals(new RomanNumber(1).toString(), "I");
    Assert.assertEquals(new RomanNumber(2).toString(), "II");
    Assert.assertEquals(new RomanNumber(3).toString(), "III");
    Assert.assertEquals(new RomanNumber(4).toString(), "IV");
    Assert.assertEquals(new RomanNumber(5).toString(), "V");
    Assert.assertEquals(new RomanNumber(6).toString(), "VI");
    Assert.assertEquals(new RomanNumber(7).toString(), "VII");
    Assert.assertEquals(new RomanNumber(8).toString(), "VIII");
    Assert.assertEquals(new RomanNumber(9).toString(), "IX");
    Assert.assertEquals(new RomanNumber(10).toString(), "X");
  }

  @Test
  public void testToString10To100() throws Exception
  {
    Assert.assertEquals(new RomanNumber(11).toString(), "XI");
    Assert.assertEquals(new RomanNumber(14).toString(), "XIV");
    Assert.assertEquals(new RomanNumber(15).toString(), "XV");
    Assert.assertEquals(new RomanNumber(19).toString(), "XIX");
    Assert.assertEquals(new RomanNumber(20).toString(), "XX");
    Assert.assertEquals(new RomanNumber(45).toString(), "XLV");
    Assert.assertEquals(new RomanNumber(49).toString(), "XLIX");
    Assert.assertEquals(new RomanNumber(50).toString(), "L");
    Assert.assertEquals(new RomanNumber(64).toString(), "LXIV");
    Assert.assertEquals(new RomanNumber(99).toString(), "XCIX");
  
  }

  @Test
  public void testToStringSuffix100To1000() throws Exception
  {
    Assert.assertEquals(new RomanNumber(150).toString(), "CL");
    Assert.assertEquals(new RomanNumber(449).toString(), "CDXLIX");
  }

}
