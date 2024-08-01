package com.test.credential.manager;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.W32APIOptions;

import java.util.Arrays;
import java.util.List;

public class WindowsCredentialStoreExample {

  public static void main(String[] args) {
    // Store a credential
  //  storeCredential("exampleCredential", "username", "password");

    // Retrieve the credential
    retrieveCredential("Cosmic/vault.url");
  }

  // Define the CredWrite function
  public interface Advapi32 extends Library {
    Advapi32 INSTANCE = Native.load("Advapi32", Advapi32.class, W32APIOptions.UNICODE_OPTIONS);

    boolean CredWrite(CREDENTIAL credential, int flags);

    boolean CredRead(String targetName, int type, int reservedFlag, PointerByReference credential);

    boolean CredFree(PointerByReference credential);
  }

  // Define the CREDENTIAL structure
  @Structure.FieldOrder({"Flags", "Type", "TargetName", "Comment", "LastWritten", "CredentialBlobSize", "CredentialBlob", "Persist", "AttributeCount", "Attributes", "TargetAlias", "UserName"})
  public static class CREDENTIAL extends Structure {
    public int Flags;
    public int Type;
    public String TargetName;
    public String Comment;
    public FILETIME LastWritten;
    public int CredentialBlobSize;
    public byte[] CredentialBlob = new byte[256];
    public int Persist;
    public int AttributeCount;
    public PointerByReference Attributes;
    public String TargetAlias;
    public String UserName;
    CREDENTIAL() {
    }
    CREDENTIAL(Pointer pointer) {
      useMemory(pointer);
    }
  }

  // Define the FILETIME structure
  @Structure.FieldOrder({"dwLowDateTime", "dwHighDateTime"})
  public static class FILETIME extends Structure {
    public int dwLowDateTime;
    public int dwHighDateTime;
  }


  public static void storeCredential(String targetName, String username, String password) {
    CREDENTIAL credential = new CREDENTIAL();
    credential.TargetName = targetName;
    credential.UserName = username;
    credential.CredentialBlob = Native.toByteArray(password);
    credential.CredentialBlobSize = password.length() * 2;
    credential.Type = 1; // CRED_TYPE_GENERIC
    credential.Persist = 2; // CRED_PERSIST_LOCAL_MACHINE

    boolean result = Advapi32.INSTANCE.CredWrite(credential, 0);
    if (result) {
      System.out.println("Credential stored successfully");
    } else {
      System.out.println("Failed to store credential");
    }
  }

  public static void retrieveCredential(String targetName) {
    PointerByReference pCredential = new PointerByReference();
    boolean result = Advapi32.INSTANCE.CredRead(targetName, 1, 0, pCredential);
    if (result) {
      CREDENTIAL credential = new CREDENTIAL(pCredential.getValue());
      credential.read();
      byte[] passwordBytes = Arrays.copyOf(credential.CredentialBlob, credential.CredentialBlobSize);
      String password = new String(passwordBytes, 0, credential.CredentialBlobSize / 2);
      System.out.println("Username: " + credential.UserName);
      System.out.println("Password: " + password);
      Advapi32.INSTANCE.CredFree(pCredential);
    } else {
      System.out.println("Failed to retrieve credential");
    }
  }

}
