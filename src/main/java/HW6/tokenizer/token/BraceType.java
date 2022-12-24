package HW6.tokenizer.token;

public enum BraceType {
    OPEN("("),
    CLOSE(")");

    public final String symbol;

    BraceType(String symbol) {
        this.symbol = symbol;
    }
}
