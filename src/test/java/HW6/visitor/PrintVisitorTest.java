package HW6.visitor;

import HW6.tokenizer.token.NumberToken;
import HW6.tokenizer.token.Operation;
import HW6.tokenizer.token.Token;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrintVisitorTest {
    @Test
    public void getTokensStringTest() {
        List<Token> tokens = List.of(
            NumberToken.number(32),
            NumberToken.number(2),
            Operation.multiply(),
            NumberToken.number(10),
            NumberToken.number(2),
            Operation.divide(),
            Operation.plus(),
            NumberToken.number(8),
            Operation.minus()
        );

        String result = new PrintVisitor().getTokensString(tokens);

        assertThat(result)
            .isEqualTo(
                "NUMBER(32) NUMBER(2) MULTIPLY NUMBER(10) NUMBER(2) DIVIDE PLUS NUMBER(8) MINUS"
            );
    }
}
