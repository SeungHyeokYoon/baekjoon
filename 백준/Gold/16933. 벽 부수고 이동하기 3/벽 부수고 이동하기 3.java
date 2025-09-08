import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    static int n, m, k;
    static char[][] map;
    static boolean[][][] visited;

    static class Node implements Comparable<Node>{
        int x, y, cnt, flag, depth;

        Node(int x, int y, int cnt, int depth, int flag){
            this.x = x; this.y = y; this.cnt = cnt; this.flag = flag; this.depth = depth;
        }

        @Override
        public int compareTo(Node o){
            return this.depth - o.depth;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m][k+1];

        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<m; j++){
                map[i][j] = str.charAt(j);
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        PriorityQueue<Node> q = new PriorityQueue<>();
        visited[0][0][0] = true;
        q.add(new Node(0, 0, 0, 1, 0)); //zero is day, 1 is night

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == m-1 && now.y == n-1) return now.depth;

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx>=0 && ny>=0 && nx<m && ny<n){
                    if(map[ny][nx] == '0'){
                        if(!visited[ny][nx][now.cnt]){
                            visited[ny][nx][now.cnt] = true;
                            q.add(new Node(nx, ny, now.cnt, now.depth+1, (now.flag+1)%2));
                        }
                    }
                    else{
                        if(now.cnt < k){
                            if(!visited[ny][nx][now.cnt+1]){
                                visited[ny][nx][now.cnt+1] = true;
                                if(now.flag == 0){
                                    q.add(new Node(nx, ny, now.cnt+1, now.depth+1, 1));
                                }
                                else{
                                    q.add(new Node(nx, ny, now.cnt+1, now.depth+2, 1));
                                }
                            }
                        }
                    }
                }
            }
        }


        return -1;
    }
}

