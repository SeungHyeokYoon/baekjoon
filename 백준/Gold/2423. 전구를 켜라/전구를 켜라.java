import java.io.*;
import java.util.*;

public class Main {

    static final int[][] dx = {{1, -1, 0, 0, 1, -1}, {1, -1, 0, 0, 1, -1}};
    static final int[][] dy = {{0, 0, 1, -1, 1, -1}, {0, 0, 1, -1, -1, 1}};
    static int n, m;
    static int[][] map;
    static int[][] dp;

    static class Node implements Comparable<Node>{
        int x, y, cnt, type;

        Node(int x, int y, int cnt, int type){
            this.x = x; this.y = y; this.cnt = cnt; this.type = type;
        }

        @Override
        public int compareTo(Node o){
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if((n+m)%2 != 0){
            System.out.println("NO SOLUTION");
            return;
        }

        map = new int[n][m];
        dp = new int[n][m];
        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<m; j++){
                map[i][j] = (str.charAt(j) == '/') ? 1 : 0;
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dijkstra();

        System.out.println(dp[n-1][m-1]);
    }

    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dp[0][0] = map[0][0]==0 ? 0 : 1;
        pq.add(new Node(0, 0, (map[0][0]==1 ? 1 : 0), 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.cnt > dp[now.y][now.x]) continue;

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[now.type][i];
                int ny = now.y + dy[now.type][i];

                if(nx>=0 && ny>=0 && nx<m && ny<n){
                    int add = (now.type==map[ny][nx]) ? 1 : 0;
                    if(dp[ny][nx] > now.cnt + add){
                        dp[ny][nx] = now.cnt + add;
                        if(nx == m-1 && ny == n-1) return;
                        pq.add(new Node(nx, ny, now.cnt+add, Math.abs(now.type-1)));
                    }
                }
            }

            for(int i = 4; i<6; i++){
                int nx = now.x + dx[now.type][i];
                int ny = now.y + dy[now.type][i];

                if(nx>=0 && ny>=0 && nx<m && ny<n){
                    int add = (now.type==map[ny][nx]) ? 0 : 1;
                    if(dp[ny][nx] > now.cnt + add){
                        dp[ny][nx] = now.cnt + add;
                        if(nx == m-1 && ny == n-1) return;
                        pq.add(new Node(nx, ny, now.cnt+add, now.type));
                    }
                }
            }
        }
    }

}
//i+j is even, direction must be '\'
//i+j is odd, direction must be '/'
