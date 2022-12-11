package HW6.visitor;

import HW6.tokenizer.token.Brace;
import HW6.tokenizer.token.NumberToken;
import HW6.tokenizer.token.Operation;
import HW6.tokenizer.token.Token;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserVisitorTest {
    @Test
    public void testInfixToRPNSimple() {
        /*
        Исходное выражение: 32 * 2
        Должно превратиться в:
        32 2 *
         */
        List<Token> infix = List.of(
            NumberToken.number(32),
            Operation.multiply(),
            NumberToken.number(2)
        );

        List<Token> RPN = new ParserVisitor().getReversePolishNotation(infix);
        assertThat(RPN).usingRecursiveFieldByFieldElementComparator()
            .containsExactly(
                NumberToken.number(32),
                NumberToken.number(2),
                Operation.multiply()
            );
    }

    @Test
    public void testInfixToRPNComplex() {
        /*
        Исходное выражение: (23 + 10) * 5 - 3 * (32 + 5) + 8 / 2
        Должно превратиться в:
        23 10 + 5 * 3 32 5 + * - 8 2 / +
         */
        List<Token> infix = List.of(
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

        List<Token> RPN = new ParserVisitor().getReversePolishNotation(infix);
        assertThat(RPN).usingRecursiveFieldByFieldElementComparator()
            .containsExactly(
                NumberToken.number(23),
                NumberToken.number(10),
                Operation.plus(),
                NumberToken.number(5),
                Operation.multiply(),
                NumberToken.number(3),
                NumberToken.number(32),
                NumberToken.number(5),
                Operation.plus(),
                Operation.multiply(),
                Operation.minus(),
                NumberToken.number(8),
                NumberToken.number(2),
                Operation.divide(),
                Operation.plus()
            );
        System.out.println();
    }

    @Test
    public void testInvalidInfix1() {
        /*
        Исходное выражение: 32 * 2)
         */
        List<Token> infix = List.of(
            NumberToken.number(32),
            Operation.multiply(),
            NumberToken.number(2),
            Brace.close()
        );

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            new ParserVisitor().getReversePolishNotation(infix);
        });

        assertThat(thrown.getMessage()).isEqualTo("CLOSE brace without corresponding OPEN brace");
    }

    @Test
    public void testInvalidInfix2() {
        /*
        Исходное выражение: (32 * 2
         */
        List<Token> infix = List.of(
            Brace.open(),
            NumberToken.number(32),
            Operation.multiply(),
            NumberToken.number(2)
        );

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            new ParserVisitor().getReversePolishNotation(infix);
        });

        assertThat(thrown.getMessage()).isEqualTo("OPEN brace without corresponding CLOSE brace");
    }

    @Test
    public void testInvalidInfix3() {
        /*
        Исходное выражение: 32 * 2 * (
         */
        List<Token> infix = List.of(
            Brace.open(),
            NumberToken.number(32),
            Operation.multiply(),
            NumberToken.number(2),
            Operation.multiply(),
            Brace.open()
        );

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            new ParserVisitor().getReversePolishNotation(infix);
        });

        assertThat(thrown.getMessage()).isEqualTo("OPEN brace without corresponding CLOSE brace");
    }
}
