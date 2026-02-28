import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long ans = 0;
        while (N > 0) {
            ans += N;   // 현재 단계에서 존재하는 "단위"들을 1개씩 센 횟수
            N /= M;     // 다음 단계로 올라가면 묶음 개수만 남음
        }

        System.out.println(ans);
    }
}