package ru.spbau.kirilenko.hw6FP;

import org.jetbrains.annotations.NotNull;

/**
 * Interface of the two-argument function
 * @param <X> type of the first argument
 * @param <Y> type of the second argument
 * @param <T> return value type
 */
public interface Function2<X, Y, T> {
    /**
     * A method that should apply the function to the argument
     * @param x first argument of the function
     * @param y second argument of the function
     * @return returns the result of applying function to the arguments
     */
    T apply(X x, Y y);

    /**
     * A method that creates a new function applying other function to the result of this function
     * @param function function that should be applied to the result of current function
     * @param <A> return value type of the composition of the functions
     * @return returns a function that is result of the composition
     */
    @NotNull
    default <A> Function2<X, Y, A> compose(@NotNull Function1<? super T, A> function) {
        return (x, y) -> function.apply(this.apply(x, y));
    }

    /**
     * A method that creates single argument function from two-argument function. It puts input argument as the first
     * @param x placed argument
     * @return single argument function
     */
    @NotNull
    default Function1<Y, T> bind1(X x) {
        return (y) -> this.apply(x, y);
    }

    /**
     * A method that creates single argument function from two-argument function. It puts input argument as the second
     * @param y placed argument
     * @return single argument function
     */
    @NotNull
    default Function1<X, T> bind2(Y y) {
        return (x) -> this.apply(x, y);
    }

    /**
     * A method that carries function of two arguments
     * @return carried function
     */
    @NotNull
    default Function1<Y, Function1<X, T>> curry() {
        return this::bind2;
    }
}