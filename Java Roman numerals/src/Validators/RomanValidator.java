package Validators;

public class RomanValidator {

    private int i;
    private int one;
    private int ten;
    private int hundred;
    private char previous;
    private String expr;
    
    public RomanValidator(String expr) {
        this.expr = expr;
    }
    
    
    public boolean validate() {
        for (i = 0; i < expr.length(); i++) {
            switch (expr.charAt(i)) {
                case 'I': {
                    if(isIExpected()) {
                        one++;
                        ten = 0;
                        hundred = 0;
                        break;
                    }
                    return false;
                }
                case 'V': {
                    if(isVExpected()) {
                        one = 0;
                        ten = 0;
                        hundred = 0;
                        break;
                    }
                    return false;
                }
                case 'X': {
                    if(isXExpected()) {
                        one = 0;
                        ten++;
                        hundred = 0;
                        break;
                    }
                    return false;
                }
                case 'L': {
                    if(isLExpected()) {
                        one = 0;
                        ten = 0;
                        hundred = 0;
                        break;
                    }
                    return false;
                }
                case 'C': {
                    if(isCExpected()) {
                        one = 0;
                        ten = 0;
                        hundred++;
                        break;
                    }
                    return false;
                }
                case 'D': {
                    if(isDExpected()) {
                        one = 0;
                        ten = 0;
                        hundred = 0;
                        break;
                    }
                    return false;
                }
                case 'M': {
                    if(isMExpected()) {
                        one = 0;
                        ten = 0;
                        hundred = 0;
                        break;
                    }
                    return false;
                }
                default: return false;
            }
            previous = expr.charAt(i);
        }
        return true;
    }

    private boolean isIExpected() {
        return !(
                (one >= 3) ||
                (i >= 2 && previous != 'I' && expr.charAt(i - 2) == 'I')
        );
    }

    private boolean isVExpected() {
        return !(
                (one > 1 || previous == 'V' || (i >= 2 && expr.charAt(i - 2) == 'V')) ||
                (previous == 'X' && (i >= 2 && expr.charAt(i - 2) == 'I'))
        );
    }

    private boolean isXExpected() {
        return !(
                (ten >= 3 || one > 1) ||
                (previous == 'V')     ||
                (i >= 2 && previous != 'I' && previous != 'X' && expr.charAt(i - 2) == 'X') ||
                (i >= 2 && ten == 1 && expr.charAt(i - 2) == 'I')
        );
    }

    private boolean isLExpected() {
        return !(
                (ten > 1 || one > 0) ||
                (previous == 'V' || previous == 'L') ||
                (i >= 2 && expr.charAt(i - 2) == 'L')
        );
    }

    private boolean isCExpected() {
        return !(
                (hundred >= 3) ||
                (ten > 1 || one > 0) ||
                (previous == 'V' || previous == 'L') ||
                (i >= 2 && previous != 'C' && expr.charAt(i - 2) == 'C')
        );
    }

    private boolean isDExpected() {
        return !(
                (ten > 0 || one > 0 || hundred > 1)  ||
                (previous == 'L' || previous == 'V' || previous == 'D') ||
                (i >= 2 && expr.charAt(i - 2) == 'D')
        );
    }

    private boolean isMExpected() {
        return !(
                (hundred > 1) ||
                (previous != '\0' && previous != 'C' && previous != 'M')
        );
    }
}
