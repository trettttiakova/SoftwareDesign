package HW6.visitor;

import HW6.tokenizer.token.Brace;
import HW6.tokenizer.token.NumberToken;
import HW6.tokenizer.token.Operation;
import HW6.tokenizer.token.Token;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcVisitorTest {
    @Test
    public void calculateValueFromValidRPNTest() {
        /*
        Исходное выражение: (23 + 10) * 5 - 3 * (32 + 5) + 8 / 2
        RPN: 23 10 + 5 * 3 32 5 + * - 8 2 / +
         */
        List<Token> RPN = List.of(
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

        int result = new CalcVisitor().calculateValueFromRPN(RPN);

        assertThat(result).isEqualTo(58);
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidRPNArgumentsProvider.class)
    public void calculateValueFromInvalidRPNTest(List<Token> tokens, String message) {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            new CalcVisitor().calculateValueFromRPN(tokens);
        });

        assertThat(thrown.getMessage()).isEqualTo(message);
    }

    static class InvalidRPNArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                // Лишняя операция
                Arguments.of(
                    List.of(
                        NumberToken.number(2),
                        NumberToken.number(2),
                        Operation.plus(),
                        Operation.minus()
                    ),
                    "Expression is invalid"
                ),
                // Не хватает операндов
                Arguments.of(
                    List.of(
                        NumberToken.number(2),
                        Operation.plus()
                    ),
                    "Expression is invalid"
                ),
                // Лишнее число
                Arguments.of(
                    List.of(
                        NumberToken.number(2),
                        NumberToken.number(3),
                        NumberToken.number(4),
                        Operation.plus()
                    ),
                    "Expression is invalid"
                ),
                // Лишнее число
                Arguments.of(
                    List.of(
                        NumberToken.number(2),
                        NumberToken.number(3),
                        Operation.plus(),
                        NumberToken.number(4)
                    ),
                    "Expression is invalid"
                ),
                Arguments.of(
                    List.of(
                        Brace.open(),
                        Operation.plus()
                    ),
                    "Brace should not appear in RPN"
                )
            );
        }
    }
}
