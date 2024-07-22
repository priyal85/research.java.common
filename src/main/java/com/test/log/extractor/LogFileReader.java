package com.test.log.extractor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFileReader {
    private static final Pattern EXCEPTION_PATTERN = Pattern.compile("^(\\w+Exception): (.*)");

    public Set<String> extractDistinctExceptionStackTraces(String logFilePath, String specificMessage) {
        Set<String> distinctStackTraces = new HashSet<>();
        StringBuilder currentStackTrace = new StringBuilder();
        String currentExceptionMessage = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = EXCEPTION_PATTERN.matcher(line);
                if (matcher.matches()) {
                    if (currentExceptionMessage != null && currentExceptionMessage.contains(specificMessage)) {
                        distinctStackTraces.add(currentStackTrace.toString());
                    }
                    currentStackTrace = new StringBuilder(line);
                    currentExceptionMessage = matcher.group(2);
                } else if (currentExceptionMessage != null) {
                    currentStackTrace.append("\n").append(line);
                }
            }
            // Add the last stack trace if it matches the specific message
            if (currentExceptionMessage != null && currentExceptionMessage.contains(specificMessage)) {
                distinctStackTraces.add(currentStackTrace.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return distinctStackTraces;
    }
}
