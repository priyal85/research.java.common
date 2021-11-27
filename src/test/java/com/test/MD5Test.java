package com.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.testng.annotations.BeforeTest;

public class MD5Test {
  @BeforeTest
  public void beforeTest() {
  }
  @Test
  public void test() 
    throws NoSuchAlgorithmException {
      String hash = "ARXMX9P0TBEVISVKFFAPCG==";
      String password = "FreeTextChecklistActivityDefinitionSupplier"+"ChemistryRequestActivityDefinitionSupplier"+"LocalAnalysisActivityDefinitionSupplier"+"LocalAnalysisRequestActivityDefinitionSupplier"+"MicrobiologyRequestActivityDefinitionSupplier"+"RadiologyRequestActivityDefinitionSupplier";
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(password.getBytes());
      byte[] digest = md.digest();
      String myHash = DatatypeConverter
        .printBase64Binary(digest).toUpperCase();
      
      System.out.println(myHash);
      System.out.println(myHash.length());
      System.out.println(password.length());
      System.out.println("se.cambio.activity.postinstall.adcreation.ActivityDefinitionCreationPostInstallTask".length());
          
     assertEquals(myHash, hash);
  }

}
