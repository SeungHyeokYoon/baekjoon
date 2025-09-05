import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};
    static char[][] map;
    static boolean[][] visited;
    static int n, m;

    static class Node implements Comparable<Node>{
        int x, y, cnt;

        Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o){
            return this.cnt - o.cnt;
        };
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<m; j++){
                map[i][j] = str.charAt(j);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited[0][0] = true;
        pq.add(new Node(0, 0, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.x == m-1 && now.y == n-1){
                System.out.println(now.cnt);
                return;
            }
            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx>=0 && ny>=0 && nx<m && ny<n && !visited[ny][nx]){

                    visited[ny][nx] = true;
                    if(map[ny][nx] == '0') pq.add(new Node(nx, ny, now.cnt));
                    else pq.add(new Node(nx, ny, now.cnt+1));
                }
            }
        }


    }



}

