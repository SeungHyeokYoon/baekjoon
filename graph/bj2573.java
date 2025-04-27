import java.io.*;
import java.util.*;

public class bj2573{

    static int n, m;
    static int[][] sea;
    static boolean[][] check;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sea = new int[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                sea[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        Loop1:
        while(true){
            int cnt = melting();
            if(cnt == 0){
                System.out.println(0);
                return;
            }
            ans++;

            Loop2:
            for(int i = 0; i<n; i++){
                for(int j = 0; j<m; j++){
                    if(sea[i][j] != 0){
                        if(cnt != bfs(j, i)){
                            break Loop1;
                        }
                        break Loop2;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static int melting(){
        int cnt = 0;
        Queue<ice> q = new LinkedList<>();
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(sea[i][j] != 0){
                    int zerocnt = 0;
                    for(int k = 0; k<4; k++){
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        if(nx >= 0 && ny >= 0 && nx < m && ny < n && sea[ny][nx] == 0){
                            zerocnt++;
                        }
                    }
                    int value = sea[i][j]-zerocnt;
                    if(value <= 0){
                        q.add(new ice(j, i));
                    }
                    else{
                        sea[i][j] = value;
                        cnt++;
                    }
                }
            }
        }

        while(!q.isEmpty()){
            ice now = q.poll();
            sea[now.y][now.x] = 0;
        }

        return cnt;
    }

    static int bfs(int x, int y){
        int cnt = 1;
        Queue<ice> q = new LinkedList<>();
        check = new boolean[n][m];
        q.add(new ice(x, y));
        check[y][x] = true;

        while(!q.isEmpty()){
            ice now = q.poll();

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < m && ny < n){
                    if(sea[ny][nx] != 0 && !check[ny][nx]){
                        q.add(new ice(nx, ny));
                        check[ny][nx] = true;
                        cnt++;
                    }
                }
            }

        }
        return cnt;
    }


    static class ice{
        int x;
        int y;

        ice(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}