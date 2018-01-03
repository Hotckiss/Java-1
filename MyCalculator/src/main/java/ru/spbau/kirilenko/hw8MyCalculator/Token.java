package ru.spbau.kirilenko.hw8MyCalculator;

/**
 * A class that represent a token in expression
 */
public class Token {

    public enum TokenId { TOK_OP, TOK_NUM };

    private TokenId id;
    private double num;
    private int op;

    /**
     * Constructs a token with id and value
     * @param id id of token
     * @param num new value
     */
    public Token(TokenId id, double num) {
        this.id = id;
        this.num = num;
    }

    /**
     * Constructs a token with id and value
     * @param id id of token
     * @param op new operation
     */
    public Token(TokenId id, int op) {
        this.id = id;
        this.op = op;
    }

    /**
     * A method that compares two Tokens
     * @param other token to compare
     * @return true if equals false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this.id != ((Token)other).id) {
            return false;
        } else {
            if (this.id == TokenId.TOK_NUM) {
                return this.num == ((Token)other).num;
            } else {
                return this.op == ((Token)other).op;
            }
        }
    }

    /**
     * Method that returns value of token if it is number
     * @return number in token
     * @throws IllegalStateException if token is not a number
     */
    public double getNum() throws IllegalStateException {
        if (this.id == TokenId.TOK_OP) {
            throw new IllegalStateException("Not a number!");
        }
        return this.num;
    }

    /**
     * Method that returns value of token if it is operation
     * @return operation code
     * @throws IllegalStateException if token is not an operation
     */
    public int getOp() throws IllegalStateException {
        if (this.id == TokenId.TOK_NUM) {
            throw new IllegalStateException("Not an operation!");
        }
        return this.op;
    }

    /**
     * Returns id of token
     * @return returns id of token
     */
    public TokenId getId() {
        return this.id;
    }

    /**
     * Method that updates value in token
     * @param newNum new value in token
     * @throws IllegalStateException if token is not a number
     */
    public void setNum(double newNum) throws IllegalStateException {
        if (this.id == TokenId.TOK_OP) {
            throw new IllegalStateException("Not a number!");
        }
       this.num = newNum;
    }
}
