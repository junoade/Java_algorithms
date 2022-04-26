package category.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] args) {
        String s = "1S2D*3T";
        String regex = "(([0-9]|10)[A-Z][*#]?)";
        // Pattern ptrn = Pattern.compile("([\\d|10])([SDT])([*#])?");
        Pattern pattern=Pattern.compile("([0-9]+)([SDT])([*#]?)");
        Matcher matcher=pattern.matcher(s);
        System.out.println(matcher.group(1));
    }
}
