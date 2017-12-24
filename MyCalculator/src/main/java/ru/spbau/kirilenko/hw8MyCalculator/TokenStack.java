package ru.spbau.kirilenko.hw8MyCalculator;

/**
 * A class that represents a simple stack structure
 */
public class TokenStack {
    private Node top = null;

    /**
     * Method that push element to the top of the stack
     * @param t new element
     */
    public void push(Token t) {
        Node newNode = new Node(t, top);
        top = newNode;
    }

    /**
     * Method that drop top element of stack
     */
    public Token pop() {
        if (top == null) {
            return null;
        } else {
            Token result = top.token;
            top = top.next;
            return result;
        }
    }

    /**
     * Method that shows top element of stack
     */
    public Token top() {
        if (top == null) {
            return null;
        }
        return top.token;
    }

    /**
     * Method that tells is stack empty
     * @return true if empty false otherwise
     */
    public boolean empty() {
        return top == null;
    }

    private static class Node {
        private Token token;
        private Node next;

        Node(Token token, Node next) {
            this.next = next;
            this.token = token;
        }
    }

}
