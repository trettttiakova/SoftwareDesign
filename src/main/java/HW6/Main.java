package HW6;

import HW6.tokenizer.Tokenizer;
import HW6.visitor.CalcVisitor;
import HW6.visitor.ParserVisitor;
import HW6.visitor.PrintVisitor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.isEmpty()) {
            try {
                var tokens = new Tokenizer(input).getTokens();
                var RPN = new ParserVisitor().getReversePolishNotation(tokens);
                System.out.println(new PrintVisitor().getTokensString(RPN));
                System.out.println(new CalcVisitor().calculateValueFromRPN(RPN));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
            input = in.nextLine();
        }
    }
}
