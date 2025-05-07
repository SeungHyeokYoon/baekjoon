import java.io.*;
import java.util.StringTokenizer;

public class swea1247{

    static int[][] points;
    static boolean[] visited;
    static int n, ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for(int T = 1; T <= tc; T++){
            n = Integer.parseInt(br.readLine());
            n += 2;
            points = new int[n][2];
            visited = new boolean[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<n; i++){
                points[i][0] = Integer.parseInt(st.nextToken());
                points[i][1] = Integer.parseInt(st.nextToken());
            }

            ans = Integer.MAX_VALUE;
            dfs(0, 0, 0);

            sb.append("#").append(T).append(" ").append(ans).append("\n");

        }

        System.out.println(sb);

    }

    static void dfs(int prev, int value, int cnt){

        if(cnt == n-2){
            ans = Math.min(ans, value + Math.abs(points[prev][0] - points[1][0]) + Math.abs(points[prev][1] - points[1][1]));
            return;
        }

        for(int i = 2; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, value + Math.abs(points[prev][0] - points[i][0]) + Math.abs(points[prev][1] - points[i][1]), cnt + 1);
                visited[i] = false;
            }
        }
    }
}