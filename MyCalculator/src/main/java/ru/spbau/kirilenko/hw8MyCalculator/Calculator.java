package ru.spbau.kirilenko.hw8MyCalculator;

/**
 * A class that allows to convert arithmetic
 * expression to polish notation and calculate it
 */
public class Calculator {

    private TokenStack operands = null;
    private TokenStack operators = null;

    private enum State { WAIT_PREFIX, WAIT_SUFFIX, PARSE_ERROR, DONE, END }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Bad format!");
            return;
        }

        Calculator calculator = new Calculator(new TokenStack(), new TokenStack());
        String input = args[0];

        double answer = calculator.eval(calculator.parseExpr(calculator.scanner(input)));

        System.out.println(input + "=" + answer);
    }

    /**
     * Simple constructor to standart calculation
     */
    public Calculator() {
        this.operands = new TokenStack();
        this.operators = new TokenStack();
    }

    /**
     * Constructor with special stacks for converting to polish notation
     * @param stackOperands stack of operands
     * @param stackOperators stack of operators
     */
    public Calculator(TokenStack stackOperands, TokenStack stackOperators) {
        this.operands = stackOperands;
        this.operators = stackOperators;
    }

    /**
     * Method that scans string and make
     * a queue of tokens from it
     * @param expr input string
     * @return queue of tokens
     */
    public TokenQueue scanner(String expr) {
        TokenQueue result = new TokenQueue();
        int i = 0;
        while (i < expr.length()) {
            Token t;
            char ch = expr.charAt(i);
            if (" \n\t\r".indexOf(ch) != -1) {
                i++;
                continue;
            } else if ("+-*/%^()".indexOf(ch) != -1) {
                t = new Token(Token.TokenId.TOK_OP, (int)ch);
                i++;
            } else if (Character.isDigit(ch)) {
                t = new Token(Token.TokenId.TOK_NUM, (double)(ch - '0'));
                i++;
                while (i < expr.length() && isDigit(expr.charAt(i))) {
                    t.setNum(t.getNum() * 10 + (expr.charAt(i) - '0'));
                    i++;
                }
                if (i < expr.length() && (expr.charAt(i) == '.')) {
                    i++;
                    int denum = 1;
                    while (i < expr.length() && isDigit(expr.charAt(i))) {
                        denum *= 10;
                        t.setNum(t.getNum() + ((double)(expr.charAt(i) - '0')) / denum);
                        i++;
                    }
                }
            } else {
                throw new IllegalArgumentException("Parse error on symbol: " + ((Character)expr.charAt(i)).toString());
            }
            result.push(t);
        }
        return result;
    }

    /**
     * Method that evaluates expression in reversed polish notation
     * @param tokens queue of tokens
     * @return calculated value
     */
    public double eval(TokenQueue tokens) {
        TokenStack stack = new TokenStack();
        while (!tokens.empty()) {
            Token t = tokens.pop();
            if (t.getId() == Token.TokenId.TOK_NUM) {
                stack.push(t);
            }
		    else {
                Token a = stack.pop();
                Token b = stack.pop();
                switch ((char)t.getOp())
                {
                    case '+':
                        a.setNum(a.getNum() + b.getNum());
                        stack.push(a);
                        break;
                    case '-':
                        a.setNum(a.getNum() - b.getNum());
                        stack.push(a);
                        break;
                    case '*':
                        a.setNum(a.getNum() * b.getNum());
                        stack.push(a);
                        break;
                    case '/':
                        if(b.getNum() == 0) {
                            throw new NumberFormatException("Zero division!");
                        }
                        a.setNum(a.getNum() / b.getNum());
                        stack.push(a);
                        break;
                    case '^':
                        a.setNum(Math.pow(a.getNum(), b.getNum()));
                        stack.push(a);
                        break;
                    case '%':
                        a.setNum(a.getNum() - ((int)(a.getNum() / b.getNum())) * b.getNum());
                        stack.push(a);
                        break;
                }
            }
        }
        return stack.pop().getNum();
    }

    /**
     * A method that translates expression to
     * reversed polish notation using sorting station
     * @param inputTokens queue of input tokens
     * @return queue of tokens in polish notation order
     */
    public TokenQueue parseExpr(TokenQueue inputTokens) {
        TokenQueue queueResult = new TokenQueue();
        Token t = new Token(Token.TokenId.TOK_NUM, 0.0);

        State state = State.WAIT_PREFIX;

        while (state != State.END) {
            if (state.ordinal() < State.PARSE_ERROR.ordinal()) {
                t = inputTokens.pop();
                if (t == null){
                    if (state == State.WAIT_SUFFIX) {
                        state = State.DONE;
                    } else {
                        throw new IllegalStateException("Unexpected ending.");
                    }
                }
            }
            switch (state) {
                case WAIT_PREFIX:
                    if (t.getId() == Token.TokenId.TOK_OP && t.getOp() == (int)'(') {
                        operators.push(t);
                    }
                    else if (t.getId() == Token.TokenId.TOK_NUM) {
                        operands.push(t);
                        state = State.WAIT_SUFFIX;

                    } else {
                        throw new IllegalStateException("Wait number or '('");
                    }
                    break;
                case WAIT_SUFFIX:
                    if (t.getId() == Token.TokenId.TOK_OP && t.getOp() != (int)'(') {
                        dropOpers(operands, operators, getPriority(t.getOp()));
                        if (t.getOp() != (int)')') {
                            operators.push(t);
                            state = State.WAIT_PREFIX;
                        } else {
                            t = operators.pop();
                            if (t == null) {
                                throw new IllegalStateException("Missing '('");
                            }
                        }
                    } else {
                        throw new IllegalStateException("Wait operator or ')'");
                    }
                    break;
                case PARSE_ERROR:
                    state = State.END;
                    break;
                case DONE:
                    dropOpers(operands, operators, getPriority(')'));
                    if (operators.empty()) {
                        while (!operands.empty()) {
                            t = operands.pop();
                            operators.push(t);
                        }
                        while (!operators.empty()) {
                            t = operators.pop();
                            queueResult.push(t);
                        }
                        state = State.END;
                    }
                    else {
                        throw new IllegalStateException("Waiting for ')'");
                    }
                    break;
            }
        }
        return queueResult;
    }

    private int getPriority(int op) {
        switch ((char)op) {
            case '(':
                return 0;
            case ')':
                return 1;
            case '+':
            case '-':
                return 2;
            case '*':
            case '/':
            case '%':
                return 3;
            case '^':
                return 4;
        }
        return -1;
    }

    private void dropOpers(TokenStack destination, TokenStack source, int prior) {
        while (!source.empty() && getPriority(source.top().getOp()) >= prior) {
            Token t = source.pop();
            destination.push(t);
        }
    }

    private boolean isDigit(char ch) {
        return (ch >= '0') && (ch <= '9');
    }
}
