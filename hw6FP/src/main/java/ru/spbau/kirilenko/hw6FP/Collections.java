package ru.spbau.kirilenko.hw6FP;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * A class that contains methods to work with collections in functional style
 */
public class Collections {
    /**
     * A method that takes collection and transform all it's elements using function
     * @param function function that transforms single element
     * @param collection storage of the elements
     * @param <X> type of stored elements
     * @param <T> type of transformed elements
     * @return List of transformed elements
     */
    @NotNull
    public static final <X, T> List<T> map(Function1<? super X, T> function, Iterable<X> collection) {
        List<T> answer = new ArrayList<>();

        for (X x : collection) {
            answer.add(function.apply(x));
        }

        return answer;
    }

    /**
     * A method that leaves only valid elements for the predicate
     * @param predicate predicate that validate elements
     * @param collection storage of the elements
     * @param <X> type of stored elements
     * @return List of elements that are valid for the predicate
     */
    @NotNull
    public static final <X> List<X> filter(Predicate<? super X> predicate, Iterable<X> collection) {
        List<X> answer = new ArrayList<>();

        for (X x : collection) {
            if (predicate.apply(x)) {
                answer.add(x);
            }
        }

        return answer;
    }

    /**
     * A method that adds elements from the collection to the list until the first invalid element
     * @param predicate validator of the elements
     * @param collection storage of the elements
     * @param <X> type of stored elements
     * @return List of elements that were before first invalid element
     */
    @NotNull
    public static final <X> List<X> takeWhile(@NotNull Predicate<? super X> predicate, @NotNull Iterable<X> collection) {
        List<X> answer = new ArrayList<>();

        for (X x : collection) {
            if (predicate.apply(x)) {
                answer.add(x);
            }
            else {
                return answer;
            }
        }

        return answer;
    }

    /**
     * A method that adds elements from the collection to the list until the first valid element
     * @param predicate validator of the elements
     * @param collection storage of the elements
     * @param <X> type of stored elements
     * @return List of elements that were before first valid element
     */
    @NotNull
    public static final <X> List<X> takeUnless(@NotNull Predicate<? super X> predicate, @NotNull Collection<X> collection) {
        return takeWhile(predicate.not(), collection);
    }

    /**
     * A method that folds list from the beginning and count some value in the accum
     * @param function function that should be applied to the accum and current element
     * @param accum value that should be counted
     * @param collection storage of the elements
     * @param <X> type of stored elements
     * @param <T> type of counted value
     * @return counted value
     */
    public static final <X, T> T foldl(@NotNull Function2<? super T, ? super X, ? extends T> function, T accum, @NotNull Collection<X> collection) {
        for (X x : collection) {
            accum = function.apply(accum, x);
        }

        return accum;
    }

    /**
     * A method that folds list from the end and count some value in the accum
     * @param function function that should be applied to the accum and current element
     * @param accum value that should be counted
     * @param collection storage of the elements
     * @param <X> type of stored elements
     * @param <T> type of counted value
     * @return counted value
     */
    public static final <X, T> T foldr(@NotNull Function2<? super X, ? super T, ? extends T> function, T accum, @NotNull Collection<X> collection) {
        List<X> list = new ArrayList<>();

        list.addAll(collection);

        ListIterator<X> it = list.listIterator(list.size());

        while (it.hasPrevious()) {
            accum = function.apply(it.previous(), accum);
        }

        return accum;
    }
}
