package ru.spbau.kirilenko.hw2;

/**
 * A class representing an extendable hash table of strings with keys of strings
 */

public class HashTable implements KeyValueStorage {
    private LinkedList[] cells;
    private int size = 0;

    /**
     * Build empty HashTable with 32 cells
     */
    public HashTable() {
        this(32);
    }

    /**
     * Build empty HashTable
     *
     * @throws IllegalArgumentException if non positive size input
     *
     * @param size number of cells in the HashTable
     */
    public HashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Too small size.");
        }

        cells = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            cells[i] = new LinkedList();
        }
    }

    /**
     * Returns current number of stored elements
     *
     * @return number of elements in the HashTable
     */
    public int size() {
        return size;
    }

    /**
     * Adding a pair with predetermined key and value
     *
     * @throws IllegalArgumentException if null key input
     *
     * @param key which is need to be added
     * @param value which connected with the key
     * @return previous string connected with that key in the HashTable, null if key was not in the list
     */
    public String put(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException("Bad key.");
        }

        if (cells.length == size) {
            reinitialize();
        }

        int hash = getHash(key);
        String previousValue = cells[hash].put(key, value);
        if (previousValue == null)
            size++;

        return previousValue;
    }

    /**
     * Returns a value connected with input key
     *
     * @throws IllegalArgumentException if null key input
     *
     * @param key which is need to be found
     * @return string connected with that key in the HashTable
     */
    public String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Bad key.");
        }

        int hash = getHash(key);

        return cells[hash].get(key);
    }

    /**
     * Checking an existance of input key in HashTable
     *
     * @throws IllegalArgumentException if null key input
     *
     * @param key which is need to be checked
     * @return true if key is stored in the HashTable, false otherwise
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Bad key.");
        }

        int hash = getHash(key);

        return cells[hash].contains(key);
    }

    /**
     * Delete all elements from structure
     */
    public void clear() {
        for (LinkedList cell : cells) {
            cell.clear();
        }

        size = 0;
    }

    /**
     * Removes a value connected with input key
     *
     * @throws IllegalArgumentException if null key input
     *
     * @param key which is need to be extracted
     * @return string connected with that key in the HashTable
     */
    public String remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Bad key.");
        }

        int hash = getHash(key);
        String previousValue = cells[hash].remove(key);
        if (previousValue != null)
            size--;

        return previousValue;
    }

    private int getHash(String key) {
        return (int)(((long)key.hashCode() + 0xffffffffL) % cells.length);
    }

    private void reinitialize() {
        LinkedList[] oldCells = cells;
        cells = new LinkedList[oldCells.length * 2];

        for (int i = 0; i < cells.length; i++) {
            cells[i] = new LinkedList();
        }

        for (int i = 0; i < oldCells.length; i++) {
            while (true) {
                KeyValuePair kvp = oldCells[i].popFront();
                if (kvp == null) {
                    break;
                }
                cells[getHash(kvp.getKey())].put(kvp.getKey(), kvp.getValue());
            }
        }
    }
}
