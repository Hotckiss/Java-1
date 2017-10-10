package ru.spbau.kirilenko;

/**
 * A class representing generic-set of comparable unique elements
 * Elements stored in non-balanced binary tree
 * @param <T> type od stored elements and they must be comparable
 */
public class MySet<T extends Comparable<T>> {
    private Node<T> root = new Node<>(null);
    private int size;

    /**
     * Method that adds new element in the Set
     * @param newElement element that should be added
     * @return true if element really was added to the tree, false if it was already in set
     */
    public boolean add(T newElement) {
        if (newElement == null) {
            throw new IllegalArgumentException("Bad element!");
        }

        if (root.data == null) {
            root.data = newElement;
            size++;
            
            return true;
        }

        Node<T> newNode = new Node<>(newElement);
        Node<T> last = root;
        Node<T> current = root;

        while (current != null) {
            last = current;

            if (current.compareTo(newNode) > 0) {
                current = current.getLeft();
            } else if (current.compareTo(newNode) < 0) {
                current = current.getRight();
            } else {
                return false;
            }
        }

        if(last.compareTo(newNode) > 0) {
            last.setLeft(newNode);
        } else {
            last.setRight(newNode);
        }
        size++;

        return true;
    }

    /**
     * Method that checks if the element stored in the Set
     * @param element element that should be found in the set
     * @return true if element really was in the tree, false otherwise
     */
    public boolean contains(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Bad element!");
        }

        if (root.isNull()) {
            return false;
        }

        Node<T> containsNode = new Node<>(element);
        Node<T> current = root;

        while (current != null) {
            if (current.compareTo(containsNode) > 0) {
                current = current.getLeft();
            } else if (current.compareTo(containsNode) < 0) {
                current = current.getRight();
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * Method that returns current number of elements in the Set
     * @return number of stored elements
     */
    public int size() {
        return size;
    }

    private static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        private T data;
        private Node<T> left = null;
        private Node<T> right = null;

        public Node(T element) {
            data = element;
        }

        public boolean isNull() {
            return data == null;
        }

        public Node<T> getLeft() {
            return left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setLeft(Node<T> node) {
            left = node;
        }

        public void setRight(Node<T> node) {
            right = node;
        }

        @Override
        public int compareTo(Node<T> o) {
            return data.compareTo(o.data);
        }
    }
}
