package com.test.log.extractor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExceptionTraceExtractor {

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);

    String logFolderPath;
    if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
      logFolderPath = args[0];
    } else {
      System.out.println("Enter log folder path:");
      logFolderPath = scanner.nextLine();
      if (logFolderPath == null || logFolderPath.isEmpty()) {
        System.out.println("Invalid log folder path. Exiting...");
        return;
      }
    }

    String outputFileName;
    if (args.length > 1 && args[1] != null && !args[1].isEmpty()) {
      outputFileName = args[1];
    } else {
      System.out.println("Enter output file name:");
      outputFileName = scanner.nextLine();
      if (outputFileName == null || outputFileName.isEmpty()) {
        System.out.println("Invalid output file name. Exiting...");
        return;
      }
    }
    String targetExceptionMessage = "#### SPRING UPGRADE PRE-CHECK";

    HashMap<String, Integer> exceptionCounts = new HashMap<>();
    try {
      Files.list(Paths.get(logFolderPath))
        .filter(Files::isRegularFile)
        .forEach(file -> {
          try
          {
            processLogFile(file, targetExceptionMessage, exceptionCounts);
          }
          catch (IOException e)
          {
            throw new RuntimeException(" Processing of file "+file.toString() +" failed.",e);
          }
        });
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Unique exceptions count : "+exceptionCounts.size()+" Total matched exception count : "+ exceptionCounts.values().stream().mapToInt(Integer::intValue).sum());

    writeToFile(exceptionCounts, outputFileName);
  }

  private static void writeToFile(HashMap<String, Integer> exceptionCounts, String outputFileName)
    throws FileNotFoundException, UnsupportedEncodingException
  {
    try (PrintWriter writer = new PrintWriter(outputFileName, "UTF-8")) {
      for (Map.Entry<String, Integer> entry : exceptionCounts.entrySet()) {
        writer.println("Stack Trace: \n" + entry.getKey() + "\nCount: " + entry.getValue() + "\n\n");
      }
    }
  }

  private static void processLogFile(Path logFilePath,
                                     String targetExceptionMessage,
                                     HashMap<String, Integer> exceptionCounts) throws IOException
  {
    try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath.toFile()))) {
      String line;
      StringBuilder currentStackTrace = new StringBuilder();
      boolean inStackTrace = false;

      while ((line = reader.readLine()) != null) {
        line = line.replaceFirst("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3} ", ""); // Remove date time stamp
        line = line.replaceAll("\\(default task-\\d+\\)", ""); // Remove Thread (default task-nn)
        line = line.replaceAll("\\s\\s\\[\\w+\\]", ""); // Remove [username]
        if (line.contains(targetExceptionMessage)) {
          inStackTrace = true;
          currentStackTrace.append(line).append("\n");
        } else if (inStackTrace) {
          line = line.replaceFirst("\tat", "\tat "); // Remove leading "at
          currentStackTrace.append(line).append("\n");
          if (line.trim().isEmpty()) { // End of stack trace
            String stackTrace = currentStackTrace.toString();
            exceptionCounts.compute(stackTrace, (k, v) -> v == null ? 1 : v + 1);
            currentStackTrace.setLength(0);
            inStackTrace = false;
          }
        }
      }
    }
  }
}