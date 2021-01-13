/**
 * Ծրագիր, տպում է տրված տարի, ամիս, ամսաթվի օրացույցը։
 */
public class Main {

    public static void main(String[] args) {
        // Տվյալների ներմուծում։
        int year = InOut.inputNaturalNumber("Enter full year: ");
        int month = InOut.inputBetween(1, 12, "Enter month as a number between 1 and 12: ");
        int day = inputDay(month, year);

        Date date = new Date(day, month, year);
        date.showCalendar();
    }

    /**
     * Ապահովում է ճիշտ ամսաթիվի ներմուծումը։
     * @param month ամիսը թվերի տեսքով
     * @param year  տարի
     * @return      օր
     */
    private static int inputDay(int month, int year) {
        int day = InOut.inputBetween(1, 31, "Enter a day: ");
        while(!Date.isDay(day, month, year)) {
            day = InOut.inputBetween(1, 31, "Enter a valid day: ");
        }
        return day;
    }

}