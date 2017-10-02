package ru.spbau.kirilenko.hw3Trie;

import java.io.*;
import java.util.*;

/**
 * A class that can store a set strings in a tree-like structure and add/remove them,
 * counting number of strings with fixed prefix
 */
public class Trie implements MySerializable {
    private Node root = new Node(null);

    /**
     * Serializes object by writing data into a given stream.
     *
     * @param output stream where data will be written
     * @throws IOException any exception thrown by the underlying OutputStream.
     */
    public void serialize(OutputStream output) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(output);
        outputStream.writeObject(root);
    }

    /**
     * Deserializes object by reading data from a given stream.
     *
     * @param input stream to read data from
     * @throws IOException any of the usual Input/Output related exceptions.
     */
    public void deserialize(InputStream input) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(input);
        root = (Node)inputStream.readObject();

        root.linkTrie();
    }

    /**
     * Method that add a string in a trie
     * @param element that should be add
     * @return true if this string was newly added, false otherwise
     */
    public boolean add(String element) {
        if (element == null) {
            throw new IllegalArgumentException("Null string!");
        }

        Node addNode = getNodeByString(element, true);

        if (addNode.isTerminal()) {
            return false;
        }

        addNode.setTerminal(true);

        return true;
    }

    /**
     * Method that check containing a string in a trie
     * @param element that should be checked
     * @return true if this string was in trie, false otherwise
     */
    public boolean contains(String element) {
        if (element == null) {
            throw new IllegalArgumentException("Null string!");
        }

        Node candidateNode = getNodeByString(element, false);

        if (candidateNode == null) {
            return false;
        }

        return candidateNode.isTerminal();
    }

    /**
     * Method that remove a string in a trie
     * @param element that should be removed
     * @return true if this string was in trie and was deleted, false otherwise
     */
    public boolean remove(String element) {
        if (element == null) {
            throw new IllegalArgumentException("Null string!");
        }

        Node removeNode = getNodeByString(element, false);

        if (removeNode == null) {
            return false;
        }

        if (!removeNode.isTerminal()) {
            return false;
        }

        removeNode.setTerminal(false);

        return true;
    }

    /**
     * Method that returns a power of set of strings
     * @return number of strings in trie
     */
    int size() {
        return root.getNumberOfTerminalsInSubTree();
    }

    /**
     * Method that returns a number of of strings with input prefix
     * @return number of strings in trie with this prefix
     */
    public int howManyStartsWithPrefix(String prefix) {
        Node endNode = getNodeByString(prefix, false);
        return (endNode == null) ? 0 : endNode.getNumberOfTerminalsInSubTree();
    }

    private Node getNodeByString(String element, boolean createNodes) {
        Node currentNode = root;

        for (int i = 0; i < element.length(); i++) {
            Character ch = element.charAt(i);

            if (!currentNode.existsEdge(ch)) {
                if (createNodes) {
                    currentNode.newChild(ch);
                } else {
                    return null;
                }
            }

            currentNode = currentNode.getChild(ch);
        }

        return currentNode;
    }

    private static class Node implements Serializable {
        private Node parent;
        private HashMap<Character, Node> children = new HashMap<>();
        private boolean isTerminal = false;
        private int numberOfTerminalsInSubTree = 0;

        public Node(Node parentNode) {
            this.parent = parentNode;
        }

        public boolean existsEdge(Character edge) {
            return children.containsKey(edge);
        }

        public void newChild(Character ch) {
            Node childNode = new Node(this);
            children.put(ch, childNode);
        }

        public Node getChild(Character ch) {
            return children.get(ch);
        }

        public boolean isTerminal() {
            return isTerminal;
        }

        public void setTerminal(boolean value) {
            if (isTerminal == value) {
                return;
            }
            isTerminal = value;
            int change = value ? 1 : -1;
            Node it = this;

            while (it != null) {
                it.numberOfTerminalsInSubTree += change;
                it = it.parent;
            }
            isTerminal = value;
        }

        public int getNumberOfTerminalsInSubTree() {
            return numberOfTerminalsInSubTree;
        }

        public void linkTrie() {
            Stack<Node> stack = new Stack<>();
            stack.push(this);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
                    Node child = entry.getValue();
                    child.parent = node;

                    stack.push(child);
                }
            }
        }

        private void writeObject(ObjectOutputStream output) throws IOException {
            parent = null;
            output.writeObject(children);
            output.writeBoolean(isTerminal);
            output.writeInt(numberOfTerminalsInSubTree);
        }

        private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
            children = (HashMap<Character, Node>)input.readObject();
            isTerminal = input.readBoolean();
            numberOfTerminalsInSubTree = input.readInt();
        }
    }
}


