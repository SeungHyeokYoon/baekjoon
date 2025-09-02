import java.io.*;
import java.util.*;

public class Main{

    static int n, INF = 987654321;
    static int[][] room;
    static int[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int idx = 1;

        while(true){
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            room = new int[n][n];
            visited = new int[n][n];

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    room[i][j] = Integer.parseInt(st.nextToken());
                    visited[i][j] = INF;
                }
            }

            bfs();

            sb.append("Problem ").append(idx).append(": ").append(visited[n-1][n-1]).append("\n");
            idx++;
        }

        System.out.println(sb);






    }

    static void bfs(){
        PriorityQueue<Node> q = new PriorityQueue<>();
        visited[0][0] = room[0][0];
        q.add(new Node(0, 0, room[0][0]));

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == n-1 && now.y == n-1){
                visited[now.y][now.x] = Math.min(visited[now.y][now.x], now.value);
            }
            if(now.value > visited[n-1][n-1]) continue;

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx>=0 && ny>=0 && nx<n && ny<n){
                    if(now.value + room[ny][nx] < visited[ny][nx]){
                        visited[ny][nx] = now.value + room[ny][nx];
                        q.add(new Node(nx, ny, visited[ny][nx]));
                    }
                }
            }
        }
    }



    static class Node implements Comparable<Node>{
        int x;
        int y;
        int value;

        Node(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }



}