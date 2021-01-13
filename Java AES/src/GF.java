@SuppressWarnings("unused")
public final class GF {

    public static int multiply(int a, int b) {
        int p = 0;
        while(a != 0) {
            if((a & 1) == 1) p ^= b;
            b <<= 1;
            a >>= 1;
        }
        return mod(p);
    }

    public static int mod(int a) {
        int s;
        while((s = bitLength(a) - 9) >= 0) {
            a = a ^ (0x11b << s);
        }
        return a;
    }

    public static int bitLength(int a) {
        int length = 0;
        while(a != 0) {
            a >>= 1;
            length++;
        }
        return length;
    }

    public static void printBinaryCode(int a) {
        System.out.print(a + ": ");
        do {
            System.out.print(a & 1);
            a >>= 1;
        } while(a != 0);
        System.out.println();
    }
}
