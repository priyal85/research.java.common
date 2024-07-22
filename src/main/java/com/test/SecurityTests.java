package com.test;

import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class SecurityTests
{
  public static void main(String[] args) throws NoSuchAlgorithmException
  {
    int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
    System.out.println("Max Key Size for AES : " + maxKeySize);

    String algorithm = "MessageDigest.SHA-256";
    String currentProviders = Security.getProperty("security.provider." + algorithm);
    // Display the current provider list
    System.out.println("Current Providers for " + algorithm + ": " + currentProviders);
  }
}
