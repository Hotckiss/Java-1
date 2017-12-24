package ru.spbau.kirilenko.hw8MyCalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A class that tests all methods of stack
 */
public class TokenStackTest {

    /**
     * Test of pushing to stack
     */
    @Test
    public void testPushPopSingle() {
        TokenStack mystack = new TokenStack();
        Token expected = new Token(Token.TokenId.TOK_NUM, 1.0);

        mystack.push(new Token(Token.TokenId.TOK_NUM, 1.0));

        assertTrue(expected.equals(mystack.pop()));
        assertNull(mystack.pop());

    }

    /**
     * Test of pushing and popping to stack
     */
    @Test
    public void testPushPopMulti() {
        TokenStack mystack = new TokenStack();
        Token expected1 = new Token(Token.TokenId.TOK_NUM, 1.0);
        Token expected2 = new Token(Token.TokenId.TOK_NUM, 2.0);

        mystack.push(new Token(Token.TokenId.TOK_NUM, 1.0));
        mystack.push(new Token(Token.TokenId.TOK_NUM, 2.0));

        assertTrue(expected2.equals(mystack.pop()));
        assertTrue(expected1.equals(mystack.pop()));
        assertNull(mystack.pop());
    }

    /**
     * Test of pushing and popping to stack
     */
    @Test
    public void testPushPopMultiAfterPop() {
        TokenStack mystack = new TokenStack();
        Token expected1 = new Token(Token.TokenId.TOK_NUM, 1.0);
        Token expected2 = new Token(Token.TokenId.TOK_NUM, 2.0);
        Token expected3 = new Token(Token.TokenId.TOK_NUM, 3.0);
        Token expected4 = new Token(Token.TokenId.TOK_NUM, 4.0);

        mystack.push(new Token(Token.TokenId.TOK_NUM, 1.0));
        mystack.push(new Token(Token.TokenId.TOK_NUM, 2.0));

        assertTrue(expected2.equals(mystack.pop()));
        assertTrue(expected1.equals(mystack.pop()));

        mystack.push(new Token(Token.TokenId.TOK_NUM, 3.0));
        mystack.push(new Token(Token.TokenId.TOK_NUM, 4.0));

        assertTrue(expected4.equals(mystack.pop()));
        assertTrue(expected3.equals(mystack.pop()));

        assertNull(mystack.pop());
    }

    /**
     * Test of emptiness of stack
     */
    @Test
    public void testEmpty() {
        TokenStack mystack = new TokenStack();

        assertTrue(mystack.empty());

        mystack.push(new Token(Token.TokenId.TOK_NUM, 1.0));
        mystack.push(new Token(Token.TokenId.TOK_NUM, 2.0));

        assertFalse(mystack.empty());

        mystack.pop();
        mystack.pop();

        assertTrue(mystack.empty());

        mystack.push(new Token(Token.TokenId.TOK_NUM, 3.0));
        mystack.push(new Token(Token.TokenId.TOK_NUM, 4.0));

        assertFalse(mystack.empty());

        mystack.pop();
        mystack.pop();

        assertTrue(mystack.empty());
    }

    /**
     * Test of getting top value from stack
     */
    @Test
    public void testTop() {
        TokenStack mystack = new TokenStack();
        Token expected1 = new Token(Token.TokenId.TOK_NUM, 1.0);
        Token expected2 = new Token(Token.TokenId.TOK_NUM, 2.0);
        Token expected4 = new Token(Token.TokenId.TOK_NUM, 4.0);

        assertNull(mystack.top());
        mystack.push(new Token(Token.TokenId.TOK_NUM, 1.0));

        assertTrue(expected1.equals(mystack.top()));
        mystack.pop();

        mystack.push(new Token(Token.TokenId.TOK_NUM, 2.0));

        assertTrue(expected2.equals(mystack.top()));
        mystack.pop();

        mystack.push(new Token(Token.TokenId.TOK_NUM, 3.0));
        mystack.push(new Token(Token.TokenId.TOK_NUM, 4.0));

        assertTrue(expected4.equals(mystack.top()));
        mystack.pop();
    }
}