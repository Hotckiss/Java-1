package ru.spbau.kirilenko.hw5Maybe;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * A class that stores some value, or not stores it
 * @param <T> type of stored value
 */
public class Maybe<T> {
    private @Nullable T value;

    /**
     * This method constructs new Maybe class that stores input value
     * @param t valuse that should be stored
     * @param <T> value type
     * @return new Maybe that stores value
     */
    public static <T> Maybe<T> just(@NotNull T t) {
        return new Maybe<>(t);
    }

    /**
     * This method constructs new Maybe class that does not stores any value
     * @param <T> value type
     * @return new Maybe with no value inside
     */
    public static <T> Maybe<T> nothing() {
        return new Maybe<>(null);
    }

    /**
     * A method that returns stored value if it is exists
     * @return stored value
     * @throws MaybeException if there is not value in this Maybe
     */
    public T get() throws MaybeException {
        if (value == null) {
            throw new MaybeException("Value not found!");
        }

        return value;
    }

    /**
     * A method that checks that this Maybe stores value
     * @return true if this Maybe contains value, false otherwise
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * A method that apply function to the stored value and returns new Maybe that stores the result
     * @param mapper function that should be applied
     * @param <U> value type of new Maybe
     * @return new Maybe with the result
     */
    public <U> Maybe<U> map(Function<T, U> mapper) {
        if (!isPresent()) {
            return new Maybe<>(null);
        }

        return new Maybe<>(mapper.apply(value));
    }

    private Maybe(@Nullable T val) {
        value = val;
    }
}
