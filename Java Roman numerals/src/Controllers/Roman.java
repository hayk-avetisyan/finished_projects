package Controllers;

import Validators.RomanValidator;
import javafx.scene.Parent;

import java.util.HashMap;
import java.util.regex.Pattern;

public final class Roman extends Base {

    static final private HashMap <Character, Integer> num = new HashMap<>();
    private char[] text;

    static {
        num.put('I', 1);
        num.put('V', 5);
        num.put('X', 10);
        num.put('L', 50);
        num.put('C', 100);
        num.put('D', 500);
        num.put('M', 1000);
    }

    public Roman(Parent page) {
        super(page);
    }

    @Override
    protected void convert() {
        text = input.getText().toCharArray();
        if(text.length == 0) return;

        output.setText(String.valueOf(count(0)));
    }

    private int count(int k) {
        int c = num.get(text[k]), count = c;
        for (int i = k + 1; i < text.length; i++) {

            if(c == num.get(text[i])) count += c;
            if(c < num.get(text[i])) count = num.get(text[i]) - count;
            if(c > num.get(text[i])) return count + count(i);

        }
        return count;
    }

    @Override
    protected boolean validExpression(String e) {
        return new RomanValidator(e).validate();
    }

    @Override
    protected String setInputFieldID() {
        return "roman_field";
    }

    @Override
    protected String setOutputFieldID() {
        return "roman_to_decimal_result";
    }

    @Override
    protected String setButtonID() {
        return "roman_button";
    }
}
