import java.io.*;
import java.util.*;

public class swea1226 {

    static int[][] maze;
    static boolean[][] visited;
    static int sx, sy, ex, ey;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= 10; t++){
            maze = new int[16][16];
            visited = new boolean[16][16];

            br.readLine();
            for(int i = 0; i<16; i++){
                String str = br.readLine();
                for(int j = 0; j<16; j++){
                    maze[i][j] = str.charAt(j) - '0';
                    if(maze[i][j] == 2){
                        sx = j;
                        sy = i;
                    }
                    else if(maze[i][j] == 3){
                        ex = j;
                        ey = i;
                    }
                }
            }

            sb.append("#" + t + " ");
            if(bfs(sx, sy)){
                sb.append(1).append('\n');
            }
            else{
                sb.append(0).append('\n');
            }
            

        }

        System.out.println(sb);

    }

    static boolean bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[y][x] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx>=0 && ny>=0 && nx<16 && ny<16){
                    if(maze[ny][nx] == 3){
                        return true;
                    }

                    if(!visited[ny][nx] && maze[ny][nx] == 0){
                        q.add(new Node(nx, ny));
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        return false;
    }

    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
