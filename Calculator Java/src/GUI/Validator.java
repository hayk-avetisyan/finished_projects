package GUI;

import Calculator.Calculator;

/** Դասը նախատեսված է ներմուծված արժեքի ստուգման և կատարվելիք գործողությունը որոշելու համար */
public class Validator {

    /** Ընթացիկ արժեքը, որի "համար" կատարվում է ստուգում */
    private char value;

    /** Հաշվիչի դաշտում գրված ամբողջ տեքստը */
    private String text;

    /** Ներմուծման կուրսորը */
    private int cursor;

    /** true, եթե հաշվվել է արտահայտության արժեքը, false` հակառակ դեպքում */
    private boolean calculated;

    /** Բացված և դեռևս չփակված փակագծերի քանակը */
    private int openedParentheses;

    /** Ստեղծում է {@link Validator} դասի օբյեկտ
     *
     * @param value արժեքը, որի համար պետք է կատարել ստուգում
     * @param text ներմուծման դաշտի ամբողջ տեքստը
     * @param cursor ներմուծման դաշտի կուրսորը
     * @param calculated արդյոք հաշվարկվել է ներմուծման դաշտի արժեքը
     * @param openedParentheses բացված և չփակված փակագծերի քանակը
     */
    public Validator(char value, String text, int cursor, boolean calculated, int openedParentheses) {
        this.value = value;
        this.text = text;
        this.cursor = cursor;
        this.calculated = calculated;
        this.openedParentheses = openedParentheses;
    }

    /**
     * Ստուգում է ներմուծված արժեքը, որոշում և վերդարձնում համապատասխան գործողության կոդը․
     * @return թիվ, գործողության կատարման կոդը
     */
    public int validate() {

        Character previous = (cursor > 0) ? text.charAt(cursor - 1) : null;

        if (value == '(' && isExpectedOpenParentheses(previous)) return 3;
        if (value == ')' && isExpectedCloseParentheses(previous)) return 4;
        if (value == '.' && isExpectedDot(text)) return 0;

        if (Calculator.isNumber(value) && isExpectedNumber(previous)) {
            return (calculated) ? 2 : 0;
        }

        if (Calculator.isAction(value)) {
            if (isExpectedMinusAction(previous, value)) return 0;
            if (isExpectedAction(previous)) return 0;
            if (cursor > 1 && Calculator.isNumber(text.charAt(cursor - 2)) && Calculator.isAction(previous))
                return 1;
            return -1;
        }
        return -1;
    }

    /** @return true, եթե կետը թույլատրելի է, false՝ հակառակ դեպքում */
    private boolean isExpectedDot(String text) {
        if (cursor == 0) return false;

        if (Calculator.isNumber(text.charAt(cursor - 1))) {
            if (cursor - 1 > 0) {
                char c;
                for (int i = cursor - 1; i >= 0; i--) {
                    c = text.charAt(i);
                    if (Calculator.isNumber(c)) continue;
                    if (Calculator.isAction(c) || c == ')' || c == '(') return true;
                    if (c == '.') return false;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    /** @return true, եթե թիվը թույլատրելի է, false՝ հակառակ դեպքում */
    private boolean isExpectedNumber(Character previous) {
        return previous == null || previous != ')';
    }

    /** @return true, եթե բացասման նշանը թույլատրելի է, false՝ հակառակ դեպքում */
    private boolean isExpectedMinusAction(Character previous, char value) {
        return (previous == null || previous == '(') && value == '-';
    }

    /** @return true, եթե գործողության նշանը թույլատրելի է, false՝ հակառակ դեպքում */
    private boolean isExpectedAction(Character previous) {
        return previous != null && previous != '(' && (Calculator.isNumber(previous) || previous == ')');
    }

    /** @return true, եթե բաց փակագիծը թույլատրելի է, false՝ հակառակ դեպքում */
    private boolean isExpectedOpenParentheses(Character previous) {
        return previous == null || (cursor == 1 && previous == '-') || (Calculator.isAction(previous) || previous == '(');
    }

    /** @return true, եթե փակ փակագիծը թույլատրելի է, false՝ հակառակ դեպքում */
    private boolean isExpectedCloseParentheses(Character previous) {
        return previous != null && (previous != '(' || Calculator.isAction(previous)) && openedParentheses > 0;
    }
}
