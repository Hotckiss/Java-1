package ru.spbau.kirilenko.hw8MyCalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A class that tests all methods of queue
 */
public class TokenQueueTest {

    /**
     * Test of pushing to queue
     */
    @Test
    public void testPushPopSingle() {
        TokenQueue myqueue = new TokenQueue();
        Token expected = new Token(Token.TokenId.TOK_NUM, 1.0);

        myqueue.push(new Token(Token.TokenId.TOK_NUM, 1.0));

        assertTrue(expected.equals(myqueue.pop()));
        assertNull(myqueue.pop());

    }

    /**
     * Test of pushing to queue
     */
    @Test
    public void testPushPopMulti() {
        TokenStack myqueue = new TokenStack();
        Token expected1 = new Token(Token.TokenId.TOK_NUM, 1.0);
        Token expected2 = new Token(Token.TokenId.TOK_NUM, 2.0);

        myqueue.push(new Token(Token.TokenId.TOK_NUM, 1.0));
        myqueue.push(new Token(Token.TokenId.TOK_NUM, 2.0));

        assertTrue(expected2.equals(myqueue.pop()));
        assertTrue(expected1.equals(myqueue.pop()));
        assertNull(myqueue.pop());
    }

    /**
     * Test of pushing to queue
     */
    @Test
    public void testPushPopMultiAfterPop() {
        TokenQueue myqueue = new TokenQueue();
        Token expected1 = new Token(Token.TokenId.TOK_NUM, 1.0);
        Token expected2 = new Token(Token.TokenId.TOK_NUM, 2.0);
        Token expected3 = new Token(Token.TokenId.TOK_NUM, 3.0);
        Token expected4 = new Token(Token.TokenId.TOK_NUM, 4.0);

        myqueue.push(new Token(Token.TokenId.TOK_NUM, 1.0));
        myqueue.push(new Token(Token.TokenId.TOK_NUM, 2.0));

        assertTrue(expected1.equals(myqueue.pop()));
        assertTrue(expected2.equals(myqueue.pop()));

        myqueue.push(new Token(Token.TokenId.TOK_NUM, 3.0));
        myqueue.push(new Token(Token.TokenId.TOK_NUM, 4.0));

        assertTrue(expected3.equals(myqueue.pop()));
        assertTrue(expected4.equals(myqueue.pop()));

        assertNull(myqueue.pop());
    }

    /**
     * Test of emptiness of stack
     */
    @Test
    public void testEmpty() {
        TokenQueue tokenQueue = new TokenQueue();

        assertTrue(tokenQueue.empty());

        tokenQueue.push(new Token(Token.TokenId.TOK_NUM, 1.0));
        tokenQueue.push(new Token(Token.TokenId.TOK_NUM, 2.0));

        assertFalse(tokenQueue.empty());

        tokenQueue.pop();
        tokenQueue.pop();

        assertTrue(tokenQueue.empty());

        tokenQueue.push(new Token(Token.TokenId.TOK_NUM, 3.0));
        tokenQueue.push(new Token(Token.TokenId.TOK_NUM, 4.0));

        assertFalse(tokenQueue.empty());

        tokenQueue.pop();
        tokenQueue.pop();

        assertTrue(tokenQueue.empty());
    }
}