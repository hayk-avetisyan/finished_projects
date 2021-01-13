import java.util.Arrays;
import java.util.Scanner;

/**
 * InOut class-ը օգտագործվում է ներմուծման և արտածման
 * գործընթացը հեշտացնելու համար։
 *
 * @author Հայկ Ավետիսյան
 * @version 2.5
 */
@SuppressWarnings("unused")
public class InOut {
    /**
     * Ստանդարտ Scanner class-ի օբյեկտ console-ից տվյալներ ներմուծելու համար։
     */
    private static Scanner scan = new Scanner(System.in);

    /**
     * Քանի որ այս class-ը տրամադրում է միայն static ֆունկցիաներ,
     * անհրաժեշտ է թույլ չտալ class-ից օբյեկտի ստեղծումը։
     */
    private InOut() {}

    /**
     * Թույլ է տալիս միայն բնական թվեր տիպի արժեքներ մուտքագրել
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     * @return վերադարձնում է int տիպի բնական թիվ։
     */
    public static int inputNaturalNumber(String s) {
        int n;
        n = inputInt(s);
        while (n < 1) {
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
     * Մուտքագրում է int տեսակի զանգված՝ արտածելով համապատասխան հաղորդագրություններ։
     * @see InOut#inputNaturalNumber(String)
     * @see InOut#inputInt(String)
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
     * @see InOut#inputNaturalNumber(String)
     * @see InOut#inputDouble(String)
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
     * @see InOut#inputNaturalNumber(String)
     * @see InOut#inputFloat(String)
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
     * @see InOut#inputNaturalNumber(String)
     * @see InOut#inputBoolean(String)
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
     * @see InOut#inputNaturalNumber(String)
     * @see InOut#inputChar(String)
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
     * @see InOut#inputNaturalNumber(String)
     * @see InOut#inputString(String)
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
     * @see InOut#inputNaturalNumber(String)
     * @see InOut#inputLine(String)
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

    /**
     * Տպում է որպես պարամետր տրված էլեմենտները
     * @param elements էլեմենտներ, որ անհրաժեշտ է տպել
     */
    public static void print(Object... elements) {
        for (Object item : elements) {
            System.out.print(item.toString() + " ");
        }
    }

    /**
     * Տպում է որպես պարամետր տրված էլեմենտները, այնուհետև անցնում հաջորդ տող
     * @see #print(Object...)
     *
     * @param elements էլեմենտներ, որ անհրաժեշտ է տպել
     */
    public static void println(Object... elements) {
        print(elements);
        System.out.println();
    }


    /**
     * Արտածում է որպես պարամետր տրված միաչափ զանգվածը։
     * @see Arrays#toString(int[])
     *
     * @param array int տիպի միաչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */
    public static void printArray(int[] array, String s) {
        System.out.println(((s != null) ? s : "") + Arrays.toString(array));
    }

    /**
     * Արտածում է որպես պարամետր տրված միաչափ զանգվածը։
     * @see Arrays#toString(double[])
     *
     * @param array double տիպի միաչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */
    public static void printArray(double[] array, String s) {
        System.out.println(((s != null) ? s : "") + Arrays.toString(array));
    }

    /**
     * Արտածում է որպես պարամետր տրված միաչափ զանգվածը։
     * @see Arrays#toString(float[])
     *
     * @param array float տիպի միաչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */
    public static void printArray(float[] array, String s) {
        System.out.println(((s != null) ? s : "") + Arrays.toString(array));
    }

    /**
     * Արտածում է որպես պարամետր տրված միաչափ զանգվածը։
     * @see Arrays#toString(boolean[])
     *
     * @param array boolean տիպի միաչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */
    public static void printArray(boolean[] array, String s) {
        System.out.println(((s != null) ? s : "") + Arrays.toString(array));
    }

    /**
     * Արտածում է որպես պարամետր տրված միաչափ զանգվածը։
     * @see Arrays#toString(char[])
     *
     * @param array char տիպի միաչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */
    public static void printArray(char[] array, String s) {
        System.out.println(((s != null) ? s : "") + Arrays.toString(array));
    }

    /**
     * Արտածում է որպես պարամետր տրված միաչափ զանգվածը։
     * @see Arrays#toString(Object[])
     *
     * @param array String տիպի միաչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */
    public static void printArray(String[] array, String s) {
        System.out.println(((s != null) ? s : "") + Arrays.toString(array));
    }

    /**
     * Արտածում է որպես պարամետր տրված միաչափ զանգվածը։
     * @see Arrays#toString(Object[])
     *
     * @param array String տիպի միաչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */
    public static void printArray(Object[] array, String s) {
        if(s != null) System.out.println(s);
        for (Object o : array) {
            System.out.println(o.toString());
        }
    }

    /**
     * Արտածում է որպես պարամետր տրված երկչափ զանգվածը։
     * @see InOut#printArray(int[], String)
     *
     * @param array int տիպի երկչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */

    public static void printArrayInArray(int[][] array, String s) {
        if(s != null) System.out.println(s);
        for (int[] ints : array) {
            InOut.printArray(ints, null);
        }
    }

    /**
     * Արտածում է որպես պարամետր տրված երկչափ զանգվածը։
     * @see InOut#printArray(double[], String)
     *
     * @param array double տիպի երկչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */

    public static void printArrayInArray(double[][] array, String s) {
        if(s != null) System.out.println(s);
        for (double[] ints : array) {
            InOut.printArray(ints, null);
        }
    }

    /**
     * Արտածում է որպես պարամետր տրված երկչափ զանգվածը։
     * @see InOut#printArray(float[], String)
     *
     * @param array float տիպի երկչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */

    public static void printArrayInArray(float[][] array, String s) {
        if(s != null) System.out.println(s);
        for (float[] ints : array) {
            InOut.printArray(ints, null);
        }
    }

    /**
     * Արտածում է որպես պարամետր տրված երկչափ զանգվածը։
     * @see InOut#printArray(boolean[], String s)
     *
     * @param array boolean տիպի երկչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */

    public static void printArrayInArray(boolean[][] array, String s) {
        if(s != null) System.out.println(s);
        for (boolean[] booleans : array) {
            InOut.printArray(booleans, null);
        }
    }

    /**
     * Արտածում է որպես պարամետր տրված երկչափ զանգվածը։
     * @see InOut#printArray(char[], String)
     *
     * @param array char տիպի երկչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */

    public static void printArrayInArray(char[][] array, String s) {
        if(s != null) System.out.println(s);
        for (char[] chars : array) {
            InOut.printArray(chars, null);
        }
    }

    /**
     * Արտածում է որպես պարամետր տրված երկչափ զանգվածը։
     * @see InOut#printArray(String[], String)
     *
     * @param array String տիպի երկչափ զանգված
     * @param s Տող որ տպվելու է նախքան մուտքագրումը
     */

    public static void printArrayInArray(String[][] array, String s) {
        if(s != null) System.out.println(s);
        for (String[] strings : array) {
            InOut.printArray(strings, null);
        }
    }
}
