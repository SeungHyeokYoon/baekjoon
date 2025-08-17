import java.io.*;
import java.util.*;

public class Main{

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<n; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });

        pq.add(new int[]{0, 0, 0});
        visited[0][0] = true;

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int cnt = now[0];
            int x = now[1];
            int y = now[2];

            if(x == n-1 && y == n-1){
                System.out.println(cnt);
                break;
            }

            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0 && ny>=0 && nx<n && ny<n && !visited[ny][nx]){
                    if(map[ny][nx] == 0){
                        pq.add(new int[]{cnt+1, nx, ny});
                    }
                    else{
                        pq.add(new int[]{cnt, nx, ny});
                    }
                    visited[ny][nx] = true;
                }
            }
        }
    }

    
}