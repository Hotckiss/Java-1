package ru.spbau.kirilenko.hw3Trie;

import java.io.*;

/**
 * Interface of a class which can serialize and deserialize itself.
 */
public interface MySerializable {
    /**
     * Serializes object by writing data into a given stream.
     *
     * @param outputStream stream where elements will be written
     * @throws IOException any exception thrown by the underlying OutputStream.
     */
    void serialize(OutputStream outputStream) throws IOException;

    /**
     * Dematerializes object by reading elements from a given stream.
     * @param inputStream stream to read data from
     * @throws IOException any of the usual Input/Output related exceptions.
     */
    void deserialize(InputStream inputStream) throws IOException, ClassNotFoundException;
}

