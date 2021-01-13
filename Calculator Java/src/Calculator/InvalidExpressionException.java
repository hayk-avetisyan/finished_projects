package Calculator;

/** Սխալ արտահայտության բացառություն */
public class InvalidExpressionException extends Exception {

    public InvalidExpressionException() {
        super("Invalid Expression!");
    }

    public InvalidExpressionException(String string) {
        super(string);
    }

}
