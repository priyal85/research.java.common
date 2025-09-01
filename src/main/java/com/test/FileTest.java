package com.test;

import java.io.File;
import java.io.IOException;

public class FileTest
{
  public static void main(String[] args) throws IOException
  {
    File.createTempFile("prefix", "suffix");
  }
}
