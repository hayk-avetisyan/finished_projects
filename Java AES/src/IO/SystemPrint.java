package IO;

import java.util.Locale;

/**
 * IO.SystemPrint ինտերֆեյսը օգտագործվում է արտածման համար։
 *
 * @author Հայկ Ավետիսյան
 * @version 1.0
 */
@SuppressWarnings("unused")
public interface SystemPrint {

    static <T> void println(T value) {
        System.out.println(value);
    }

    static <T> void println() {
        System.out.println();
    }

    static <T> void print(T value) {
        System.out.print(value);
    }

    static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    static void printf(Locale locale, String format, Object... args) {
        System.out.printf(locale, format, args);
    }
}
