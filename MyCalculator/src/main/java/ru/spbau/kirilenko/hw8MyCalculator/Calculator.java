package ru.spbau.kirilenko.hw8MyCalculator;

/**
 * A class that allows to convert arithmetic
 * expression to polish notation and calculate it
 */
public class Calculator {

    private MyStack operands = null;
    private MyStack operators = null;

    private enum State { WAIT_PREFIX, WAIT_SUFFIX, PARSE_ERROR, DONE, END }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Bad format!");
            return;
        }

        Calculator calculator = new Calculator(new MyStack(), new MyStack());
        String input = args[0];

        double answer = calculator.eval(calculator.parseExpr(calculator.scanner(input)));

        System.out.println(input + "=" + answer);
    }

    /**
     * Simple constructor to standart calculation
     */
    public Calculator() {
        this.operands = new MyStack();
        this.operators = new MyStack();
    }

    /**
     * Constructor with special stacks for converting to polish notation
     * @param stackOperands stack of operands
     * @param stackOperators stack of operators
     */
    public Calculator(MyStack stackOperands, MyStack stackOperators) {
        this.operands = stackOperands;
        this.operators = stackOperators;
    }

    /**
     * Method that scans string and make
     * a queue of tokens from it
     * @param expr input string
     * @return queue of tokens
     */
    public MyQueue scanner(String expr) {
        Tok t;
        MyQueue result = new MyQueue();
        int i = 0;
        while (i < expr.length()) {
            char ch = expr.charAt(i);
            switch (ch) {
                case ' ':
                case '\n':
                case '\t':
                case '\r':
                    i++;
                    continue;
                case '+':
                case '-':
                case '*':
                case '/':
                case '%':
                case '^':
                case '(':
                case ')':
                    t = new Tok(Tok.Tokid.TOK_OP, (int)ch);
                    i++;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    t = new Tok(Tok.Tokid.TOK_NUM, (double)(ch - '0'));
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

                    break;
                default:
                    throw new IllegalArgumentException("Parse error on symbol: " + ((Character)expr.charAt(i)).toString());

            }
            result.push(t);
        }
        return result;
    }

    /**
     * Method that evaluates expressino in reversed polish notation
     * @param tokens queue of tokens
     * @return calculated value
     */
    public double eval(MyQueue tokens) {
        MyStack stack = new MyStack();
        Tok t;
        Tok a;
        Tok b;
        while (!tokens.empty()) {
            t = tokens.pop();
            if (t.getId() == Tok.Tokid.TOK_NUM) {
                stack.push(t);
            }
		    else {
                b = stack.pop();
                a = stack.pop();
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
    public MyQueue parseExpr(MyQueue inputTokens) {
        MyQueue queueResult = new MyQueue();
        Tok t = new Tok(Tok.Tokid.TOK_NUM, 0.0);

        State state = State.WAIT_PREFIX;

        while (state != State.END) {
            if (state.ordinal() < State.PARSE_ERROR.ordinal()) {
                t = inputTokens.pop();
                if (t == null){
                    if (state.ordinal() == State.WAIT_SUFFIX.ordinal()) {
                        state = State.DONE;
                    } else {
                        state = State.PARSE_ERROR;
                        throw new IllegalStateException("Unexpected ending.");
                    }
                }
            }
            switch (state) {
                case WAIT_PREFIX:
                    if (t.getId() == Tok.Tokid.TOK_OP && t.getOp() == (int)'(') {
                        operators.push(t);
                    }
                    else if (t.getId() == Tok.Tokid.TOK_NUM) {
                        operands.push(t);
                        state = State.WAIT_SUFFIX;

                    } else {
                        state = State.PARSE_ERROR;
                        throw new IllegalStateException("Wait number or '('");
                    }
                    break;
                case WAIT_SUFFIX:
                    if (t.getId() == Tok.Tokid.TOK_OP && t.getOp() != (int)'(') {
                        dropOpers(operands, operators, getPriority(t.getOp()));
                        if (t.getOp() != (int)')') {
                            operators.push(t);
                            state = State.WAIT_PREFIX;
                        } else {
                            t = operators.pop();
                            if (t == null) {
                                state = State.PARSE_ERROR;
                                throw new IllegalStateException("Missing '('");
                            }
                        }
                    } else {
                        state = State.PARSE_ERROR;
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
                        state = State.PARSE_ERROR;
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

    private void dropOpers(MyStack destination, MyStack source, int prior) {
        while (!source.empty() && getPriority(source.top().getOp()) >= prior) {
            Tok t;
            t = source.pop();
            destination.push(t);
        }
    }

    private boolean isDigit(char ch) {
        return (ch >= '0') && (ch <= '9');
    }
}
