package ru.spbau.kirilenko.hw2;

/**
 * An interface represents methods which must be implemented in the storage of string pairs
 *
 * @author Kirilenko Andrey
 */

public interface KeyValueStorage {
    int size();
    boolean contains(String key);
    String get(String key);
    String put(String key, String value);
    String remove(String key);
    void clear();
}
