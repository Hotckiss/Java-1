package ru.spbau.kirilenko.hw8MyCalculator;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * A class that tests methods of Calculator class
 */
public class CalculatorTest {

    /**
     * A test that check correctness of converting
     * to polish notation using mock objects
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testParserMockTest() {
        MyQueue mockedQueueTokens = mock(MyQueue.class);
        MyStack mockedStackOperands = mock(MyStack.class);
        MyStack mockedStackOperators = mock(MyStack.class);

        when(mockedQueueTokens.pop())
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'('))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 1.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 3.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)')'))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 4.0))
                .thenReturn(null);

        when(mockedStackOperators.pop())
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'('))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 1.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 3.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 4.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'));

        when(mockedStackOperators.empty())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        when(mockedStackOperators.top())
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'('))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'('))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'))
                .thenReturn(null);

        when(mockedStackOperands.pop())
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 4.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 3.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 1.0));


        when(mockedStackOperands.empty())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        Calculator calculator = new Calculator(mockedStackOperands, mockedStackOperators);
        MyQueue inversed = calculator.parseExpr(mockedQueueTokens);
        StringBuilder sb = new StringBuilder();

        while (!inversed.empty()) {
            Tok token = inversed.pop();
            if (token.getId() == Tok.Tokid.TOK_OP) {
                sb.append((char)token.getOp());
            } else {
                sb.append(token.getNum());
            }
            sb.append(" ");
        }

        assertEquals("1.0 3.0 + 4.0 * ", sb.toString());
        verify(mockedStackOperators, times(8)).push(anyObject());
    }

    /**
     * A test that check correctness of converting
     * to polish notation and evaluating using mock objects
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluateMockTest() {
        MyQueue mockedQueueTokens = mock(MyQueue.class);
        MyStack mockedStackOperands = mock(MyStack.class);
        MyStack mockedStackOperators = mock(MyStack.class);

        when(mockedQueueTokens.pop())
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'('))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 1.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 5.5))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)')'))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 4.0))
                .thenReturn(null);

        when(mockedStackOperators.pop())
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'('))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 1.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 5.5))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 4.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'));

        when(mockedStackOperators.empty())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        when(mockedStackOperators.top())
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'('))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'('))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'))
                .thenReturn(null);

        when(mockedStackOperands.pop())
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'*'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 4.0))
                .thenReturn(new Tok(Tok.Tokid.TOK_OP, (int)'+'))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 5.5))
                .thenReturn(new Tok(Tok.Tokid.TOK_NUM, 1.0));


        when(mockedStackOperands.empty())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        Calculator calculator = new Calculator(mockedStackOperands, mockedStackOperators);
        MyQueue inversed = calculator.parseExpr(mockedQueueTokens);
        assertEquals(26.0, calculator.eval(inversed), 0.0);
        verify(mockedStackOperators, times(8)).push(anyObject());
    }


    /**
     * A test that check correctness of scanning
     * an expression to the queue of tokens
     */
    @Test
    public void testScannerStandartTest() {
        Calculator calculator = new Calculator();
        String expr = "(1+2)*3 - 4 *((((5)+6)))-7.5/15+111";
        MyQueue parsed = calculator.scanner(expr);

        StringBuilder sb = new StringBuilder();
        while (!parsed.empty()) {
            Tok token = parsed.pop();
            if (token.getId() == Tok.Tokid.TOK_OP) {
                sb.append((char)token.getOp());
            } else {
                sb.append(token.getNum());
            }
        }
        assertEquals("(1.0+2.0)*3.0-4.0*((((5.0)+6.0)))-7.5/15.0+111.0", sb.toString());
    }

    /**
     * A test that check correctness of evaluating an expression
     */
    @Test
    public void testEvalStandartTest() {
        Calculator calculator = new Calculator();
        MyQueue inversed = new MyQueue();

        inversed.push(new Tok(Tok.Tokid.TOK_NUM, 1.0));
        inversed.push(new Tok(Tok.Tokid.TOK_NUM, 2.0));
        inversed.push(new Tok(Tok.Tokid.TOK_OP, (int)'+'));
        inversed.push(new Tok(Tok.Tokid.TOK_NUM, 3.0));
        inversed.push(new Tok(Tok.Tokid.TOK_OP, (int)'*'));

        assertEquals(9.0, calculator.eval(inversed), 0.0);
    }

    /**
     * A test that check correctness of converting an expression to polish notation
     */
    @Test
    public void testParserStandartTest() {
        Calculator calculator = new Calculator();
        String expr = "((1+2)*3 - 4   *(((5+6)))-7.5/15+111)";
        MyQueue parsed = calculator.scanner(expr);

        MyQueue inversed = calculator.parseExpr(parsed);
        StringBuilder sb = new StringBuilder();
        while (!inversed.empty()) {
            Tok token = inversed.pop();
            if (token.getId() == Tok.Tokid.TOK_OP) {
                sb.append((char)token.getOp());
            } else {
                sb.append(token.getNum());
            }
            sb.append(" ");
        }

        assertEquals("1.0 2.0 + 3.0 * 4.0 5.0 6.0 + * - 7.5 15.0 / - 111.0 + ", sb.toString());
    }

}