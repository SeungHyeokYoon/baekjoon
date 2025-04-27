import java.io.*;
import java.util.*;

public class bj1926 {

    static int n, m;
    static int[][] paper;
    static boolean[][] check;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        paper = new int[n][m];
        check = new boolean[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        int maxvalue = 0;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(!check[i][j] && paper[i][j] == 1){
                    ans++;
                    maxvalue = Math.max(bfs(j, i), maxvalue);
                }
            }
        }

        System.out.println(ans);
        System.out.println(maxvalue);

    }

    static int bfs(int x, int y){
        Queue<Integer> q = new LinkedList<>();

        int cnt = 0;
        q.add(x);
        q.add(y);
        check[y][x] = true;

        while(!q.isEmpty()){
            int nowx = q.poll();
            int nowy = q.poll();

            cnt++;

            for(int i = 0; i<4; i++){
                int newx = nowx + dx[i];
                int newy = nowy + dy[i];
                if(newx >= 0 && newy >= 0 && newx < m && newy < n && !check[newy][newx] && paper[newy][newx] == 1){
                    q.add(newx);
                    q.add(newy);
                    check[newy][newx] = true;
                }
            }
            
        }

        return cnt;
    }
}
