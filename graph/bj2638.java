import java.io.*;
import java.util.*;

public class bj2638{

    static int n, m;
    static int[][] map;
    static boolean[][] airmap;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        airmap = new boolean[n][m];


        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> q = new LinkedList<>();      

        dfs(0, 0);

        boolean finish = true;
        int ans = -1;
        while(finish){
            ans++;
            finish = false;
            for(int i = 1; i<n-1; i++){
                for(int j = 1; j<m-1; j++){
                    if(!airmap[i][j] && map[i][j] == 1 && checkdel(j, i)){
                        q.add(i);
                        q.add(j);
                        finish = true;
                    }
                }
            }

            while(!q.isEmpty()){
                int y = q.poll();
                int x = q.poll();

                map[y][x] = 0;
                dfs(x, y);
            }
        }

        System.out.println(ans);

        
        
    }
    
    static void dfs(int x, int y){
        airmap[y][x] = true;
        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >=0 && ny >= 0 && nx < m && ny < n && !airmap[ny][nx] && map[ny][nx] == 0){
                dfs(nx, ny);
            }
        }
    }

    static boolean checkdel(int x, int y){
        int cnt = 0;

        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >=0 && ny >= 0 && nx < m && ny < n && airmap[ny][nx]){
                cnt++;
            }
        }

        return cnt < 2 ? false : true;

    }
}