import java.io.*;
import java.util.*;

public class Main{

    static int n, m, min;
    static int[][] room;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        room = new int[n][m];
        visited = new boolean[n][m][n+m];

        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<m; j++){
                room[i][j] = str.charAt(j) - '0';
            }
        }

        min = Integer.MAX_VALUE;

        bfs();
        System.out.println(min);




    }

    static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        visited[0][0][0] = true;
        q.add(new Node(0, 0, 0));


        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == m-1 && now.y == n-1){
                min = Math.min(min, now.oneCount);
                continue;
            }

            if(now.oneCount > min) continue;
            if(now.oneCount > n+m-2) continue;

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx>=0 && ny>=0 && nx<m && ny<n){
                    int nowone = now.oneCount;
                    if(room[ny][nx] == 1) nowone++;
                    if(!visited[ny][nx][nowone]){
                        visited[ny][nx][nowone] = true;
                        q.add(new Node(nx, ny, nowone));
                    }
                }
            }
        }
    }

    static class Node{
        int x;
        int y;
        int oneCount;

        Node(int x, int y, int oneCount){
            this.x = x;
            this.y = y;
            this.oneCount = oneCount;
        }
    }



}