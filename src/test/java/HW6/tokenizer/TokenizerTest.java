package HW6.tokenizer;

import HW6.tokenizer.token.Brace;
import HW6.tokenizer.token.NumberToken;
import HW6.tokenizer.token.Operation;
import HW6.tokenizer.token.Token;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenizerTest {
    @Test
    public void getTokensTest() {
        String expression = "(23 + 10) * 5 - 3 * (32 + 5) + 8 / 2";

        List<Token> tokens = new Tokenizer(expression).getTokens();
        assertThat(tokens).usingRecursiveFieldByFieldElementComparator()
            .containsExactly(
                Brace.open(),
                NumberToken.number(23),
                Operation.plus(),
                NumberToken.number(10),
                Brace.close(),
                Operation.multiply(),
                NumberToken.number(5),
                Operation.minus(),
                NumberToken.number(3),
                Operation.multiply(),
                Brace.open(),
                NumberToken.number(32),
                Operation.plus(),
                NumberToken.number(5),
                Brace.close(),
                Operation.plus(),
                NumberToken.number(8),
                Operation.divide(),
                NumberToken.number(2)
            );
    }

    @Test(expected = RuntimeException.class)
    public void getTokensInvalidExpressionTest() {
        String expression = "(23 + 10) * this is expression";

        new Tokenizer(expression).getTokens();
    }
}
