package ru.spbau.kirilenko.hw2;

/**
 * An interface represents methods which must be implemented in the storage of string pairs
 *
 * @author Kirilenko Andrey
 */

public interface KeyValueStorage {
    /**
     * A method that should return current number of elements in the storage
     * @return current number of elements in the storage
     */
    int size();

    /**
     * A method that should return true if storage contains element with the input key
     * @param key that should be found
     * @return true if element with this key is in storage, false otherwise
     */
    boolean contains(String key);

    /**
     * A method that should return element that matches to the input key
     * @param key that should be matched to the elements of the storage
     * @return element matched to this key in storage, null otherwise
     */
    String get(String key);

    /**
     * A method that add an element to the storage and connect key to the value
     * @param key which should be added
     * @param value matched to the key
     * @return previous value matched to this key, null otherwise
     */
    String put(String key, String value);

    /**
     * A method that remove an element from the storage
     * @param key that should be found and deleted
     * @return deleted element if it exists, null otherwise
     */
    String remove(String key);

    /**
     * A method that removes all elements from the storage
     */
    void clear();
}
