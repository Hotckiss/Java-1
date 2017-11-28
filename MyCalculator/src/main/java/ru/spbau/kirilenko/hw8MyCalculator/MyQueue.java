package ru.spbau.kirilenko.hw8MyCalculator;

/**
 * A class that represents a simple queue structure
 */
public class MyQueue {

    private Node head = null;
    private Node tail = null;

    /**
     * Method that push element to the end of the queue
     * @param t new element
     */
    public void push(Tok t) {
        if (head == null) {
            Node newNode = new Node(t, null);
            head = newNode;
            tail = newNode;
        } else {
            Node newNode = new Node(t, null);
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Method that drop first element of queue
     */
    public Tok pop() {
        if (head == null) {
            return null;
        } else if (head == tail) {
            Tok result = head.tok;
            head = null;
            tail = null;
            return result;
        } else {
            Tok result = head.tok;
            head = head.next;
            return result;
        }
    }

    /**
     * Method that tells is queue empty
     * @return true if empty false otherwise
     */
    public boolean empty()  {
        return head == null;
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
