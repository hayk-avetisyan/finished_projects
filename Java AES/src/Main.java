import static IO.SystemInput.*;
import static IO.SystemPrint.*;

/** AES համաչափ գաղտնագրման ալգորիթմ։ */
public class Main {

    public static void main(String[] args) {
        String message = inputLineWithMinLength(16, "Enter the message: ");
        String key = inputStringWithLength(16, "Enter the key: ");

        String encrypt = AES.encrypt(key, message);
        println(encrypt);
        println();
        println(AES.decrypt(key, encrypt));
    }


}
