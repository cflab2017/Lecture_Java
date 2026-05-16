import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        String text = "주문번호: 1234, 가격: 5678원, 수량: 3개";
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println("매치=" + m.group());
        }

        Pattern email = Pattern.compile("[\\w.]+@[\\w.]+");
        System.out.println(email.matcher("hi jisu@example.com bye").find());
    }
}
