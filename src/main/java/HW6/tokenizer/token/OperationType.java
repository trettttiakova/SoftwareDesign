package HW6.tokenizer.token;

public enum OperationType {
    PLUS("+", 2),
    MINUS("-", 2),
    MULTIPLY("*", 1),
    DIVIDE("/", 1);

    public final String symbol;
    public final int priority;

    OperationType(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }
}
