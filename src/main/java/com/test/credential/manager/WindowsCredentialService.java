package com.test.credential.manager;

import java.io.UnsupportedEncodingException;

public interface WindowsCredentialService
{
  Credential getCredential(String target);

  boolean setCredential(String target, String userName, String password) throws UnsupportedEncodingException;

  boolean deleteCredential(String target) throws UnsupportedEncodingException;
}
