package ru.spbau.kirilenko.hw6FP;

import org.jetbrains.annotations.NotNull;

/**
 * Interface that represents function with boolean return value
 * @param <X> argument value type
 */
public interface Predicate<X> extends Function1<X, Boolean> {
    /**
     * A method that makes new predicate from two others and it's return value is logical "or" of the return values of this two predicates
     * @param predicate second predicate
     * @param <Y> argument type of the second predicate
     * @return new predicate "or"
     */
    @NotNull
    default <Y extends X> Predicate<Y> or(@NotNull Predicate<Y> predicate) {
        return (x) -> this.apply(x).booleanValue() || predicate.apply(x).booleanValue();
    }

    /**
     * A method that makes new predicate from two others and it's return value is logical "and" of the return values of this two predicates
     * @param predicate second predicate
     * @param <Y> argument type of the second predicate
     * @return new predicate "and"
     */
    @NotNull
    default <Y extends X> Predicate<Y> and(@NotNull Predicate<Y> predicate) {
        return (x) -> this.apply(x).booleanValue() && predicate.apply(x).booleanValue();
    }

    /**
     * A method that makes new predicate and it's return value is logical "not" of the return value of current predicate
     * @return new predicate "not"
     */
    @NotNull
    default Predicate<X> not() {
        return (x) -> !this.apply(x).booleanValue();
    }

    /**
     * A method that creates predicate, that returns true always
     * @param <X> argument value type
     * @return new predicate
     */
    @NotNull
    static <X> Predicate<X> alwaysTrue() {
        return (x) -> true;
    }

    /**
     * A method that creates predicate, that returns false always
     * @param <X> argument value type
     * @return new predicate
     */
    @NotNull
    static <X> Predicate<X> alwaysFalse() {
        return (x) -> false;
    }
}