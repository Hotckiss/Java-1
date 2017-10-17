package ru.spbau.kirilenko.hw5MaybeTest;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import ru.spbau.kirilenko.hw5Maybe.Maybe;
import ru.spbau.kirilenko.hw5Maybe.MaybeException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;

/**
 * A class that check all public methods of Maybe class
 */
public class MaybeTest {

    /**
     * Testing correctness of just method
     */
    @Test
    public void testJust() throws Exception {
        Maybe<Integer> maybe = Maybe.just(8);

        assertTrue(maybe.isPresent());
    }

    /**
     * Testing correctness of just method
     */
    @Test
    public void testNothing() throws Exception {
        Maybe<Integer> maybe = Maybe.nothing();

        assertFalse(maybe.isPresent());
    }

    /**
     * Testing correctness of get method from nothing
     */
    @Test(expected = MaybeException.class)
    public void testGetNull() throws Exception {
        Maybe<Integer> maybe = Maybe.nothing();

        maybe.get();
    }

    /**
     * Testing correctness of get method from Maybe with value
     */
    @Test
    public void testGetNotNull() throws Exception {
        Maybe<Integer> maybe = Maybe.just(7);
        try {
            assertTrue(maybe.get().equals(7));
        } catch(MaybeException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Testing correctness of isPresent method with empty Maybe
     */
    @Test
    public void testIsPresentNull() throws Exception {
        Maybe<Integer> maybe = Maybe.nothing();

        assertFalse(maybe.isPresent());
    }

    /**
     * Testing correctness of isPresent method with non-empty Maybe
     */
    @Test
    public void testIsPresentNotNull() throws Exception {
        Maybe<Integer> maybe = Maybe.just(8);

        assertTrue(maybe.isPresent());
    }

    /**
     * Testing correctness of map method with non-empty Maybe
     */
    @Test
    public void testMapNotNull() throws Exception {
        Maybe<Integer> maybe = Maybe.just(7);

        try {
            assertTrue(maybe.map(x -> x*x).get().equals(49));
        } catch(MaybeException ex) {
            fail(ex.getMessage());
        }

    }

    /**
     * Testing correctness of map method with empty Maybe
     */
    @Test
    public void testMapNull() throws Exception {
        Maybe<Integer> maybe = Maybe.nothing();

        assertFalse(maybe.map(x -> x*x).isPresent());
    }

    /**
     * Testing correctness of reading numbers to maybe from file
     */
    @Test
    public void testReadFromFile() throws Exception {
        File file = new File("in.txt");
        Scanner scanner = new Scanner(file);

        assertTrue(MaybeTest.readNumber(scanner).get().equals(1234));
        assertFalse(MaybeTest.readNumber(scanner).isPresent());
        assertTrue(MaybeTest.readNumber(scanner).get().equals(5));
        assertTrue(MaybeTest.readNumber(scanner).get().equals(6));
        assertTrue(MaybeTest.readNumber(scanner).get().equals(7));
        assertFalse(MaybeTest.readNumber(scanner).isPresent());
        assertTrue(MaybeTest.readNumber(scanner).get().equals(4444));

        scanner.close();
    }

    /**
     * Testing correctness of writing numbers to maybe from file
     */
    @Test
    public void testWriteToFile() throws Exception {
        File fileIn = new File("in.txt");
        File fileOut = new File("out.txt");
        fileOut.createNewFile();

        Scanner scanner = new Scanner(fileIn);

        Maybe<Integer> maybe;
        try (BufferedWriter fw = new BufferedWriter(new FileWriter("out.txt"))) {
            for(int i = 0; i < 7; i++) {
                maybe = MaybeTest.readNumber(scanner);
                if (maybe.isPresent()) {
                    fw.write(maybe.map(x -> x * x).get().toString() + "\n");
                } else {
                    fw.write("null\n");
                }
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        scanner.close();

        Scanner scannerOut = new Scanner(fileOut);

        assertTrue(MaybeTest.readNumber(scannerOut).get().equals(1522756));
        assertFalse(MaybeTest.readNumber(scannerOut).isPresent());
        assertTrue(MaybeTest.readNumber(scannerOut).get().equals(25));
        assertTrue(MaybeTest.readNumber(scannerOut).get().equals(36));
        assertTrue(MaybeTest.readNumber(scannerOut).get().equals(49));
        assertFalse(MaybeTest.readNumber(scannerOut).isPresent());
        assertTrue(MaybeTest.readNumber(scannerOut).get().equals(19749136));

        scannerOut.close();

    }

    private static @NotNull Maybe<Integer> readNumber(Scanner scanner) {
        String str = scanner.nextLine();
        Maybe<Integer> maybe;
        try {
            maybe = Maybe.just(parseInt(str));
        } catch (NumberFormatException ex) {
            maybe =  Maybe.nothing();
        }
        return maybe;
    }

}