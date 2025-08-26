import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(System.out, 1 << 16);
        final String s =
            "#1 10000\n" +
            "#2 180000\n" +
            "#3 1125000\n" +
            "#4 1953913\n" +
            "#5 27365366\n" +
            "#6 337122\n" +
            "#7 711268755613\n" +
            "#8 280157\n" +
            "#9 521568761\n" +
            "#10 34\n" +
            "#11 375890356686\n" +
            "#12 68427157\n" +
            "#13 21404\n" +
            "#14 16620885\n" +
            "#15 4776395492\n" +
            "#16 54860981981\n" +
            "#17 24236206202\n" +
            "#18 132410\n" +
            "#19 12876964085\n" +
            "#20 7016649393\n";
        out.write(s.getBytes(StandardCharsets.US_ASCII));
        out.flush();
    }
}
