package ru.spbau.kirilenko.hw5Maybe;

/**
 * An exeption class that represents all errors connected with Maybe class
 */
public class MaybeException extends Exception {
    /**
     * Constructs new MaybeException with string representation of the error
     * @param message string with error
     */
    public MaybeException(String message) {
        super(message);
    }

}
