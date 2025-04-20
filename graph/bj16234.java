import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj16234 {

    static int n, l ,r;
    static int[][] A;
    static boolean[][] check;
    static boolean move;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        A = new int[n][n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int ans = 0;
        while(true){
            check = new boolean[n][n];
            move = false;

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!check[i][j]){
                        if(bfs(i, j)){
                            move = true;
                        }
                    }
                }
            }

            if(!move){
                break;
            }

            ans++;
        }

        System.out.println(ans);

    }

    static boolean bfs(int y, int x){
        Queue<Point> q = new LinkedList<>();
        List<Point> union = new ArrayList<>();

        q.add(new Point(y, x));
        union.add(new Point(y, x));
        check[y][x] = true;

        int sum = A[y][x];
        int cnt = 1;

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int d = 0; d < 4; d++){
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];

                if(ny >= 0 && nx >= 0 && ny < n && nx < n && !check[ny][nx]){
                    int diff = Math.abs(A[p.y][p.x] - A[ny][nx]);
                    if(diff >= l && diff <= r){
                        check[ny][nx] = true;
                        q.add(new Point(ny, nx));
                        union.add(new Point(ny, nx));
                        sum += A[ny][nx];
                        cnt++;
                    }
                }
            }
        }

        if (cnt == 1) return false;

        int avg = sum / cnt;
        for (Point p : union) {
            A[p.y][p.x] = avg;
        }

        return true;
        
    }

    static class Point{
        int x;
        int y;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }


}
