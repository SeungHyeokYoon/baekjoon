import java.io.*;
import java.util.*;

public class swea4193 {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static int[][] ocean;
    static int n, endx, endy;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t<=T; t++){

            n = Integer.parseInt(br.readLine());
            ocean = new int[n][n];
            visited = new boolean[n][n];

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    ocean[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());
            int starty = Integer.parseInt(st.nextToken());
            int startx = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endy = Integer.parseInt(st.nextToken());
            endx = Integer.parseInt(st.nextToken());

            int time = bfs(startx, starty);


            sb.append("#").append(t).append(" ").append(time).append("\n");

        }

        System.out.println(sb);
    }

    static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y, 0));
        visited[y][x] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == endx && now.y == endy){
                return now.time;
            }
            
            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx>=0 && ny>=0 && nx<n && ny<n){
                    if(!visited[ny][nx]){
                        if(ocean[ny][nx] == 0){
                            visited[ny][nx] = true;
                            q.add(new Node(nx, ny, now.time+1));
                        }
                        else if(ocean[ny][nx] == 2){
                            if(now.time%3 == 2){
                                visited[ny][nx] = true;
                                q.add(new Node(nx, ny, now.time+1));
                            }
                            else{
                                q.add(new Node(now.x, now.y, now.time+1));
                            }
                        }
                    }
                }
            }


        }

        return -1;

    }


    static class Node{
        int x;
        int y;
        int time;

        Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
