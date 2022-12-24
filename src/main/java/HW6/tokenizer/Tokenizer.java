package HW6.tokenizer;

import HW6.tokenizer.token.*;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final String expression;
    private int currentPosition = 0;

    public Tokenizer(String expression) {
        this.expression = expression;
    }

    public List<Token> getTokens() {
        List<Token> tokens = new ArrayList<>();

        Token currentToken = getNextToken();
        while (currentToken != null) {
            tokens.add(currentToken);
            currentToken = getNextToken();
        }

        return tokens;
    }

    private Token getNextToken() {
        skipWhitespaces();
        if (currentPosition >= expression.length()) {
            return null;
        }

        char currentChar = expression.charAt(currentPosition);
        switch (currentChar) {
            case '+' -> {
                currentPosition++;
                return Operation.plus();
            }
            case '-' -> {
                currentPosition++;
                return Operation.minus();
            }
            case '*' -> {
                currentPosition++;
                return Operation.multiply();
            }
            case '/' -> {
                currentPosition++;
                return Operation.divide();
            }
            case '(' -> {
                currentPosition++;
                return Brace.open();
            }
            case ')' -> {
                currentPosition++;
                return Brace.close();
            }
            default -> {
                String number = getDigits();
                if (number == null) {
                    throw new RuntimeException(
                        "Unexpected symbol " + currentChar + " at position " + currentPosition
                    );
                }

                return new NumberToken(Integer.parseInt(number));
            }
        }
    }

    private void skipWhitespaces() {
        while (currentPosition < expression.length()
            && Character.isWhitespace(expression.charAt(currentPosition))) {
            currentPosition++;
        }
    }

    private String getDigits() {
        int start = currentPosition;

        while (currentPosition < expression.length()
            && Character.isDigit(expression.charAt(currentPosition))) {
            currentPosition++;
        }

        if (start == currentPosition) {
            return null;
        }

        return expression.substring(start, currentPosition);
    }
}
