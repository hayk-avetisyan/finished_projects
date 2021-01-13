public class Date {

    /** Կոնսոլում կարմիր գույնի կոդ*/
    final static private String RED ="\033[0;31m";

    /** Վերականգնում է կոնսոլի գույնը */
    final static private String RESET ="\u001B[0m";

    /** Հայտնի տարի, որն օգտագործվում է շաբաթվա ճիշտ օրը հաշվարկելու համար։ */
    final static private int START_YEAR = 1970;

    /** {@link #START_YEAR} տարվա 01․01-ի շաբաթվա օրը։ */
    final static private int START_WEEK = 3;

    /** Ամիսների օրերի տարբերությունը 30-ից */
    final static private int[] months = {1, -2, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1};

    /** Ամիսների անվանումները */
    final static private String[] monthTitles = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    /** Օր */
    private int day;

    /** Ամիս */
    private int month;

    /** Տարի */
    private int year;

    /** {@link #year} և {@link #START_YEAR} տարիների տարբերության մոդուլը */
    private int yearsBetween;

    /** {@link #year} և {@link #START_YEAR} տարիների միջև գտնվող նահանջ տարիների քանակը */
    private int leapYearsBetween;

    /**
     * Արժեքավորում է դաշտերը։
     *
     * @param day օր
     * @param month ամիսը թվերով
     * @param year տարի
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month - 1;
        this.year = year;

        yearsBetween = Math.abs(year - START_YEAR);

        int current = Math.min(year, START_YEAR);

        for(int i = 0; i < yearsBetween; i++) {
            if(isLeap(current)) {
                leapYearsBetween++;
            }
            current++;
        }
    }

    /**
     * @param year տարեթիվ
     * @return  true, եթե տարին նահանջ է և false՝ հակառակ դեպքում
     */
    public static boolean isLeap(int year) {
        return (year % 400 == 0 || (year % 100 != 0) && (year % 4 == 0));
    }

    /**
     * @param day օր
     * @param month ամիսը թվերով
     * @param year տարի
     * @return true, եթե օրը գոյություն ունի և false հակառակ դեպքում
     */
    public static boolean isDay(int day, int month, int year) {
        return (day >= 1 && day <= 30 + months[month] + ((isLeap(year) && month == 1) ? 1 : 0));
    }

    /**
     * @param month int, ամիսը թվով
     * @return int, {@link #year} տարվա տրված ամսվա օրերի քանակ
     */
    public int getDaysOfMonth(int month) {
        return 30 + months[month] + ((isLeap(year) && month == 1) ? 1 : 0);
    }

    /** Ցուցադրում է {@link #month} ամսվա օրացույցը։ */
    public void showCalendar() {
        int k = 1, m = 1,
            start  = getFirstWeekIndex(),
            days   = getDaysOfMonth(month),
            before = ((month - 1 == -1) ? 31 : getDaysOfMonth(month - 1)) + 1 - start++;

        System.out.println("\n           " + monthTitles[month]);
        System.out.println(" ----------------------------");
        System.out.println(" Mon Tue Wed Thu Fri Set Sun");
        for (int i = 1; i <= 42; i++) {
            if(i < start) System.out.printf(RED + "%4d" + RESET, before++);
            else if(k <= days) {
                if(day == k) System.out.printf("|%2d|", k);
                else System.out.printf("%4d", k);
                k++;
            }
            else System.out.printf(RED + "%4d" + RESET, m++);
            if(i % 7 == 0) System.out.println();
        }
    }

    /** @return {@link #year} տարվա {@link #month} ամսվա 1-ին շաբաթվա է-ին օրվա ինդեքսը։ */
    private int getFirstWeekIndex() {
        int days = yearsBetween * 365 + leapYearsBetween + 30 * month + START_WEEK;
        for (int i = 0; i < month; i++) {
            days += months[i];
        }
        if(isLeap(year) && month > 1) days++;
        return days % 7;
    }
}
