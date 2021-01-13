package GUI;

/**
 * Այս դասի նպատակն է հավաքել և պահպանել ստեղնին վերաբերվող այն տվյալները,
 * որոնք օգտագործվում են ծրագրում, այդպիսով պարզեցնելով կոդը և բարձրացնելով արագագործությունը։
 */
public class Key {

    /** '9' թվի ստեղնը*/
    public static final Key NINE = new Key(57, "9");

    /** '0' թվի ստեղնը*/
    public static final Key ZERO = new Key(48, "0");

    /** 'SHIFT' ստեղնը*/
    public static final Key SHIFT = new Key(16, "");

    /** Ստեղնի կոդը */
    int code;

    /** Ստեղնի արժեքը */
    String value;

    /**
     * Ստեղծում է {@link Key} դասի օբյկետ
     * @param code ստեղնի կոդ
     * @param value ստեղնի արժեք
     */
    public Key(int code, String value) {
        this.code = code;
        this.value = value;
    }

    /** @return Օբյեկտի պարունակությունը տողի տեսքով */
    @Override
    public String toString() {
        return "GUI.Key [code:" + code + ", value:" + value + ']';
    }

    /**
     * Օգտագործվում է {@link java.util.ArrayList#contains(Object)} ֆունկցիայում` օբյկտների համեմատման համար։
     * @param o համեմատվող օբյեկտ
     * @return true, եթե օբյեկտների պարունակությունն ու տեսակը նույնն է, և false հակառակ դեպքում։
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Key) {
            Key key = ((Key) o);
            return key.value.equals(value) && key.code == code;
        }
        return false;
    }
}
