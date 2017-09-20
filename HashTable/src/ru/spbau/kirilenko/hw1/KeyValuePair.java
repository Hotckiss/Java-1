/**
 * A class representing a pair of two strings
 *
 * @author Kirilenko Andrey
 */

package ru.spbau.kirilenko.hw1;

public class KeyValuePair {
    private final String key;
    private String value;

    /**
     * Build empty pair with no key
     */
    public KeyValuePair() {
        key = null;
        value = null;
    }

    /**
     * Build a pair with predetermined key and value
     *
     * @throws IllegalArgumentException if null key input
     */
    public KeyValuePair(String key, String value) {
        if(key == null) {
            throw new IllegalArgumentException("Bad key.");
        }
        this.key = key;
        this.value = value;
    }

    /**
     * Method that returns pair key
     */
    public String getKey() {
        return key;
    }

    /**
     * Method that returns pair value
     */
    public String getValue() {
        return value;
    }

    /**
     * Method that sets new value in the pair
     *
     * @param value new value of the pair
     */
    public void setNewValue(String value) {
        this.value = value;
    }
}
