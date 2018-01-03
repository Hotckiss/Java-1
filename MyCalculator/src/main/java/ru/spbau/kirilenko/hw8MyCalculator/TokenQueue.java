package ru.spbau.kirilenko.hw8MyCalculator;

/**
 * A class that represents a simple queue structure
 */
public class TokenQueue {

    private Node head;
    private Node tail;

    /**
     * Method that push element to the end of the queue
     * @param t new element
     */
    public void push(Token t) {
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
    public Token pop() {
        if (head == null) {
            return null;
        } else if (head == tail) {
            Token result = head.token;
            head = null;
            tail = null;
            return result;
        } else {
            Token result = head.token;
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

    private static class Node {
        private Token token;
        private Node next;

        Node(Token token, Node next) {
            this.next = next;
            this.token = token;
        }
    }

}
