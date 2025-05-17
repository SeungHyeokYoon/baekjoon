import java.io.*;
import java.util.*;

public class swea1209 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t<=10; t++){

            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[100][100];

            for(int i = 0; i<100; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<100; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 0;
            int sum = 0;

            for(int i = 0; i<100; i++){
                sum = 0;
                for(int j = 0; j<100; j++){
                    sum += arr[i][j];
                }
                max = Math.max(max, sum);
            }

            for(int i = 0; i<100; i++){
                sum = 0;
                for(int j = 0; j<100; j++){
                    sum += arr[j][i];
                }
                max = Math.max(max, sum);
            }

            sum = 0;
            for(int i = 0; i<100; i++){
                sum += arr[i][i];
            }
            max = Math.max(max, sum);

            sum = 0;
            for(int i = 0; i<100; i++){
                sum += arr[i][99-i];
            }
            max = Math.max(max, sum);


            sb.append("#").append(t).append(" ").append(max).append("\n");

        }

        System.out.println(sb);
    }
}
