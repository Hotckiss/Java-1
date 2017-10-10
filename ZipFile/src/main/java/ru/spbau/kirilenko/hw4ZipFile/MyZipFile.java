package ru.spbau.kirilenko.hw4ZipFile;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * A class that has tools to unzip files from some path by some regexp
 */
public class MyZipFile {
    private static ArrayList<ZipFile> files = new ArrayList<>();

    /**
     * Main method that extract files from the zip archives in this directory by regex
     * @param args two strings that represent path and regex
     * @throws IOException if some errors with files occurred
     */
    public static void main(@NotNull String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of arguments!");
        }

        if (args[0] == null || args[1] == null) {
            throw new IllegalArgumentException("Bad directory or regex!");
        }

        findFiles(new File(args[0]));

        unzip(args[1]);
    }

    private static void findFiles(@NotNull File currentFile) {
        try {
            for (File file : currentFile.listFiles()) {

                if (file.isDirectory()) {
                    findFiles(file);
                } else {
                    if (file.getName().endsWith(".zip")) {
                        files.add(new ZipFile(file));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void unzip(@NotNull String regex) throws IOException {
        byte[] buffer = new byte[1024];

        for (ZipFile file : files) {
            Enumeration<? extends ZipEntry> zipEntries = file.entries();

            while (zipEntries.hasMoreElements()) {

                ZipEntry entry = zipEntries.nextElement();

                if (entry.isDirectory()) {
                    continue;
                }

                if (entry.toString().matches(regex)) {

                    File tmp = new File(file.getName());
                    Path path = Paths.get(tmp.getParentFile().toString(), entry.getName());
                    File outputFile = new File(path.toString());

                    outputFile.getParentFile().mkdirs();

                    if (!outputFile.createNewFile()) {
                        return;
                    }

                    try (FileOutputStream os = new FileOutputStream(outputFile)) {
                        InputStream input = file.getInputStream(entry);
                        int length;

                        while ((length = input.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
