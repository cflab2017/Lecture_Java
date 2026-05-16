import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 이메일 추출. */
public class Homework02 {
    public static void main(String[] args) {
        String text = "문의: a@x.com 또는 b@y.co.kr 까지";
        Pattern p = Pattern.compile("[\\w.]+@[\\w.]+");
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
