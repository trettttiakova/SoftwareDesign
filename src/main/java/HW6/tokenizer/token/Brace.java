package HW6.tokenizer.token;

import HW6.visitor.TokenVisitor;

public class Brace implements Token {
    private final BraceType type;

    private Brace(BraceType type) {
        this.type = type;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isOpen() {
        return type == BraceType.OPEN;
    }

    public static Brace open() {
        return new Brace(BraceType.OPEN);
    }

    public static Brace close() {
        return new Brace(BraceType.CLOSE);
    }
}
