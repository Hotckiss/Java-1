package ru.spbau.hw7MyTreeSet;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * An interface of key value tree set with iterators
 * @param <E> type of stored values
 */
public interface MyTreeSet<E> extends Set<E> {

    /** {@link TreeSet#descendingIterator()} **/
    Iterator<E> descendingIterator();

    /** {@link TreeSet#descendingSet()} **/
    MyTreeSet<E> descendingSet();


    /** {@link TreeSet#first()} **/
    E first();

    /** {@link TreeSet#last()} **/
    E last();


    /** {@link TreeSet#lower(E)} **/
    E lower(E e);

    /** {@link TreeSet#floor(E)} **/
    E floor(E e);


    /** {@link TreeSet#ceiling(E)} **/
    E ceiling(E e);

    /** {@link TreeSet#higher(E)} **/
    E higher(E e);
}