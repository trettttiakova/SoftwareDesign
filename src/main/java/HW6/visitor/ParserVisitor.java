package HW6.visitor;

import HW6.tokenizer.token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {
    private Stack<Token> stack = new Stack<>();
    private List<Token> RPN = new ArrayList<>();

    public List<Token> getReversePolishNotation(List<Token> tokens) {
        for (var token : tokens) {
            token.accept(this);
        }

        while (!stack.isEmpty()) {
            moveFromStackToRPN();
        }

        return RPN;
    }

    @Override
    public void visit(NumberToken token) {
        RPN.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token.isOpen()) {
            stack.add(token);
        }
        else {
            while (!stack.isEmpty() && !isOpenBrace(stack.peek())) {
                RPN.add(stack.pop());
            }

            if (stack.isEmpty()) {
                throw new RuntimeException("CLOSE brace without corresponding OPEN brace");
            }

            stack.pop(); // pop "("
        }
    }

    @Override
    public void visit(Operation token) {
        while (!stack.isEmpty() && priorityLargerThanPeek(token)) {
            moveFromStackToRPN();
        }

        stack.add(token);
    }

    private boolean isOpenBrace(Token token) {
        return (token instanceof Brace) && ((Brace) token).isOpen();
    }

    private int getPriority(Operation operation) {
        return operation.getPriority();
    }

    private boolean priorityLargerThanPeek(Operation token) {
        if (!(stack.peek() instanceof Operation)) {
            return false;
        }

        return getPriority((Operation) stack.peek()) <= token.getPriority();
    }

    private void moveFromStackToRPN() {
        if (isOpenBrace(stack.peek())) {
            throw new RuntimeException("OPEN brace without corresponding CLOSE brace");
        }
        RPN.add(stack.pop());
    }
}
