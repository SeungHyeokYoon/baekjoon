import java.io.*;
import java.util.*;

public class bj2146 {

    static int[][] map;
    static boolean[][] visited;
    static int n, ans;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idx = 2;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(map[i][j] == 1){
                    bfs(j, i, idx);
                    idx++;
                }
            }
        }

        ans = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(map[i][j] != 0){
                    bridge(j, i, map[i][j]);
                }
            }
        }

        System.out.println(ans);
    }

    static void bridge(int x, int y, int num){
        Queue<location> q = new LinkedList<>();
        q.add(new location(x, y, 0));

        visited = new boolean[n][n];
        visited[y][x] = true;

        while(!q.isEmpty()){
            location now = q.poll();

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[ny][nx]){
                    if(map[ny][nx] == 0){
                        visited[ny][nx] = true;
                        q.add(new location(nx, ny, now.depth+1));
                    }
                    else if(map[ny][nx] != num){
                        ans = Math.min(ans, now.depth);
                        return;
                    }
                }
            }
        }
    }


    static void bfs(int x, int y, int idx){
        Queue<location> q = new LinkedList<>();
        q.add(new location(x, y, 0));
        visited[y][x] = true;
        map[y][x] = idx;

        while(!q.isEmpty()){
            location now = q.poll();

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[ny][nx] && map[ny][nx] == 1){
                    map[ny][nx] = idx;
                    visited[ny][nx] = true;
                    q.add(new location(nx, ny, 0));
                }
            }
        }

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
