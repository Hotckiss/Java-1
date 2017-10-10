package ru.spbau.kirilenko.hw4ZipFileTest;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.kirilenko.hw4ZipFile.MyZipFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.*;

/**
 * A class that contains some tests for MyZipFile
 * Class contains three tests with different regex
 */
public class ZipFileTest {
    private byte[][] data = new byte[5][100];
    private final String dir = new String(System.getProperty("user.dir") + "/src/test/resources/");

    /**
     * Method that generates archive of files with random data
     * @throws IOException if some errors with files occurred
     */
    @Before
    public void generateTestFiles() throws IOException {

        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            rand.nextBytes(data[i]);
        }

        File file1 = new File(System.getProperty("user.dir") + "/src/test/resources/test.zip");
        file1.createNewFile();

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file1))) {
            for (int i = 0; i < 5; i++) {
                zos.putNextEntry(new ZipEntry(i +".txt"));
                zos.write(data[i], 0, data[i].length);
                zos.closeEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test extracts all files from the archive and check correctness of the files content
     */
    @Test
    public void testUnzipByRegexp1() throws Exception {
        MyZipFile.main(new String[] {dir, "(.*).txt"});

        Path path = Paths.get(dir + "0.txt");
        assertArrayEquals(data[0], Files.readAllBytes(path));
        Files.delete(path);

        path = Paths.get(dir + "1.txt");
        assertArrayEquals(data[1], Files.readAllBytes(path));
        Files.delete(path);

        path = Paths.get(dir + "2.txt");
        assertArrayEquals(data[2], Files.readAllBytes(path));
        Files.delete(path);

        path = Paths.get(dir + "3.txt");
        assertArrayEquals(data[3], Files.readAllBytes(path));
        Files.delete(path);

        path = Paths.get(dir + "4.txt");
        assertArrayEquals(data[4], Files.readAllBytes(path));
        Files.delete(path);
    }

    /**
     * Test extracts one file from the archive and check correctness of the file content
     */
    @Test
    public void testUnzipByRegexp2() throws Exception {
        MyZipFile.main(new String[] {dir, "3.txt"});

        Path path = Paths.get(dir + "3.txt");
        assertArrayEquals(data[3], Files.readAllBytes(path));
        Files.delete(path);
    }

    /**
     * Test not extract any files from the archive and check correctness
     */
    @Test
    public void testUnzipByRegexp3() throws Exception {
        MyZipFile.main(new String[] {dir, "6.txt"});
        Path path = Paths.get(dir + "6.txt");
        assertFalse(Files.exists(path));
    }

}