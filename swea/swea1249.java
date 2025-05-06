import java.io.*;
import java.util.*;

public class swea1249{

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static int[][] dp;
    static boolean[][] visited;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for(int i = 1; i<=T; i++){
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            visited = new boolean[n][n];
            dp = new int[n][n];

            for(int j = 0; j<n; j++){
                String str = br.readLine();
                for(int k = 0; k<n; k++){
                    board[j][k] = str.charAt(k) - '0';
                }
            }

            bfs();

            System.out.println("#" + i + " " + dp[n-1][n-1]);


        }
    }

    static void bfs(){
        Queue<Hole> q = new LinkedList<>();
        q.add(new Hole(0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Hole now = q.poll();

            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                    if(!visited[ny][nx] || dp[ny][nx] > dp[now.y][now.x] + board[ny][nx]){
                        visited[ny][nx] = true;
                        dp[ny][nx] = dp[now.y][now.x] + board[ny][nx];
                        q.add(new Hole(nx, ny));
                    }
                }
            }
        }
    }

    static class Hole{
        int x;
        int y;

        Hole(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    
}