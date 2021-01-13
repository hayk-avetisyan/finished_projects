package IO;
import java.util.Scanner;

/**
 * IO.SystemInput դասը օգտագործվում է ներմուծման համար։
 * @author Հայկ Ավետիսյան
 * @version 3.0
 */
@SuppressWarnings("unused")
public abstract class SystemInput {

    /** Ստանդարտ {@link Scanner} դասի օբյեկտ console-ից տվյալներ կարդալու համար։ */
    private static Scanner scan = new Scanner(System.in);

    //private static PrintWriter out = System.console().writer();
    /**
     * Թույլ է տալիս միայն unsigned int տիպի արժեքներ մուտքագրել
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է int տիպի բնական թիվ։
     */
    public static int inputNaturalNumber(String s) {
        int n;
        n = inputInt(s);
        while (n < 1) {
            scan.nextLine();
            n = inputInt("The integer must be greater than 0: ");
        }
        return n;
    }

    /**
     * Թույլ է տալիս մուտքագրել int տիպի արժեքներ [a, b] միջակայքից:
     * @see #inputInt(String)
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @param a int տիպի թիվ, որից պետք է մեծ կամ հավասար լինի ներմուծված թիվը
     * @param b int տիպի թիվ, որից պետք է փոքր կամ հավասար լինի ներմուծված թիվը
     * @return վերադարձնում է int տիպի թիվ։
     */

    public static int inputBetween(int a, int b, String s) {
        int n = inputInt(s);

        while(!(n >= a && n <= b)) {
            scan.nextLine();
            n = inputInt("Enter an integer between "+ a +" and "+ b +": ");
        }
        return  n;
    }

    /**
     * Թույլ է տալիս մուտքագրել double տիպի արժեքներ [a, b] միջակայքից:
     * @see #inputDouble(String)
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @param a double տիպի թիվ, որից պետք է մեծ կամ հավասար լինի ներմուծված թիվը
     * @param b double տիպի թիվ, որից պետք է փոքր կամ հավասար լինի ներմուծված թիվը
     * @return վերադարձնում է double տիպի թիվ։
     */

    public static double inputBetween(double a, double b, String s) {
        double n = inputDouble(s);

        while(!(Double.compare(n, a) >= 0 && Double.compare(n, b) <= 0)) {
            scan.nextLine();
            n = inputDouble("Enter a double integer between "+ a +" and "+ b +": ");
        }
        return  n;
    }

    /**
     * Թույլ է տալիս մուտքագրել float տիպի արժեքներ [a, b] միջակայքից:
     * @see #inputFloat(String)
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @param a float տիպի թիվ, որից պետք է մեծ կամ հավասար լինի ներմուծված թիվը
     * @param b float տիպի թիվ, որից պետք է փոքր կամ հավասար լինի ներմուծված թիվը
     * @return վերադարձնում է float տիպի թիվ։
     */

    public static float inputBetween(float a, float b, String s) {
        float n = inputFloat(s);
        while(!(Float.compare(n, a) >= 0 && Float.compare(n, b) <= 0)) {
            scan.nextLine();
            n = inputFloat("Enter a float integer between "+ a +" and "+ b +": ");
        }
        return n;
    }

    /**
     * Թույլ է տալիս մուտքագրել char տիպի արժեքներ [a, b] միջակայքից՝ ըստ նիշի Unicode-ի:
     * @see #inputChar(String)
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @param a char տիպի թիվ, որից պետք է մեծ կամ հավասար լինի ներմուծված թիվը
     * @param b char տիպի թիվ, որից պետք է փոքր կամ հավասար լինի ներմուծված թիվը
     * @return վերադարձնում է char տիպի արժեք։
     */

    public static char inputBetween(char a, char b, String s) {
        char n = inputChar(s);

        while(!(n >= a && n <= b)) {
            scan.nextLine();
            n = inputChar("Enter a char between "+ a +" and "+ b +": ");
        }
        return n;
    }

    /**
     * Կրկնում է մուտքագրումն այնքան, մինչև մուտքագրվի int տիպի արժեք։
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է int տիպի արժեք
     */

    public static int inputInt(String s) {
        try {
            if(s != null) System.out.print(s);
            return Integer.parseInt(scan.next());
        } catch (NumberFormatException e) {
            scan.nextLine();
            return inputInt("Enter an integer: ");
        }
    }

    /**
     * Կրկնում է մուտքագրումն այնքան, մինչև մուտքագրվի double տիպի արժեք։
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է double տիպի արժեք
     */

    public static double inputDouble(String s) {
        try {
            if (s != null) System.out.print(s);
            return Double.parseDouble(scan.next());
        } catch (NumberFormatException e) {
            scan.nextLine();
            return inputDouble("Enter a double integer: ");
        }
    }

    /**
     * Կրկնում է մուտքագրումն այնքան, մինչև մուտքագրվի float տիպի արժեք։
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է float տիպի արժեք
     */

    public static float inputFloat(String s) {
        try {
            if (s != null) System.out.print(s);
            return Float.parseFloat(scan.next());
        } catch (NumberFormatException e) {
            scan.nextLine();
            return inputFloat("Enter a float integer: ");
        }
    }

    /**
     * Կատարում է boolean տիպի արժեքի մուտքագրում՝ մուտքագրված
     * արժեքը մշկելով ըստ Boolean#parseBoolean(String) մեթոդի։
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է boolean տիպի արժեք
     */

    public static boolean inputBoolean(String s) {
        if (s != null) System.out.print(s);
        return Boolean.parseBoolean(scan.next());
    }


    /**
     * Կրկնում է մուտքագրումն այնքան, մինչև մուտքագրվի միայն մեկ նիշ:
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է char տիպի արժեք
     */

    public static char inputChar(String s) {
        String a;
        if (s != null) System.out.print(s);
        a = scan.next();
        if (a.length() > 1) {
            scan.nextLine();
            return inputChar("Enter a character: ");
        }
        return a.charAt(0);
    }

    /**
     * Թույլ է տալիս մուտքագրել ցանակցած արժեք՝ համարելով դրանք String։
     * Դադարեցնում է մուտքագրումը ցանկացած whitespace տեսակի նիշի հանդիպելիս։
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է String տիպի արժեք
     */

    public static String inputString(String s) {
        if (s != null) System.out.print(s);
        return scan.next();
    }

    /**
     * Թույլ է տալիս մուտքագրել ցանակցած արժեք՝ համարելով դրանք String։
     * Դադարեցնում է մուտքագրումը միայն \n (Enter) նիշին հանդիպելիս։
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է String տիպի արժեք
     */

    public static String inputLine(String s) {
        if (s != null) System.out.print(s);
        return scan.nextLine();
    }

    /**
     * Թույլ է տալիս մուտքագրել միայն տրված երկարության տող։
     * Դադարեցնում է մուտքագրումը ցանկացած whitespace տեսակի նիշի հանդիպելիս։
     * @param length երկարություն
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return մուտքագրված տողը
     */
    public static String inputStringWithLength(int length, String s) {
        String n = inputString(s);
        while (n.length() != length) {
            scan.nextLine();
            n = inputString("Input a string with length " + length + ": ");
        }
        return n;
    }

    /**
     * Թույլ է տալիս մուտքագրել միայն տրված մինիմալ երկարությունը ունեցող տող։
     * Դադարեցնում է մուտքագրումը ցանկացած whitespace տեսակի նիշի հանդիպելիս։
     * @param minLength մինիմալ երկարություն
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return մուտքագրված տողը
     */
    public static String inputStringWithMinLength(int minLength, String s) {
        String n = inputString(s);
        while (n.length() < minLength) {
            scan.nextLine();
            n = inputString("Input a string with minimum length " + minLength + ": ");
        }
        return n;
    }

    /**
     * Թույլ է տալիս մուտքագրել միայն տրված {@code [minLength, maxLength]} միջակայքի երկարություն ունեցող տող։
     * Դադարեցնում է մուտքագրումը ցանկացած whitespace տեսակի նիշի հանդիպելիս։
     * @param minLength մինիմալ երկարություն
     * @param maxLength մաքսիմալ երկարություն
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return մուտքագրված տողը
     */
    public static String inputStringWithLengthBetween(int minLength, int maxLength,  String s) {
        String n = inputString(s);
        while (!(n.length() >= minLength && n.length() <= maxLength)) {
            scan.nextLine();
            n = inputString("Input a string with length between " + minLength + " and "+ maxLength +": ");
        }
        return n;
    }


    /**
     * Թույլ է տալիս մուտքագրել միայն տրված երկարության տող։
     * Դադարեցնում է մուտքագրումը միայն \n (Enter) նիշին հանդիպելիս։
     * @param length երկարություն
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return մուտքագրված տողը
     */
    public static String inputLineWithLength(int length, String s) {
        String n = inputLine(s);
        while (n.length() != length) {
            n = inputLine("Input a line with length " + length + ": ");
        }
        return n;
    }

    /**
     * Թույլ է տալիս մուտքագրել միայն տրված մինիմալ երկարությունը ունեցող տող։
     * Դադարեցնում է մուտքագրումը միայն \n (Enter) նիշին հանդիպելիս։
     * @param minLength մինիմալ երկարություն
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return մուտքագրված տողը
     */
    public static String inputLineWithMinLength(int minLength, String s) {
        String n = inputLine(s);
        while (n.length() < minLength) {
            n = inputLine("Input a line with minimum length " + minLength + ": ");
        }
        return n;
    }

    /**
     * Թույլ է տալիս մուտքագրել միայն տրված {@code [minLength, maxLength]} միջակայքի երկարություն ունեցող տող։
     * Դադարեցնում է մուտքագրումը միայն \n (Enter) նիշին հանդիպելիս։
     * @param minLength մինիմալ երկարություն
     * @param maxLength մաքսիմալ երկարություն
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return մուտքագրված տողը
     */
    public static String inputLineWithLengthBetween(int minLength, int maxLength,  String s) {
        String n = inputLine(s);
        while (!(n.length() >= minLength && n.length() <= maxLength)) {
            n = inputLine("Input a line with length between " + minLength + " and "+ maxLength +": ");
        }
        return n;
    }

    /**
     * Մուտքագրում է int տեսակի զանգված՝ արտածելով համապատասխան հաղորդագրություններ։
     * @see SystemInput#inputNaturalNumber(String)
     * @see SystemInput#inputInt(String)
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է int[] տիպի արժեք
     */

    public static int[] inputIntArray(String s) {
        if(s != null) System.out.println(s);
        int[] array = new int[inputNaturalNumber("Enter the number of elements in the array: ")];
        System.out.print("Enter " + array.length + " integers: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = inputInt(null);
        }
        return array;
    }

    /**
     * Մուտքագրում է double տեսակի զանգված՝ արտածելով համապատասխան հաղորդագրություններ։
     * @see SystemInput#inputNaturalNumber(String)
     * @see SystemInput#inputDouble(String)
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է double[] տիպի արժեք
     */

    public static double[] inputDoubleArray(String s) {
        if(s != null) System.out.println(s);
        double[] array = new double[inputNaturalNumber("Enter the number of elements in the array: ")];

        System.out.print("Enter " + array.length + " double integers: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = inputDouble(null);
        }
        return array;
    }

    /**
     * Մուտքագրում է float տեսակի զանգված՝ արտածելով համապատասխան հաղորդագրություններ։
     * @see SystemInput#inputNaturalNumber(String)
     * @see SystemInput#inputFloat(String)
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է float[] տիպի արժեք
     */

    public static float[] inputFloatArray(String s) {
        if(s != null) System.out.println(s);
        float[] array = new float[inputNaturalNumber("Enter the number of elements in the array: ")];

        System.out.print("Enter " + array.length + " float integers: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = inputFloat(null);
        }
        return array;
    }


    /**
     * Մուտքագրում է boolean տեսակի զանգված՝ արտածելով համապատասխան հաղորդագրություններ։
     * @see SystemInput#inputNaturalNumber(String)
     * @see SystemInput#inputBoolean(String)
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է boolean[] տիպի արժեք
     */

    public static boolean[] inputBooleanArray(String s) {
        if(s != null) System.out.println(s);
        boolean[] array = new boolean[inputNaturalNumber("Enter the number of elements in the array: ")];

        System.out.print("Enter " + array.length + " booleans: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = inputBoolean(null);
        }
        return array;
    }

    /**
     * Մուտքագրում է char տեսակի զանգված՝ արտածելով համապատասխան հաղորդագրություններ։
     * @see SystemInput#inputNaturalNumber(String)
     * @see SystemInput#inputChar(String)
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է char[] տիպի արժեք
     */

    public static char[] inputCharArray(String s) {
        if(s != null) System.out.println(s);
        char[] array = new char[inputNaturalNumber("Enter the number of elements in the array: ")];

        System.out.print("Enter " + array.length + " characters: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = inputChar(null);
        }
        return array;
    }


    /**
     * Մուտքագրում է String տեսակի զանգված՝ արտածելով համապատասխան հաղորդագրություններ։
     * Ընդ որում յուրաքանչյուր մուտքագրում ավարտվում է whitespace տեսակի նիշի հանդիպելիս։
     * @see SystemInput#inputNaturalNumber(String)
     * @see SystemInput#inputString(String)
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է String[] տիպի արժեք
     */

    public static String[] inputStringArray(String s) {
        if(s != null) System.out.println(s);
        String[] array = new String[inputNaturalNumber("Enter the number of elements in the array: ")];

        System.out.print("Enter " + array.length + " strings: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = inputString(null);
        }
        return array;
    }

    /**
     * Մուտքագրում է String տեսակի զանգված՝ արտածելով համապատասխան հաղորդագրություններ։
     * Ընդ որում յուրաքանչյուր մուտքագրում ավարտվում է \n (Enter) նիշի հանդիպելիս։
     * @see SystemInput#inputNaturalNumber(String)
     * @see SystemInput#inputLine(String)
     *
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է String[] տիպի արժեք
     */

    public static String[] inputLineArray(String s) {
        if(s != null) System.out.println(s);
        String[] array = new String[inputNaturalNumber("Enter the number of elements in the array: ")];

        inputLine("Enter " + array.length + " sentences: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = inputLine(null);
        }
        return array;
    }
}
