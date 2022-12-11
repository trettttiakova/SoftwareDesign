package HW6.tokenizer.token;

import HW6.visitor.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor);
}
