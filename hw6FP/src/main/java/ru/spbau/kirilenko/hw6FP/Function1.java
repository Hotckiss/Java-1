package ru.spbau.kirilenko.hw6FP;

import org.jetbrains.annotations.NotNull;

/**
 * Interface of the single argument function
 * @param <X> argument type
 * @param <T> return value type
 */
public interface Function1<X, T> {
    /**
     * A method that should apply the function to the argument
     * @param x argument of the function
     * @return returns the result of applying function to the argument
     */
    T apply(X x);

    /**
     * A method that creates a new function applying other function to the result of this function
     * @param function function that should be applied to the result of current function
     * @param <A> return value type of the composition of the functions
     * @return returns a function that is result of the composition
     */
    @NotNull
    default <A> Function1<X, A> compose(@NotNull Function1<? super T, A> function) {
        return (argument) -> function.apply(this.apply(argument));
    }
}