package com.test;

import org.testng.annotations.Test;

public class InvokeTest {

  private static void invoke(final Runnable r) {
      r.run();
  }

  private static void target() {
      new Exception().printStackTrace();
  }

  @Test
  public void lambda() throws Exception {
      invoke(() -> target());
  }

  @Test
  public void methodReference() throws Exception {
      invoke(InvokeTest::target);
  }
}