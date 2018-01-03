package ru.spbau.kirilenko.hw8MyCalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A class that tests all methods of token class
 */
public class TokenTest {

    /**
     * A test of equality of two tokens
     */
    @Test
    public void testEquals() {
        Token expected1 = new Token(Token.TokenId.TOK_NUM, 1.0);
        Token expected2 = new Token(Token.TokenId.TOK_NUM, 2.0);
        Token expected3 = new Token(Token.TokenId.TOK_NUM, 1.0);

        assertEquals(expected1, expected3);

        assertNotEquals(expected1, expected2);

        assertNotEquals(expected3, expected2);
    }

    /**
     * A test of getting value from the token
     */
    @Test
    public void testGetNum() {
        Token expected1 = new Token(Token.TokenId.TOK_NUM, 1.0);

        assertEquals(1.0, expected1.getNum(), 0.0);
    }

    /**
     * A test of getting operation from the token
     */
    @Test
    public void testGetOp() {
        Token expected1 = new Token(Token.TokenId.TOK_OP, '+');

        assertEquals((int)'+', expected1.getOp());
    }

    /**
     * A test of getting id from the token
     */
    @Test
    public void testGetId() {
        Token expected1 = new Token(Token.TokenId.TOK_OP, '+');
        Token expected2 = new Token(Token.TokenId.TOK_NUM, 1.0);

        assertEquals(Token.TokenId.TOK_NUM, expected2.getId());
        assertEquals(Token.TokenId.TOK_OP, expected1.getId());
}

    /**
     * A test of setting value to the token
     */
    @Test
    public void testSetNum() {
        Token expected1 = new Token(Token.TokenId.TOK_NUM, 1.0);

        assertEquals(1.0, expected1.getNum(), 0.0);

        expected1.setNum(2.0);

        assertEquals(2.0, expected1.getNum(), 0.0);
    }

}