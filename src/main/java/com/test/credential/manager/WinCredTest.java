package com.test.credential.manager;

public class WinCredTest
{
  public static void main(String[] args)
  {
    WindowsCredentialServiceImpl wc = new WindowsCredentialServiceImpl();
    // Create a credential
   // wc.setCredential("Application Name", "Username", "Password");
    // Get a credential
    Credential cred = wc.getCredential("Cosmic/vault.url");
    String username = cred.username;
    String password = cred.password;

    System.out.println(username);
    System.out.println(password);
  }
}
