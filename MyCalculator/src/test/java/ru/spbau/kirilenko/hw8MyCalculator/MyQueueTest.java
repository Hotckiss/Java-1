package ru.spbau.kirilenko.hw8MyCalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A class that tests all methods of queue
 */
public class MyQueueTest {

    /**
     * Test of pushing to queue
     */
    @Test
    public void testPushPopSingle() {
        MyQueue myqueue = new MyQueue();
        Tok expected = new Tok(Tok.Tokid.TOK_NUM, 1.0);

        myqueue.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));

        assertTrue(expected.equals(myqueue.pop()));
        assertNull(myqueue.pop());

    }

    /**
     * Test of pushing to queue
     */
    @Test
    public void testPushPopMulti() {
        MyStack myqueue = new MyStack();
        Tok expected1 = new Tok(Tok.Tokid.TOK_NUM, 1.0);
        Tok expected2 = new Tok(Tok.Tokid.TOK_NUM, 2.0);

        myqueue.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));
        myqueue.push(new Tok(Tok.Tokid.TOK_NUM, 2.0));

        assertTrue(expected2.equals(myqueue.pop()));
        assertTrue(expected1.equals(myqueue.pop()));
        assertNull(myqueue.pop());
    }

    /**
     * Test of pushing to queue
     */
    @Test
    public void testPushPopMultiAfterPop() {
        MyQueue myqueue = new MyQueue();
        Tok expected1 = new Tok(Tok.Tokid.TOK_NUM, 1.0);
        Tok expected2 = new Tok(Tok.Tokid.TOK_NUM, 2.0);
        Tok expected3 = new Tok(Tok.Tokid.TOK_NUM, 3.0);
        Tok expected4 = new Tok(Tok.Tokid.TOK_NUM, 4.0);

        myqueue.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));
        myqueue.push(new Tok(Tok.Tokid.TOK_NUM, 2.0));

        assertTrue(expected1.equals(myqueue.pop()));
        assertTrue(expected2.equals(myqueue.pop()));

        myqueue.push(new Tok(Tok.Tokid.TOK_NUM, 3.0));
        myqueue.push(new Tok(Tok.Tokid.TOK_NUM, 4.0));

        assertTrue(expected3.equals(myqueue.pop()));
        assertTrue(expected4.equals(myqueue.pop()));

        assertNull(myqueue.pop());
    }

    /**
     * Test of emptiness of stack
     */
    @Test
    public void testEmpty() {
        MyQueue myQueue = new MyQueue();

        assertTrue(myQueue.empty());

        myQueue.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));
        myQueue.push(new Tok(Tok.Tokid.TOK_NUM, 2.0));

        assertFalse(myQueue.empty());

        myQueue.pop();
        myQueue.pop();

        assertTrue(myQueue.empty());

        myQueue.push(new Tok(Tok.Tokid.TOK_NUM, 3.0));
        myQueue.push(new Tok(Tok.Tokid.TOK_NUM, 4.0));

        assertFalse(myQueue.empty());

        myQueue.pop();
        myQueue.pop();

        assertTrue(myQueue.empty());
    }
}