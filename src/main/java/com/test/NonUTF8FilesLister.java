package com.test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NonUTF8FilesLister {
    private static final String UTF8_BOM = "\uFEFF";
    
    public static final Predicate<File> FILE_FILTER_PREDICATE =
      file -> file.isFile() && !file.isHidden() && !file.getAbsolutePath().contains(".git");

    public static void main(String[] args) throws IOException {
        // Specify the directory to search for files
    //   String directoryPath = "/path/to/directory";
        String directoryPath = args[0];

        // Get a list of all files in the directory and its subdirectories
        List<File> files = listFilesRecursive(new File(directoryPath));

        
        File folder = new File(directoryPath);
        // folder.getAbsolutePath();
         try (Stream<Path> path = Files.walk(Paths.get(directoryPath)))
         {
             Predicate<? super File> predicate= (f) -> true;
              path.map(Path::toFile)
             .filter(predicate).collect(Collectors.toMap(getRelativePathFromRootFolderFunction(folder), File::getAbsolutePath));
         }
         catch (IOException e)
         {
           throw new RuntimeException(e);
         }
        
        // Filter the list to include only files that are not UTF-8 encoded
        List<File> nonUTF8Files = new ArrayList<>();
        for (File file : files) {
            if (!isUTF8Encoded(file)) {
                nonUTF8Files.add(file);
            }
        }

        // Print the list of non-UTF-8 encoded files
        System.out.println("Non-UTF-8 encoded files:");
        for (File file : nonUTF8Files) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static Function<File,String> getRelativePathFromRootFolderFunction(File folder)
    {
        return f -> f.getPath().substring(folder.getAbsolutePath().length());
    }

    /**
     * Recursively list all files in a directory and its subdirectories.
     *
     * @param directory the directory to search for files
     * @return a list of all files in the directory and its subdirectories
     */
    private static List<File> listFilesRecursive(File directory) {
        List<File> files = new ArrayList<>();
        if (directory.isDirectory()) {
            File[] subDirectories = directory.listFiles(File::isDirectory);
            for (File subDirectory : subDirectories) {
                files.addAll(listFilesRecursive(subDirectory));
            }

            File[] subFiles = directory.listFiles(File::isFile);
            if (subFiles != null) {
                files.addAll(Arrays.asList(subFiles));
            }
        }

        return files;
    }

    /**
     * Check if a file is UTF-8 encoded.
     *
     * @param file the file to check
     * @return true if the file is UTF-8 encoded, false otherwise
     * @throws IOException if an I/O error occurs while reading the file
     */
    private static boolean isUTF8Encoded(File file) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] bytes = new byte[3];
//            bis.mark(3);
//            bis.read(bytes, 0, 3);
//            bis.reset();
            String fileHeader = new String(bytes, StandardCharsets.UTF_8);
            byte[] allbytes = new byte[bis.read()];
            bis.read(allbytes);
            return !fileHeader.equals(UTF8_BOM) && Charset.availableCharsets().get("UTF-8").newDecoder().decode(ByteBuffer.wrap(allbytes)).toString().equals("");
        }
    }
}
