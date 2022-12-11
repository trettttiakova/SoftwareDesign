package HW6.visitor;

import HW6.tokenizer.token.Brace;
import HW6.tokenizer.token.NumberToken;
import HW6.tokenizer.token.Operation;

public interface TokenVisitor {
    void visit(NumberToken token);
    void visit(Brace token);
    void visit(Operation token);
}
