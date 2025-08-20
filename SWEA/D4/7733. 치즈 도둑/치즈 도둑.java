import java.io.*;
import java.util.*;

public class Solution {

    static int n, cnt;
    static int[][] cheeze;
    static boolean[][] visited;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            n = Integer.parseInt(br.readLine());
            cheeze = new int[n][n];

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    cheeze[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 1;

            for(int day = 1; day<=100; day++){
                visited = new boolean[n][n];
                int partCnt = 0;
                for(int i = 0; i<n; i++){
                    for(int j = 0; j<n; j++){
                        if(!visited[i][j] && cheeze[i][j] > day){
                            dfs(j, i, day);
                            partCnt++;
                        }
                    }
                }
                max = Math.max(max, partCnt);
            }


            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }


        System.out.print(sb);
    }

    static void dfs(int x, int y, int day){
        visited[y][x] = true;

        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<n && ny<n && !visited[ny][nx] && cheeze[ny][nx] > day){
                dfs(nx, ny, day);
            }
        }
    }
}