package Calculator;

import java.util.ArrayList;

/**
 * Այս ծրագիրը հաշվում է արտահայտության արժեքը:
 * Արտահայտությունը պետք է լինի Ճիշտ:
 */
public class Calculator {

    /** Ցուցիչ  */
    private int cursor = 0;
    
    /** Հաշվելիք արտահայտություն */
    private StringBuilder expression;
    
    /** Թույլատրվող գործողություններ */
    private static char[] operations = {'+', '-', '/', '*', '%', '^'};

    /** Արտահայտության մեջ գտնվող թվերի բազմություն */
    private ArrayList<Double> digits = new ArrayList<>();

    /** Արտահայտության մեջ կատարվելիք գործողությունների բազմություն */
    private ArrayList<Character> actions = new ArrayList<>();

    /** Կոնստրունկտոր, որը մշակում է արգումենտ տրված արտհայտությունը
     * @see #setExpression(String)
     */
    public Calculator(String expression) throws InvalidExpressionException {
        setExpression(expression);
    }

    /**
     * Մշակում է որպես արգումենտ տրված արտահայտությունը։
     * Արտահայտությունում առկա փակագծերի մեջ գտնվող արտահայտությունները
     * փոխարինվում են համապատասխան արժեքներով։
     * Մնացած գործողությունները և թվերը գրանցվում են համապատասխանաբար
     * {@link #actions}, {@link #digits} ցուցակներում
     *
     * Սխալ արտահայտության դեպքում տպվում է համապատասխան հաղորդագրություն։
     * @param expr String, մշակվող արտահայտություն
     */
    private void setExpression(String expr) throws InvalidExpressionException {
        this.expression = new StringBuilder(expr);
        int[] indices = {0, 0};
        double value;
        String sub;
        trim();

            while(parentheses(indices[0], indices)) {
                sub = expression.substring(indices[0] + 1, indices[1] - 1);
                expression.delete(indices[0], indices[1]);
                value = (new Calculator(sub)).calculate();

                if (Double.compare(value, 0) < 0) {
                    expression.insert(indices[0], "(" + value + ")");
                }
                else expression.insert(indices[0], value);
            }

            digits.add(readInt());
            if(available()) {
                actions.add(readAction());
                digits.add(readInt());
                while (available()) {
                    actions.add(readAction());
                    digits.add(readInt());
                }
            }

    }

    /**
     * Փնտրում է առաջին իսկ հանդիպող (...) փակագծերի ինդեքսները
     * @param start սկզբնական ցուցիչ
     * @param indices զանգված, որում պահպանվում են կատարման արդյունքները
     * @return 'true' եթե համընկնում կա և 'false' հակառակ դեպքում
     * @throws InvalidExpressionException սխալ {@link #expression} արտահայտության դեպքում
     */
    private boolean parentheses(int start, int[] indices) throws InvalidExpressionException {
        char find;
        int n = 1, count;

        if((start = expression.indexOf("(", start)) > -1) {
            count = 1;
            n += start;

            while(count != 0) {
                if(n == expression.length()) throw new InvalidExpressionException("Parentheses haven't been closed!");
                find = expression.charAt(n);
                if(find == '(') count++;
                if(find == ')') {
                    if(isInt(start + 1, n)) {
                        return parentheses(n, indices);
                    }
                    count--;
                }
                n++;
            }

            indices[0] = start;
            indices[1] = n;
            return true;
        }
        return false;
    }

    /**
     * Հաշվում է {@link #expression} արտահայտության արժեքը
     * @return Double, {@link #expression} արտահայտության արժեքը
     */
    public Double calculate() {
        if(digits.size() > 1) {
            for (int i = operations.length - 1; i >= 0; i--) {
                for (int k = 0; k < actions.size(); ) {
                    if (actions.get(k) == operations[i]) {
                        evaluate(operations[i], k);
                    } else k++;
                }
            }
        }

        return digits.get(0);
    }

    /**
     * Կատարում է տրված գործողությունը {@link #digits} ցուցակի տրված k և k+1-րդ թվերի հետ։
     * Արդյունքը գրվում է ցուցակի k-րդ դիրքում։
     * k+1-րդ թիվը ջնջվում է
     * Գործողությունների {@link #actions} ցուցակի k-րդ տարրը ջնջվում է։
     *
     * @param action char, գործողության նշան
     * @param index int, ինդեքս
     */
    private void evaluate(char action, int index) {
        switch(action) {
            case '+': {
                digits.set(index, digits.get(index) + digits.get(index + 1));
                break;
            }
            case '-': {
                digits.set(index, digits.get(index) - digits.get(index + 1));
                break;
            }
            case '*': {
                digits.set(index, digits.get(index) * digits.get(index + 1));
                break;
            }
            case '/': {
                digits.set(index, digits.get(index) / digits.get(index + 1));
                break;
            }
            case '%': {
                digits.set(index, (digits.get(index) * digits.get(index + 1)) / 100);
                break;
            }
            case '^': {
                digits.set(index, Math.pow(digits.get(index), digits.get(index + 1)));
                break;
            }
        }

        digits.remove(index + 1);
        actions.remove(index);
    }

    /** @return true եթե կան չկարդացված սիմվոլներ արտահայտությունում, false՝ հակառակ դեպքում */
    private boolean available() {
        return (expression.length() - cursor > 0);
    }

    /**
     * Ստուգում է արդյոք {@link #expression} արտահայտությունում
     * տրված ինդեքսներում գտնվող հատվածը թիվ է։
     * @param start int, սկզբնական ինդեքս
     * @param end int, վերջնական ինդեքս
     * @return true եթե տողը թիվ է, false` հակառակ դեպքում
     */
    private boolean isInt(int start, int end) {
        try {
            Double.parseDouble(expression.substring(start, end));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Կարդում է թիվ {@link #expression} արտահայտության
     * {@link #cursor} ինդեքսից սկսած ինչքան հնարավոր է։
     * @return int, կարդացած թիվը
     * @throws InvalidExpressionException սխալ {@link #expression} արտահայտության դեպքում
     */
    private double readInt() throws InvalidExpressionException {
        char c;
        int start = cursor;
        boolean Q = false, N = false, P = false, negative = false;
        StringBuilder r = new StringBuilder();

        if(available() && get() == '-' && actions.size() == 0) {
            negative = true;
            cursor++;
        }
        if(available() && get() == '(') {
            P = true;
            cursor++;
            if(available() && get() == '-') {
                negative = !negative;
                cursor++;
            }
            else throw new InvalidExpressionException();
        }

        if(negative) r.insert(0, '-');

        while (available()) {
            c = get();
            if (c == '.') {
                if (Q || !N) throw new InvalidExpressionException();
                else {
                    Q = true;
                    r.append(c);
                }
            } else if (c >= '0' && c <= '9') {
                r.append(c);
                N = true;
            } else if (c == ')' && P) {
                cursor++;
                break;
            } else break;
            cursor++;
        }

        if (start == cursor) throw new InvalidExpressionException();
        return Double.parseDouble(r.toString());
    }

    /**
     * Կարդում է մեկ գործողության նշան
     * @return char, գործողության նշան
     * @throws InvalidExpressionException սխալ {@link #expression} արտահայտության դեպքում
     */
    private char readAction() throws InvalidExpressionException {
        if(available()) {
            char value = get();
            if (isAction(value)) {
                cursor++;
                return value;
            } else {
                throw new InvalidExpressionException();
            }
        }
        throw new InvalidExpressionException();
    }

    /**
     * Ստուգում է արդյոք տրված նշանը գտվում է թույլատրվող գործողությունների
     * նշանների բազմությունում՝ {@link #operations}
     * @param value, char նշան
     * @return 'true', եթե նշանը թույլատրելի է, 'false'՝ հակառակ դեպքում
     */
    public static boolean isAction(Character value) {
        if(value == null) return false;

        char c = value;
        for(char action: operations) {
            if(c == action) return true;
        }
        return false;
    }

    /**
     * @return կարդում է {@link #cursor}-րդ նշանը {@link #expression} արտահայտությունից
     * @throws IndexOutOfBoundsException սխալ {@link #expression} արտահայտության դեպքում
     */
    private char get() throws IndexOutOfBoundsException {
        return expression.charAt(cursor);
    }

    /** Ջնջում է {@link #expression} արտահայտության մեջ գտնվող բոլոր բացատանիշերը։ */
    private void trim() {
        for (int i = 0; i < expression.length();) {
            if(expression.charAt(i) == ' ') {
                expression.deleteCharAt(i);
            }
            else i++;
        }
    }

    /**
     * Ստուգում է արդյոք տրված նիշը թիվ է։
     * @param value, char նիշ
     * @return {@code true}, եթե նիշը թիվ է, {@code false} հակառակ դեպքում
     */
    public static boolean isNumber(char value) {
        return (value >= '0' && value <= '9');
    }
}
