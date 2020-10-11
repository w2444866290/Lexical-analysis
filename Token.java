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
            file = new File(args[0]);
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
                                System.out.print("Begin/r/n");
                                break;
                            case "END":
                                System.out.print("End/r/n");
                                break;
                            case "FOR":
                                System.out.print("For/r/n");
                                break;
                            case "IF":
                                System.out.print("If/r/n");
                                break;
                            case "THEN":
                                System.out.print("Then/r/n");
                                break;
                            case "ELSE":
                                System.out.print("Else/r/n");
                                break;
                            default:
                                System.out.print("Ident(" + reserve + ")/r/n");
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
                        System.out.print("Int(" + run.token.toString() + ")/r/n");
                        run.token = new StringBuilder();
                        continue;
                    }
                    else if ((char)n == '+') {
                        System.out.print("Plus/r/n");
                    }
                    else if ((char)n == '*') {
                        System.out.print("Star/r/n");
                    }
                    else if ((char)n == ',') {
                        System.out.print("Comma/r/n");
                    }
                    else if ((char)n == '(') {
                        System.out.print("LParenthesis/r/n");
                    }
                    else if ((char)n == ')') {
                        System.out.print("RParenthesis/r/n");
                    }
                    else if ((char)n == ':') {
                        n = input.read();
                        if ((char)n == '=')
                            System.out.print("Assign/r/n");
                        else {
                            System.out.print("Colon/r/n");
                            continue;
                        }
                    }
                    else {
                        System.out.print("Unknown/r/n");
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
