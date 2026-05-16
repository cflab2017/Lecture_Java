/** 윤년 판별. */
public class Homework01 {
    public static void main(String[] args) {
        int year = 2024;
        boolean leap = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
        System.out.println(year + "은(는) 윤년? " + leap);
    }
}
