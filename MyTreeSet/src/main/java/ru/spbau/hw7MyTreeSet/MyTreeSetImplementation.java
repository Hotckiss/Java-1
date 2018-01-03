package ru.spbau.hw7MyTreeSet;


import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A class that implements MyTreeSet interface using binary tree
 * @param <K> type of stored elements
 */
public class MyTreeSetImplementation<K> extends AbstractSet<K> implements MyTreeSet<K> {

    private int size = 0;
    private Node root = null;
    private Node minNode = null;
    private Node maxNode = null;
    private Comparator<? super K> comparator;
    private long version = 0;
    /**
     * Constructor without comparator - we will use standard
     */
    public MyTreeSetImplementation() {
        this.comparator = (a, b) -> ((Comparable)a).compareTo(b);
    }

    /**
     * Constructor with comparator
     */
    public MyTreeSetImplementation(@NotNull Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    /**
     * Add to tree method a new value
     * @param k - key to add
     * @returns true if element was added, false otherwise
     */
    @Override
    public boolean add(K k) {
        Node was = findNodeByKey(k);
        if (was != null) {
            return false;
        } else {
            if (root == null) {
                root = new Node(k);
            } else {
                addToSubTree(root, k);
            }
            updateMax();
            updateMin();
            size++;
            version++;
            return true;
        }
    }

    /**
     * Method that check that value contained in the tree
     * @param k - key to search
     * @returns true if element is in tree, false otherwise
     */
    @Override
    public boolean contains(Object k) {
        return findNodeByKey(k) != null;
    }

    /* Kill me, please */

    /**
     * Method that removes node from a tree
     * @param k key to delete
     * @return true if element was deleted, false otherwise
     */
    @Override
    public boolean remove(Object k) {
        Node toRemove = findNodeByKey(k);
        if (toRemove == null) {
            return false;
        }
        if (toRemove == root) {
            if ((toRemove.left == null) && (toRemove.right == null)) {
                root = null;
            } else if (toRemove.left == null) {
                root = root.right;
                root.parent = null;
            } else if (toRemove.right == null) {
                root = root.left;
                root.parent = null;
            } else {
                Node ptr = root.right;
                while (ptr.left != null) {
                    ptr = ptr.left;
                }
                ptr.left = root.left;
                root.left.parent = ptr;
                root = root.right;
            }
        } else {
            if ((toRemove.left == null) && (toRemove.right == null)) {
                if (comparator.compare((K)toRemove.parent.key, (K)toRemove.key) > 0) {
                    toRemove.parent.left = null;
                } else {
                    toRemove.parent.right = null;
                }
            } else if (toRemove.left == null) {
                if (comparator.compare((K)toRemove.parent.key, (K)toRemove.key) > 0) {
                    toRemove.parent.left = toRemove.right;
                } else {
                    toRemove.parent.right = toRemove.right;
                }
                toRemove.right.parent = toRemove.parent;
            } else if (toRemove.right == null) {
                if (comparator.compare((K)toRemove.parent.key, (K)toRemove.key) > 0) {
                    toRemove.parent.left = toRemove.left;
                } else {
                    toRemove.parent.right = toRemove.left;
                }
                toRemove.left.parent = toRemove.parent;
            } else {
                Node ptr = toRemove.right;
                while (ptr.left != null) {
                    ptr = ptr.left;
                }
                ptr.left = toRemove.left;
                toRemove.left.parent = ptr;
                ptr = toRemove.right;
                if (comparator.compare((K)toRemove.parent.key, (K)ptr.key) > 0) {
                    toRemove.parent.left = ptr;
                } else {
                    toRemove.parent.right = ptr;
                }
            }
        }
        updateMax();
        updateMin();
        size--;
        version++;
        return true;
    }

    /**
     * Returns an ascending iterator of a tree, complexity: O(size) for all elements
     * @return Iterator of a tree
     */
    @Override
    public Iterator<K> iterator() {
        return new IncreadingIterator<>();
    }

    /**
     * Returns an descending iterator of a tree, complexity: O(size) for all elements
     * @return Descending iterator of a tree
     */
    @Override
    public Iterator<K> descendingIterator() {
        return new DecreadingIterator<>();
    }

    /**
     * Returns a size of a tree
     * @return size of a tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an descending set tree constructed from a current tree
     * @return Descending tree of a current tree
     */
    @Override
    public MyTreeSet<K> descendingSet() {
        return new MyTreeSet<K>() {
            @Override
            public Iterator<K> descendingIterator() {
                return MyTreeSetImplementation.this.iterator();
            }

            @NotNull
            @Override
            public Iterator<K> iterator() {
                return MyTreeSetImplementation.this.descendingIterator();
            }

            @Override
            public MyTreeSet<K> descendingSet() {
                return MyTreeSetImplementation.this;
            }

            @Override
            public K first() {
                return MyTreeSetImplementation.this.last();
            }

            @Override
            public K last() {
                return MyTreeSetImplementation.this.first();
            }

            @Override
            public K lower(K e) {
                return MyTreeSetImplementation.this.higher(e);
            }

            @Override
            public K floor(K e) {
                return MyTreeSetImplementation.this.ceiling(e);
            }

            @Override
            public K ceiling(K e) {
                return MyTreeSetImplementation.this.floor(e);
            }

            @Override
            public K higher(K e) {
                return MyTreeSetImplementation.this.lower(e);
            }

            @Override
            public int size() {
                return MyTreeSetImplementation.this.size();
            }

            @Override
            public boolean isEmpty() {
                return MyTreeSetImplementation.this.isEmpty();
            }

            @Override
            public boolean contains(Object o) {
                return MyTreeSetImplementation.this.contains(o);
            }

            @NotNull
            @Override
            public Object[] toArray() {
                return MyTreeSetImplementation.this.toArray();
            }

            @NotNull
            @Override
            public <T> T[] toArray(@NotNull T[] a) {
                return MyTreeSetImplementation.this.toArray(a);
            }

            @Override
            public boolean add(K e) {
                return MyTreeSetImplementation.this.add(e);
            }

            @Override
            public boolean remove(Object o) {
                return MyTreeSetImplementation.this.remove(o);
            }

            @Override
            public boolean containsAll(@NotNull Collection<?> c) {
                return MyTreeSetImplementation.this.containsAll(c);
            }

            @Override
            public boolean addAll(@NotNull Collection<? extends K> c) {
                return MyTreeSetImplementation.this.addAll(c);
            }

            @Override
            public boolean retainAll(@NotNull Collection<?> c) {
                return MyTreeSetImplementation.this.retainAll(c);
            }

            @Override
            public boolean removeAll(@NotNull Collection<?> c) {
                return MyTreeSetImplementation.this.removeAll(c);
            }

            @Override
            public void clear() {
                MyTreeSetImplementation.this.clear();
            }
        };
    }

    /**
     * Method that returns the first key of tree in order of comparing
     * @return first key
     */
    @Override
    public K first() {
        return (minNode == null) ? null : (K)minNode.key;
    }

    /**
     * Method that returns the last key of tree in order of comparing
     * @return last key
     */
    @Override
    public K last() {
        return (maxNode == null) ? null : (K)maxNode.key;
    }

    /**
     * Method that returns the biggest key that less than k or null if it does not exists
     * @param k key to find lower
     * @return key that is biggest but less than k or null if it does not exists
     */
    @Override
    public K lower(K k) {
        return lower(root, k);
    }

    /**
     * Method that returns the biggest key that less or equals than k or null if it does not exists
     * @param k key to find floor
     * @return key that is biggest but less or equals than k or null if it does not exists
     */
    @Override
    public K floor(K k) {
        Node node = findNodeByKey(k);
        if (node != null) {
            return k;
        }
        return lower(k);
    }

    /**
     * Method that returns the lowest key that bigger or equals than k or null if it does not exists
     * @param k key to find ceiling
     * @return key that is lowest but bigger or equals than k or null if it does not exists
     */
    @Override
    public K ceiling(K k) {
        Node node = findNodeByKey(k);
        if (node != null) {
            return k;
        }
        return higher(k);
    }

    /**
     * Method that returns the lowest key that bigger than k or null if it does not exists
     * @param k key to find higher
     * @return key that is lowest but bigger than k or null if it does not exists
     */
    @Override
    public K higher(K k) {
        return higher(root, k);
    }

    private void addToSubTree (@NotNull Node node, K k) {
        int cmp = comparator.compare((K)node.key, k);
        if (cmp > 0) {
            if (node.left == null) {
                node.left = new Node(k, node);
            } else {
                addToSubTree(node.left, k);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(k, node);
            } else {
                addToSubTree(node.right, k);
            }
        }
    }

    private void updateMin() {
        if (root == null) {
            minNode = null;
            return;
        }
        minNode = root;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
    }

    private void updateMax() {
        if (root == null) {
            maxNode = null;
            return;
        }
        maxNode = root;
        while (maxNode.right != null) {
            maxNode = maxNode.right;
        }
    }

    private class IncreadingIterator<K> extends AbstractIterator<K> {
        IncreadingIterator() {
            super.next = minNode;
        }
        @Override
        public K next() {
            if (!super.isValid()) {
                throw new IllegalStateException();
            }

            K returnValue = (K)super.next.key;
            super.lastReturned = super.next;
            super.next = nextNode(super.next);
            return returnValue;
        }
        public void remove() {
            super.remove();
            super.next = minNode;
        }
    }

    private class DecreadingIterator<K> extends AbstractIterator<K> {
        DecreadingIterator() {
            super.next = maxNode;
        }
        @Override
        public K next() {
            if (!super.isValid()) {
                throw new IllegalStateException();
            }

            K returnValue = (K)super.next.key;
            super.lastReturned = super.next;
            super.next = prevNode(super.next);
            return returnValue;
        }
        public void remove() {
            super.remove();
            super.next = maxNode;
        }
    }

    private abstract class AbstractIterator<K> implements Iterator<K> {
        private Node next;
        private Node lastReturned = null;
        private long ver = version;
        @Override
        public boolean hasNext() {
            if (!isValid()) {
                throw new IllegalStateException();
            }

            return next != null;
        }

        @Override
        public void remove() {
            if (!isValid()) {
                throw new IllegalStateException();
            }

            if (lastReturned != null) {
                MyTreeSetImplementation.this.remove(lastReturned.key);
            }
            lastReturned = null;
            ver = version;
        }

        private boolean isValid() {
            return ver == version;
        }
    }

    private Node nextNode(Node curNode) {
        if (curNode.right != null) {
            Node next = curNode.right;
            while (next.left != null) {
                next = next.left;
            }

            return next;
        } else if (curNode.parent != null) {
            if (comparator.compare((K)curNode.key, (K)curNode.parent.key) < 0) {
                return curNode.parent;
            }
            else {
                Node ptr = curNode;
                while (ptr.parent != null && comparator.compare((K)ptr.key, (K)ptr.parent.key) > 0) {
                    ptr = ptr.parent;
                }
                return ptr.parent;
            }
        } else {
            return null;
        }
    }

    private Node prevNode(Node curNode) {
        if (curNode.left != null) {
            Node next = curNode.left;
            while (next.right != null) {
                next = next.right;
            }
            return next;
        } else if (curNode.parent != null) {
            if (comparator.compare((K)curNode.key, (K)curNode.parent.key) > 0) {
                return curNode.parent;
            }
            else {
                Node ptr = curNode.parent;
                while (ptr.parent != null && comparator.compare((K)ptr.key, (K)ptr.parent.key) < 0) {
                    ptr = ptr.parent;
                }
                return ptr.parent;
            }
        } else {
            return null;
        }
    }

    private K lower(@NotNull Node node, K k) {
        Node ptr = node;
        while (ptr != null) {
            int cmp = comparator.compare((K)ptr.key, k);
            if (cmp < 0) {
                if (ptr.right == null) {
                    return (K)ptr.key;
                } else {
                    K retValue = lower(ptr.right, k);
                    if (retValue == null) {
                        return (K)ptr.key;
                    } else {
                        return retValue;
                    }
                }
            } else {
                ptr = ptr.left;
            }
        }
        return null;
    }

    private K higher(@NotNull Node node, K k) {
        Node ptr = node;
        while (ptr != null) {
            int cmp = comparator.compare((K)ptr.key, k);
            if (cmp > 0) {
                if (ptr.left == null) {
                    return (K)ptr.key;
                } else {
                    K retValue = higher(ptr.left, k);
                    if (retValue == null) {
                        return (K)ptr.key;
                    } else {
                        return retValue;
                    }

                }
            } else {
                ptr = ptr.right;
            }
        }
        return null;
    }
    private Node findNodeByKey(Object k) {
        Node ptr = root;
        if (ptr == null) {
            return null;
        }
        while (ptr != null) {
            int cmp = comparator.compare((K)ptr.key, (K)k);
            if (cmp > 0) {
                ptr = ptr.left;
            } else if (cmp < 0) {
                ptr = ptr.right;
            } else {
                return ptr;
            }
        }
        return ptr;
    }

    private static class Node<K> {
        private Node left = null;
        private Node right = null;
        private Node parent = null;
        private K key;

        public Node(K key) {
            this.key = key;
        }

        public Node(K key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
    }

}
