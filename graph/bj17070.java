import java.io.*;
import java.util.*;

public class bj17070{

    static int[][] board;
    static int n, ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                board[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        dfs(1, 0, 0);

        System.out.println(ans);

    }

    static void dfs(int x, int y, int dir){
        /* if dir == 0 horizental
           if dir == 1 diagonal
           if dir == 2 vertical */

        if(x == n-1 && y == n-1){
            ans++;
            return;
        }

        switch(dir){
            case 0:
                hor(x, y);
                break;
            case 1:
                dia(x, y);
                break;
            case 2:
                ver(x, y);
                break;
        }

    }

    static void hor(int x, int y){
        if(x < n-1 && board[x+1][y] == 0){
            dfs(x+1, y, 0);
        }
        if(x < n-1 && y < n-1 && board[x+1][y] == 0 && board[x+1][y+1] == 0 && board[x][y+1] == 0){
            dfs(x+1, y+1, 1);
        }
    }

    static void dia(int x, int y){
        if(x < n-1 && board[x+1][y] == 0){
            dfs(x+1, y, 0);
        }
        if(x < n-1 && y < n-1 && board[x+1][y] == 0 && board[x+1][y+1] == 0 && board[x][y+1] == 0){
            dfs(x+1, y+1, 1);
        }
        if(y < n-1 && board[x][y+1] == 0){
            dfs(x, y+1, 2);
        }
    }

    static void ver(int x, int y){
        if(x < n-1 && y < n-1 && board[x+1][y] == 0 && board[x+1][y+1] == 0 && board[x][y+1] == 0){
            dfs(x+1, y+1, 1);
        }
        if(y < n-1 && board[x][y+1] == 0){
            dfs(x, y+1, 2);
        }
    }

    
}