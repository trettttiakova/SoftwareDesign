package HW6.visitor;

import HW6.tokenizer.token.Brace;
import HW6.tokenizer.token.NumberToken;
import HW6.tokenizer.token.Operation;
import HW6.tokenizer.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrintVisitor implements TokenVisitor {
    private final List<Token> tokens = new ArrayList<>();

    public String getTokensString(List<Token> tokens) {
        for (var token : tokens) {
            token.accept(this);
        }

        return tokens.stream()
            .map(Token::toString)
            .collect(Collectors.joining(" "));
    }

    @Override
    public void visit(NumberToken token) {
        tokens.add(token);
    }

    @Override
    public void visit(Brace token) {
        tokens.add(token);
    }

    @Override
    public void visit(Operation token) {
        tokens.add(token);
    }
}
