package HW6.tokenizer.token;

import HW6.visitor.TokenVisitor;

public class NumberToken implements Token {
    private final Integer number;

    public NumberToken(Integer number) {
        this.number = number;
    }

    public int getValue() {
        return number;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "NUMBER(" + number + ")";
    }

    public static NumberToken number(int number) {
        return new NumberToken(number);
    }
}
