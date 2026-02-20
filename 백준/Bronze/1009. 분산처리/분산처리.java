import java.io.*;
import java.util.*;

public class Main {
    static int modPow(int a, int b) {
        int mod = 10;
        int base = a % mod;
        int result = 1;

        while (b > 0) {
            if ((b & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            b >>= 1;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int last = modPow(a, b);
            if (last == 0) last = 10;   // 0이면 10번 컴퓨터
            sb.append(last).append('\n');
        }

        System.out.print(sb.toString());
    }
}