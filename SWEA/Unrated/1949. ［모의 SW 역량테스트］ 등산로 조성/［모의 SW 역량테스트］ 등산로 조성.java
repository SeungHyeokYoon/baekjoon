import java.io.*;
import java.util.*;

public class Solution {

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;
    static int n, k, ans;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            visited = new boolean[n][n];
            ans = 1;

            int max = 0;

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }


            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    if(map[i][j] == max) backTrack(j, i, false, 1);
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

    static void backTrack(int x, int y, boolean used, int depth){
        int now = map[y][x];
        visited[y][x] = true;

        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<n && ny<n && !visited[ny][nx]){
                int next = map[ny][nx];

                if(next < now){
                    backTrack(nx, ny, used, depth+1);
                }
                else if(!used){
                    if(next - k < now){
                        map[ny][nx] = now-1;
                        backTrack(nx, ny, !used, depth+1);
                        map[ny][nx] = next;
                    }
                }
            }
        }


        visited[y][x] = false;
        ans = Math.max(ans, depth);
    }



}