package com.test.credential.manager;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import com.sun.jna.LastErrorException;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class WindowsCredentialServiceImpl implements WindowsCredentialService
{

  private static final WindowsCredentialService credService = new CredentialAdvancedServiceApiImpl();

  public static <T> T coalesce(final T maybeNullValue, final T nonNullValue)
  {
    return maybeNullValue == null ? nonNullValue : maybeNullValue;
  }

  @Override
  public Credential getCredential(String target)
  {
    return credService.getCredential(target);
  }

  @Override
  public boolean setCredential(String target, String userName, String password) throws UnsupportedEncodingException
  {
    return credService.setCredential(target, userName, password);
  }

  @Override
  public boolean deleteCredential(String target) throws UnsupportedEncodingException
  {
    return credService.deleteCredential(target);
  }


  private static class CredentialAdvancedServiceApiImpl
    implements CredentialAdvancedServiceApi32, WindowsCredentialService
  {

    @Override
    public Credential getCredential(String target)
    {
      CredentialAdvancedServiceApi32.PCREDENTIAL pcredMem = new PCREDENTIAL();

      try
      {
        if (CredRead(target, 1, 0, pcredMem))
        {
          CREDENTIAL credMem = new CREDENTIAL(pcredMem.credential);
          byte[] passwordBytes = credMem.CredentialBlob.getByteArray(0, credMem.CredentialBlobSize);

          String password = new String(passwordBytes, Charset.forName("UTF-16LE"));
          Credential cred = new Credential(credMem.TargetName, credMem.UserName, password);
          return cred;
        }
        else
        {
          int err = Native.getLastError();
          throw new LastErrorException(err);
        }
      }
      finally
      {
        CredFree(pcredMem.credential);
      }
    }

    @Override
    public boolean setCredential(String target, String userName, String password) throws UnsupportedEncodingException
    {
      CREDENTIAL credMem = new CREDENTIAL();

      credMem.Flags = 0;
      credMem.TargetName = target;
      credMem.Type = CRED_TYPE_GENERIC;
      credMem.UserName = userName;
      credMem.AttributeCount = 0;
      credMem.Persist = CRED_PERSIST_ENTERPRISE;
      byte[] bpassword = password.getBytes("UTF-16LE");
      credMem.CredentialBlobSize = (int) bpassword.length;
      credMem.CredentialBlob = getPointer(bpassword);
      if (!CredWrite(credMem, 0))
      {
        int err = Native.getLastError();
        throw new LastErrorException(err);
      }
      else
      {
        return true;
      }
    }

    @Override
    public boolean deleteCredential(String target) throws UnsupportedEncodingException
    {
      if (!CredDelete(target, CRED_TYPE_GENERIC, 0))
      {
        int err = Native.getLastError();
        throw new LastErrorException(err);
      }
      else
      {
        return true;
      }
    }

    private static Pointer getPointer(byte[] array)
    {
      Pointer p = new Memory(array.length);
      p.write(0, array, 0, array.length);

      return p;
    }

    @Override
    public boolean CredRead(String targetName, int type, int flags, PCREDENTIAL pcredential) throws LastErrorException
    {
      synchronized (INSTANCE)
      {
        return INSTANCE.CredRead(targetName, type, flags, pcredential);
      }
    }

    @Override
    public boolean CredWrite(CREDENTIAL credential, int flags) throws LastErrorException
    {
      synchronized (INSTANCE)
      {
        return INSTANCE.CredWrite(credential, flags);
      }
    }

    @Override
    public boolean CredDelete(String targetName, int type, int flags) throws LastErrorException
    {
      synchronized (INSTANCE)
      {
        return INSTANCE.CredDelete(targetName, type, flags);
      }
    }

    @Override
    public void CredFree(Pointer credential) throws LastErrorException
    {
      synchronized (INSTANCE)
      {
        INSTANCE.CredFree(credential);
      }
    }
  }
}