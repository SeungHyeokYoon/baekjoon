import java.io.*;
import java.util.*;

public class swea1249{

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static boolean[][] visited;
    static int n;
    static int ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int i = 1; i<=T; i++){
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            visited = new boolean[n][n];

            for(int j = 0; j<n; j++){
                String str = br.readLine();
                for(int k = 0; k<n; k++){
                    board[j][k] = str.charAt(k) - '0';
                }
            }

            ans = Integer.MAX_VALUE;
            visited[0][0] = true;
            dfs(0, 0, (int)board[0][0]);

            System.out.println("#" + i + " " + ans);


        }
    }

    static void dfs(int x, int y, int sum){
        if(x == n-1 && y == n-1){
            ans = Math.min(sum, ans);
            return;
        }

        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(nx, ny, sum + (int)board[nx][ny]);
                visited[nx][ny] = false;
            }
        }


    }


    
}