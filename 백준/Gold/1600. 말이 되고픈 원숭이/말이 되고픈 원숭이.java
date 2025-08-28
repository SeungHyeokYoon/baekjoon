import java.io.*;
import java.util.*;

public class Main {

    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};
    final static int[] horseDx = {-2, -2, -1, -1, 1, 1, 2, 2};
    final static int[] horseDy = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int k, n, m;
    static int[][] arr;
    static boolean[][][] visited;

    static class Point{
        int x, y, cnt, horseCnt;

        Point(int x, int y, int cnt, int horseCnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.horseCnt = horseCnt;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m][k + 1];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());

    }

    static int bfs(){
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            Point now = q.poll();

            if(now.x == m-1 && now.y == n-1){
                return now.cnt;
            }

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx>=0 && ny>=0 && nx<m && ny<n){
                    if(arr[ny][nx] != 1 && !visited[ny][nx][now.horseCnt]){
                        visited[ny][nx][now.horseCnt] = true;
                        q.add(new Point(nx, ny, now.cnt+1, now.horseCnt));
                    }
                }
            }

            if(now.horseCnt < k){
                for(int i = 0; i<8; i++){
                    int nx = now.x + horseDx[i];
                    int ny = now.y + horseDy[i];

                    if(nx>=0 && ny>=0 && nx<m && ny<n){
                        if(arr[ny][nx] != 1 && !visited[ny][nx][now.horseCnt+1]){
                            visited[ny][nx][now.horseCnt+1] = true;
                            q.add(new Point(nx, ny, now.cnt+1, now.horseCnt+1));
                        }
                    }

                }

            }
        }

        return -1;

    }
}
