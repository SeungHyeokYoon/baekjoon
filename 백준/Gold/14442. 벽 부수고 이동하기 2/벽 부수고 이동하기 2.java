import java.io.*;
import java.util.*;

public class Main{

    static int n, m, k;
    static int[][] maze;
    static boolean[][][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        visited = new boolean[k+1][n][m];

        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<m; j++){
                maze[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());


        
    }


    static int bfs(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();
            if(now.x == m-1 && now.y == n-1){
                return now.length;
            }

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx>=0 && ny>=0 && nx<m && ny<n){
                    if(maze[ny][nx] == 1){
                        if(now.breakWallLeft<k && !visited[now.breakWallLeft +1][ny][nx]){
                            q.add(new Node(nx, ny, now.breakWallLeft+1, now.length+1));
                            visited[now.breakWallLeft+1][ny][nx] = true;
                        }
                    }
                    else{
                        if(!visited[now.breakWallLeft][ny][nx]){
                            q.add(new Node(nx, ny, now.breakWallLeft, now.length+1));
                            visited[now.breakWallLeft][ny][nx] = true;
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
        int breakWallLeft;
        int length;

        Node(int x, int y, int breakWallLeft, int length){
            this.x = x;
            this.y = y;
            this.breakWallLeft = breakWallLeft;
            this.length = length;
        }
    }





}
