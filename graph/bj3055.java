import java.io.*;
import java.util.*;

public class bj3055 {

    static char[][] map;
    static boolean[][] visited;
    static int r, c, sx, sy;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<location> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visited = new boolean[r][c];


        for(int i = 0; i<r; i++){
            String str = br.readLine();
            for(int j = 0; j<c; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S'){
                    sx = j;
                    sy = i;
                }

                if(map[i][j] == '*'){
                    q.add(new location(j, i, 0));
                }
            }
        }

        Queue<location> g = new LinkedList<>();
        g.add(new location(sx, sy, 0));
        visited[sy][sx] = true;

        int ans = -1;

        while(!g.isEmpty()){
            location now = g.poll();
            if(map[now.y][now.x]  == '*') continue;
            if(now.depth != ans){
                flood();
                ans++;
            }
            
            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx>=0 && ny>=0 && nx<c && ny<r){
                    if(map[ny][nx] == 'D'){
                        System.out.println(ans+1);
                        return;
                    }
                    
                    if(!visited[ny][nx] && map[ny][nx] == '.'){
                        visited[ny][nx] = true;
                        g.add(new location(nx, ny, now.depth+1));
                    }
                }
            }
        }
        System.out.println("KAKTUS");

    }

    static void flood(){
        int size = q.size();

        for(int i = 0; i < size; i++){
            location now = q.poll();

            for(int j = 0; j < 4; j++){
                int nx = now.x + dx[j];
                int ny = now.y + dy[j];

                if(nx >= 0 && ny >= 0 && nx < c && ny < r){
                    if(map[ny][nx] == '.'){
                        map[ny][nx] = '*';
                        q.add(new location(nx, ny, 0));
                    }
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
