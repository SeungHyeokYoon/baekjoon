import java.io.*;
import java.util.*;

public class swea2117 {

    static int n, m, ans;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = 0;
            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    bfs(j, i);
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");

        }

        System.out.println(sb);
    }

    static void bfs(int x, int y){
        boolean[][] visited = new boolean[n][n];
        Queue<location> q = new LinkedList<>();

        int max = 0;
        int nowdepth = 1;
        int count = 0;

        q.add(new location(x, y, 1));
        visited[y][x] = true;

        while(!q.isEmpty()){
            location now = q.poll();

            if(now.depth != nowdepth){
                if(count*m-(nowdepth*nowdepth+(nowdepth-1)*(nowdepth-1)) >= 0){
                    max = count;
                }
                nowdepth++;
            }

            if(now.depth > n+1){
                break;
            }

            if(map[now.y][now.x] == 1) count++;

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx>=0 && ny>=0 && nx<n && ny<n && !visited[ny][nx]){
                    q.add(new location(nx, ny, now.depth+1));
                    visited[ny][nx] = true;
                }
            }


        }

        if(count*m-(nowdepth*nowdepth+(nowdepth-1)*(nowdepth-1)) >= 0){
            max = count;
        }

        ans = Math.max(ans, max);

    }

    static class location{
        int x;
        int y;
        int depth;

        location(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
