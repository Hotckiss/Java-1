package ru.spbau.kirilenko.hw8MyCalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A class that tests all methods of stack
 */
public class MyStackTest {

    /**
     * Test of pushing to stack
     */
    @Test
    public void testPushPopSingle() {
        MyStack mystack = new MyStack();
        Tok expected = new Tok(Tok.Tokid.TOK_NUM, 1.0);

        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));

        assertTrue(expected.equals(mystack.pop()));
        assertNull(mystack.pop());

    }

    /**
     * Test of pushing and popping to stack
     */
    @Test
    public void testPushPopMulti() {
        MyStack mystack = new MyStack();
        Tok expected1 = new Tok(Tok.Tokid.TOK_NUM, 1.0);
        Tok expected2 = new Tok(Tok.Tokid.TOK_NUM, 2.0);

        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));
        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 2.0));

        assertTrue(expected2.equals(mystack.pop()));
        assertTrue(expected1.equals(mystack.pop()));
        assertNull(mystack.pop());
    }

    /**
     * Test of pushing and popping to stack
     */
    @Test
    public void testPushPopMultiAfterPop() {
        MyStack mystack = new MyStack();
        Tok expected1 = new Tok(Tok.Tokid.TOK_NUM, 1.0);
        Tok expected2 = new Tok(Tok.Tokid.TOK_NUM, 2.0);
        Tok expected3 = new Tok(Tok.Tokid.TOK_NUM, 3.0);
        Tok expected4 = new Tok(Tok.Tokid.TOK_NUM, 4.0);

        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));
        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 2.0));

        assertTrue(expected2.equals(mystack.pop()));
        assertTrue(expected1.equals(mystack.pop()));

        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 3.0));
        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 4.0));

        assertTrue(expected4.equals(mystack.pop()));
        assertTrue(expected3.equals(mystack.pop()));

        assertNull(mystack.pop());
    }

    /**
     * Test of emptiness of stack
     */
    @Test
    public void testEmpty() {
        MyStack mystack = new MyStack();

        assertTrue(mystack.empty());

        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));
        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 2.0));

        assertFalse(mystack.empty());

        mystack.pop();
        mystack.pop();

        assertTrue(mystack.empty());

        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 3.0));
        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 4.0));

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
        MyStack mystack = new MyStack();
        Tok expected1 = new Tok(Tok.Tokid.TOK_NUM, 1.0);
        Tok expected2 = new Tok(Tok.Tokid.TOK_NUM, 2.0);
        Tok expected4 = new Tok(Tok.Tokid.TOK_NUM, 4.0);

        assertNull(mystack.top());
        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));

        assertTrue(expected1.equals(mystack.top()));
        mystack.pop();

        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 2.0));

        assertTrue(expected2.equals(mystack.top()));
        mystack.pop();

        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 3.0));
        mystack.push(new Tok(Tok.Tokid.TOK_NUM, 4.0));

        assertTrue(expected4.equals(mystack.top()));
        mystack.pop();
    }
}