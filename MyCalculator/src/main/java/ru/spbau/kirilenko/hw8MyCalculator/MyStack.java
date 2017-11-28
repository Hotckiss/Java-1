package ru.spbau.kirilenko.hw8MyCalculator;

/**
 * A class that represents a simple stack structure
 */
public class MyStack {
    private Node top = null;

    /**
     * Method that push element to the top of the stack
     * @param t new element
     */
    public void push(Tok t) {
        Node newNode = new Node(t, top);
        top = newNode;
    }

    /**
     * Method that drop top element of stack
     */
    public Tok pop() {
        if (top == null) {
            return null;
        } else {
            Tok result = top.tok;
            top = top.next;
            return result;
        }
    }

    /**
     * Method that shows top element of stack
     */
    public Tok top() {
        if (top == null) {
            return null;
        }
        return top.tok;
    }

    /**
     * Method that tells is stack empty
     * @return true if empty false otherwise
     */
    public boolean empty() {
        return top == null;
    }

    private class Node {
        Tok tok = null;
        Node next = null;

        Node(Tok tok, Node next) {
            this.next = next;
            this.tok = tok;
        }
    }

}
