package HW6.visitor;

import HW6.tokenizer.token.Brace;
import HW6.tokenizer.token.NumberToken;
import HW6.tokenizer.token.Operation;
import HW6.tokenizer.token.Token;

import java.util.List;
import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private Stack<Integer> calculations = new Stack<>();

    public int calculateValueFromRPN(List<Token> RPN) {
        for (var token : RPN) {
            token.accept(this);
        }

        int result = popWithException();

        if (!calculations.isEmpty()) {
            throw new RuntimeException("Expression is invalid");
        }

        return result;
    }

    @Override
    public void visit(NumberToken token) {
        calculations.add(token.getValue());
    }

    @Override
    public void visit(Brace token) {
        throw new RuntimeException("Brace should not appear in RPN");
    }

    @Override
    public void visit(Operation token) {
        // in stack: x y
        int y = popWithException();
        int x = popWithException();

        switch (token.getType()) {
            case PLUS -> calculations.add(x + y);
            case MINUS -> calculations.add(x - y);
            case MULTIPLY -> calculations.add(x * y);
            case DIVIDE -> calculations.add(x / y);
        }
    }

    private int popWithException() {
        if (calculations.isEmpty()) {
            throw new RuntimeException("Expression is invalid");
        }

        return calculations.pop();
    }
}
