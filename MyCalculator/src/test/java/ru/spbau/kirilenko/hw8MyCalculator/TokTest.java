package ru.spbau.kirilenko.hw8MyCalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A class that tests all methods of token class
 */
public class TokTest {

    /**
     * A test of equality of two tokens
     */
    @Test
    public void testEquals() {
        Tok expected1 = new Tok(Tok.Tokid.TOK_NUM, 1.0);
        Tok expected2 = new Tok(Tok.Tokid.TOK_NUM, 2.0);
        Tok expected3 = new Tok(Tok.Tokid.TOK_NUM, 1.0);

        assertEquals(expected1, expected3);

        assertNotEquals(expected1, expected2);

        assertNotEquals(expected3, expected2);
    }

    /**
     * A test of getting value from the token
     */
    @Test
    public void testGetNum() {
        Tok expected1 = new Tok(Tok.Tokid.TOK_NUM, 1.0);

        assertEquals(1.0, expected1.getNum(), 0.0);
    }

    /**
     * A test of getting operation from the token
     */
    @Test
    public void testGetOp() {
        Tok expected1 = new Tok(Tok.Tokid.TOK_OP, '+');

        assertEquals((int)'+', expected1.getOp());
    }

    /**
     * A test of getting id from the token
     */
    @Test
    public void testGetId() {
        Tok expected1 = new Tok(Tok.Tokid.TOK_OP, '+');
        Tok expected2 = new Tok(Tok.Tokid.TOK_NUM, 1.0);

        assertEquals(Tok.Tokid.TOK_NUM, expected2.getId());
        assertEquals(Tok.Tokid.TOK_OP, expected1.getId());
}

    /**
     * A test of setting value to the token
     */
    @Test
    public void testSetNum() {
        Tok expected1 = new Tok(Tok.Tokid.TOK_NUM, 1.0);

        assertEquals(1.0, expected1.getNum(), 0.0);

        expected1.setNum(2.0);

        assertEquals(2.0, expected1.getNum(), 0.0);
    }

}