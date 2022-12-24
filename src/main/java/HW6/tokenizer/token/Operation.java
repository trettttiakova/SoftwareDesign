package HW6.tokenizer.token;

import HW6.visitor.TokenVisitor;

public class Operation implements Token {
    private final OperationType type;

    private Operation(OperationType type) {
        this.type = type;
    }

    public OperationType getType() {
        return type;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public int getPriority() {
        return type.priority;
    }

    public static Operation plus() {
        return new Operation(OperationType.PLUS);
    }
    public static Operation minus() {
        return new Operation(OperationType.MINUS);
    }
    public static Operation multiply() {
        return new Operation(OperationType.MULTIPLY);
    }
    public static Operation divide() {
        return new Operation(OperationType.DIVIDE);
    }
}
