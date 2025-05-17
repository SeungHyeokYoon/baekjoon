import java.io.*;
import java.util.*;

public class swea2805 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t<=T; t++){

            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];

            for(int i = 0; i<n; i++){
                String str = br.readLine();
                for(int j = 0; j<n; j++){
                    arr[i][j] = str.charAt(j)-'0';
                }
            }

            boolean asc = true;
            int cnt = 1;

            int sum = 0;
            for(int i = 0; i<n/2; i++){
                int idx = (n-cnt)/2;
                for(int j = 0; j<cnt; j++){
                    sum += arr[i][idx+j];
                }

                cnt += 2;
            }

            for(int i = n/2; i<n; i++){
                int idx = (n-cnt)/2;
                for(int j = 0; j<cnt; j++){
                    sum += arr[i][idx+j];
                }
                cnt -= 2;
            }


            sb.append("#").append(t).append(" ").append(sum).append("\n");

        }

        System.out.println(sb);
    }
}
