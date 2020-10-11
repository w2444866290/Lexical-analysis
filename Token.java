import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

public class Token {
    public StringBuilder token;
    public static File file;

    public Token() {
        token = new StringBuilder();
    }

    public static void main(String[] args) {
        Token run = new Token();
        try {
            file = new File("$input");
            FileInputStream input = new FileInputStream(file);
            try {
                int n = 0;
                n = input.read();
                // skip space in front of page
                while (Character.isSpaceChar((char)n) || Character.isWhitespace((char)n))
                    n = input.read();
                while (n != -1) {
                    // skip space between words
                    if (Character.isSpaceChar((char)n) || Character.isWhitespace((char)n)) {
                        n = input.read();
                        continue;
                    }
                    // lexical analysis
                    if (Character.isLetter((char)n)) {
                        run.token.append((char)n);
                        n = input.read();
                        while (Character.isLetter((char)n) || Character.isDigit((char)n)) {
                            run.token.append((char)n);
                            n = input.read();
                        }
                        String reserve = run.token.toString();
                        switch (reserve) {
                            case "BEGIN":
                                System.out.println("Begin");
                                break;
                            case "END":
                                System.out.println("End");
                                break;
                            case "FOR":
                                System.out.println("For");
                                break;
                            case "IF":
                                System.out.println("If");
                                break;
                            case "THEN":
                                System.out.println("Then");
                                break;
                            case "ELSE":
                                System.out.println("Else");
                                break;
                            default:
                                System.out.println("Ident(" + reserve + ")");
                        }
                        run.token = new StringBuilder();
                        continue;
                    }
                    else if (Character.isDigit((char)n)){
                        while ((char)n == '0')
                            n = input.read();
                        run.token.append((char)n);
                        n = input.read();
                        while(Character.isDigit((char)n)) {
                            run.token.append((char)n);
                            n = input.read();
                        }
                        System.out.println("Int(" + run.token.toString() + ")");
                        run.token = new StringBuilder();
                        continue;
                    }
                    else if ((char)n == '+') {
                        System.out.println("Plus");
                    }
                    else if ((char)n == '*') {
                        System.out.println("Star");
                    }
                    else if ((char)n == ',') {
                        System.out.println("Comma");
                    }
                    else if ((char)n == '(') {
                        System.out.println("LParenthesis");
                    }
                    else if ((char)n == ')') {
                        System.out.println("RParenthesis");
                    }
                    else if ((char)n == ':') {
                        n = input.read();
                        if ((char)n == '=')
                            System.out.println("Assign");
                        else {
                            System.out.println("Colon");
                            continue;
                        }
                    }
                    else {
                        System.out.println("Unknown");
                        break;
                    }
                    n = input.read();
                }
            }catch (IOException e) {
                e.getStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }
}
