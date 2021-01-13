package Controllers;

import javafx.scene.Parent;

public final class Decimal extends Base {

    public Decimal(Parent page) {
        super(page);
    }

    @Override
    protected void convert() {
        StringBuilder result = new StringBuilder();
        String text = input.getText();
        int size = text.length();

        for (int i = 0; i < size; i++) result.append(get(text.charAt(i), size - i));

        output.setText(result.toString());
    }

    private String get(char c, int range) {
        if(c == '0') return "";

        switch (range) {
            case 1: {
                return get(c, "I", "V", "X");
            }
            case 2: {
                return get(c, "X", "L", "C");
            }
            case 3: {
                return get(c, "C", "D", "M");
            }
            default: {
                return "M".repeat(c - 48);
            }
        }
    }

    private String get(char c, String a, String b, String d) {
        if(c == '4') return a + b;
        else if(c >= '1' && c <= '3') return a.repeat(c - 48);
        else if(c >= '5' && c <= '8') return b + a.repeat(c - 53);
        return a + d;
    }

    @Override
    protected boolean validExpression(String expression) {
        try {
            Integer.parseInt(expression);
            return true;
        } catch(NumberFormatException e) {
            return expression.length() == 0;
        }
    }

    @Override
    protected String setInputFieldID() {
        return "decimal_field";
    }

    @Override
    protected String setOutputFieldID() {
        return "decimal_to_roman_result";
    }

    @Override
    protected String setButtonID() {
        return "decimal_button";
    }

}
