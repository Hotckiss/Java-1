package ru.spbau.kirilenko.hw2;

/**
 * A class representing a simply-connecting list of KeyValuePairs
 */

public class LinkedList implements KeyValueStorage{
    private LinkedListNode head;
    private int length;

    /**
     * Build empty list
     */
    public LinkedList() {
        head = new LinkedListNode();
        length = 0;
    }

    /**
     * Returns size of list.
     *
     * @return number of elements currently stored in the list
     */
    public int size() {
        return length;
    }

    /**
     * Remove all pairs from thr list
     */
    public void clear() {
        head.setNext(null);
        length = 0;
    }

    /**
     * Method looks for a pair with predetermined key
     *
     * @param key which is need to be found
     * @return true if list contains pair with this key, false otherwise
     */
    public boolean contains(String key) {
        return findElementByKey(key).getNext() != null;
    }

    /**
     * Method looks for a pair with predetermined key and returns it's value
     *
     * @param key which is need to be found
     * @return value if list contains pair with this key, null otherwise
     */
    public String get(String key) {
        LinkedListNode node = findElementByKey(key).getNext();

        if(node == null) {
            return null;
        }

        return node.getKvp().getValue();
    }

    /**
     * Adding a pair with predetermined key and value
     *
     * @throws IllegalArgumentException if null key input
     *
     * @param key which is need to be added
     * @param value which connected with the key
     * @return previous string connected with that key in the list, null if key was not in the list
     */
    public String put(String key, String value) {
        if(key == null) {
            throw new IllegalArgumentException("Bad key.");
        }

        LinkedListNode it = findElementByKey(key);

        if(it.getNext() == null) {
            LinkedListNode node = new LinkedListNode(new KeyValuePair(key, value), null);
            it.setNext(node);
            length++;

            return null;
        } else {
            String res = it.getNext().getKvp().getValue();
            it.getNext().getKvp().setNewValue(value);

            return res;
        }
    }

    /**
     * Method that removes pair connected with input key from the list
     *
     * @throws IllegalArgumentException if null key input
     *
     * @param key which is need to be removed
     * @return value if list contains pair with this key, null otherwise
     */
    public String remove(String key) {
        if(key == null) {
            throw new IllegalArgumentException("Bad key.");
        }

        LinkedListNode node = findElementByKey(key);

        if(!node.hasNext()) {
            return null;
        }

        String res = node.getNext().getKvp().getValue();
        node.setNext(node.getNext().getNext());
        length--;

        return res;
    }

    /**
     * Method that removes first pair from the list
     * @return first pair of the list
     */
    public KeyValuePair popFront() {
        if(!head.hasNext()) {
            return null;
        }

        KeyValuePair res = head.getNext().getKvp();
        head.setNext(head.getNext().getNext());
        length--;

        return res;
    }

    private LinkedListNode findElementByKey(String key) {
        LinkedListNode it = head;

        while(it.hasNext()) {
            if(it.getNext().getKvp().getKey().equals(key)) {
                break;
            }

            it = it.getNext();
        }
        return it;
    }

    private static class LinkedListNode {
        private KeyValuePair kvp;
        private LinkedListNode next;

        public LinkedListNode() {
            kvp = null;
            next = null;
        }

        public LinkedListNode(KeyValuePair kvp, LinkedListNode next) {
            this.kvp = kvp;
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public KeyValuePair getKvp() {
            return kvp;
        }

        public LinkedListNode getNext() {
            return next;
        }

        public void setNext(LinkedListNode next) {
            this.next = next;
        }

        public void setKvp(KeyValuePair kvp) {
            this.kvp = kvp;
        }
    }
}
