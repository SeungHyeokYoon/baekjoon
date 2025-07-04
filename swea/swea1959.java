import java.io.*;
import java.util.*;

public class swea1959 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[][] arr = new int[2][Math.max(a, b)];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<a; i++){
                arr[0][i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<b; i++){
                arr[1][i] = Integer.parseInt(st.nextToken());
            }
            
            int max = Integer.MIN_VALUE;
            if(a == b){
                max = 0;
                for(int i = 0; i<a; i++){
                    max += arr[0][i]*arr[1][i];
                }
                sb.append("#").append(t).append(" ").append(max).append("\n");
                continue;
            }


            int small = (a < b) ? 0 : 1;
            int big = (small == 0) ? 1 : 0;

            for(int i = 0; i<Math.abs(a-b)+1; i++){
                int sum = 0;
                for(int j = 0; j<Math.min(a, b); j++){
                    sum += arr[small][j]*arr[big][j+i];
                }
                max = Math.max(max, sum);
            }

            sb.append("#").append(t).append(" ").append(max).append("\n");

        }

        System.out.println(sb);
    }
}
