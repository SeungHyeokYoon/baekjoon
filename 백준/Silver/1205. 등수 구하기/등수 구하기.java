import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());        // 기존 랭킹 리스트 수
        int new_score = Integer.parseInt(st.nextToken()); // 새로운 점수
        int p = Integer.parseInt(st.nextToken());         // 랭킹 리스트 제한 수

        Integer[] arr = new Integer[n];
        
        // n이 0이 아닌 경우에만 다음 줄에서 점수 배열을 읽는다
        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr, Collections.reverseOrder());
        }

        // 점수를 랭킹에 올릴 수 없는 경우: 랭킹 리스트가 꽉 차 있고, 새 점수가 가장 낮은 점수 이하일 때
        if (n == p && n > 0 && new_score <= arr[arr.length - 1]) {
            System.out.print(-1);
        } else {
            int rank = 1;
            for (int i = 0; i < arr.length; i++) {
                if (new_score < arr[i])
                    rank++;
                else
                    break;
            }
            System.out.print(rank);
        }
    }
}
