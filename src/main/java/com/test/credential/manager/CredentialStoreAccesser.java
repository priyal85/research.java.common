package com.test.credential.manager;
import waffle.windows.auth.IWindowsCredentialsHandle;
import waffle.windows.auth.IWindowsSecurityContext;
import waffle.windows.auth.impl.WindowsCredentialsHandleImpl;
import waffle.windows.auth.IWindowsIdentity;
import waffle.windows.auth.impl.WindowsSecurityContextImpl;
public class CredentialStoreAccesser
{
  public static void main(String[] args)
  {
    IWindowsCredentialsHandle clientCredential = WindowsCredentialsHandleImpl.getCurrent("Negotiate");
    clientCredential.initialize();
  //  IWindowsIdentity clientIdentity = WindowsSecurityContextImpl.getCurrent("Negotiate", "localhost").getIdentity();
    IWindowsSecurityContext clientContext = WindowsSecurityContextImpl.getCurrent("Negotiate", "localhost");
    IWindowsIdentity clientIdentity = clientContext.getIdentity();
    System.out.println(clientIdentity.getFqn());
    System.out.println(clientIdentity.getSidString());
  }
}
