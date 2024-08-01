package com.test.credential.manager;

import java.util.Objects;

public class Credential
{
  public String target;
  public String username;
  public String password;

  public Credential(String target, String username, String password)
  {
    this.target = Objects.toString(target, "");
    this.username = Objects.toString(username, "");
    this.password = Objects.toString(password, "");
  }
}
