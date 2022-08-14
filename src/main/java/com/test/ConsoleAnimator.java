package com.test;

public class ConsoleAnimator
{
  private boolean keepAnimating;
  private String lastLine = "";

  private byte animationRound;
  private String message;

  public ConsoleAnimator(String message)
  {
    this.message = message;
  }

  public void print(String line)
  {
    //clear the last line if longer
    if (lastLine.length() > line.length())
    {
      clearLastLine();
    }
    System.out.print("\r" + line);
    lastLine = line;
  }

  private void clearLastLine()
  {
    String temp = "";
    for (int i = 0; i < lastLine.length(); i++)
    {
      temp += " ";
    }
    if (temp.length() > 1)
      System.out.print("\r" + temp);

  }

  private void animate(String line)
  {
    switch (animationRound)
    {
    case 1:
    case 7:
      print(line + " . !");
      break;
    case 2:
    case 6:
      print(line + " . . !");
      break;
    case 3:
    case 5:
      print(line + " . . . !");
      break;
    case 4:
      print(line + " . . . . !");
      break;
    default:
      animationRound = 0;
      print(line + "!");
    }
    animationRound++;
  }

  public void startAnimation()
  {
    keepAnimating = true;
    while (keepAnimating)
    {
      animate(message);
      try
      {
        Thread.sleep(400);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void stopAnimation()
  {
    keepAnimating = false;
    clearLastLine();
  }

  /**
   * Main method is for testing the animation
   *  arg[0] - Time to run the animation in milliseconds, expect an integer. Default is 10000.
   *  arg[1] - Message to display as the animation prefix. Default is 'Processing'.
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException
  {
    int millis_to_run_animation = 10000;
    try
    {
      millis_to_run_animation = args.length > 0 ? Integer.parseInt(args[0]) : 10000;
    }
    catch (NumberFormatException e)
    {
      System.out.println(
        "The 1st argument should be milliseconds to run the animation and should be an integer. Continuing with default value 10000ms.");
    }
    String message = args.length > 1 ? args[1] : "Processing";
    ConsoleAnimator consoleHelper = new ConsoleAnimator(message);
    Thread t1 = new Thread(consoleHelper::startAnimation);
    t1.start();
    Thread.sleep(millis_to_run_animation);
    consoleHelper.stopAnimation();
    System.out.println("\r" + "Completed.");
  }
}


