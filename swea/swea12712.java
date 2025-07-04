import java.io.*;
import java.util.*;

public class swea12712 {

    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    static int[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t<=T; t++){

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            arr = new int[n][n];

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 0;

            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    max = Math.max(max, plusShape(n, m, j, i));
                    max = Math.max(max, xShape(n, m, j, i));
                }
            }

            sb.append("#").append(t).append(" ").append(max).append("\n");

        }

        System.out.println(sb);
    }

    static int plusShape(int n, int m, int x, int y){
        int sum = arr[y][x];
        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            int count = m - 1;
            while(nx >= 0 && ny >= 0 && nx < n && ny < n && count > 0){
                count--;
                sum += arr[ny][nx];
                nx += dx[i];
                ny += dy[i];
            }
        }
        return sum;
    }

    static int xShape(int n, int m, int x, int y){
        int sum = arr[y][x];
        for(int i = 4; i<8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            int count = m - 1;
            while(nx >= 0 && ny >= 0 && nx < n && ny < n && count > 0){
                count--;
                sum += arr[ny][nx];
                nx += dx[i];
                ny += dy[i];
            }
        }
        return sum;
    }
}
