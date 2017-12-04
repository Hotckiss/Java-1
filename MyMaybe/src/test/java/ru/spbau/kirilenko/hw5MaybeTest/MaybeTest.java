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

        assertTrue(maybe.get().equals(7));
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

        assertTrue(maybe.map(x -> x * x).get().equals(49));
    }

    /**
     * Testing correctness of map method with empty Maybe
     */
    @Test
    public void testMapNull() throws Exception {
        Maybe<Integer> maybe = Maybe.nothing();

        assertFalse(maybe.map(x -> x * x).isPresent());
    }

    /**
     * Testing correctness of reading numbers to maybe from file
     */
    @Test
    public void testReadFromFile() throws Exception {
        File file = new File("in.txt");
        try (Scanner scanner = new Scanner(file)) {
            assertEquals((Integer)1234, MaybeTest.readNumber(scanner).get());
            assertFalse(MaybeTest.readNumber(scanner).isPresent());

            assertEquals((Integer)5, MaybeTest.readNumber(scanner).get());
            assertEquals((Integer)6, MaybeTest.readNumber(scanner).get());
            assertEquals((Integer)7, MaybeTest.readNumber(scanner).get());
            assertFalse(MaybeTest.readNumber(scanner).isPresent());

            assertEquals((Integer)4444, MaybeTest.readNumber(scanner).get());
        }
    }

    /**
     * Testing correctness of writing numbers to maybe from file
     */
    @Test
    public void testWriteToFile() throws Exception {
        File fileIn = new File("in.txt");
        File fileOut = new File("out.txt");
        fileOut.createNewFile();
        Maybe<Integer> maybe;

        try (BufferedWriter fw = new BufferedWriter(new FileWriter("out.txt")); Scanner scanner = new Scanner(fileIn);) {
            for(int i = 0; i < 7; i++) {
                maybe = MaybeTest.readNumber(scanner);
                if (maybe.isPresent()) {
                    fw.write(maybe.map(x -> x * x).get().toString() + "\n");
                } else {
                    fw.write("null\n");
                }
            }
        }

        try (Scanner scannerOut = new Scanner(fileOut)) {
            assertEquals((Integer)1522756, MaybeTest.readNumber(scannerOut).get());

            assertFalse(MaybeTest.readNumber(scannerOut).isPresent());

            assertEquals((Integer)25, MaybeTest.readNumber(scannerOut).get());
            assertEquals((Integer)36, MaybeTest.readNumber(scannerOut).get());
            assertEquals((Integer)49, MaybeTest.readNumber(scannerOut).get());

            assertFalse(MaybeTest.readNumber(scannerOut).isPresent());

            assertEquals((Integer)19749136, MaybeTest.readNumber(scannerOut).get());
        }

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