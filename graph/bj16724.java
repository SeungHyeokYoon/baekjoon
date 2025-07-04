import java.io.*;
import java.util.*;

public class bj16724{

    static int n, m, ans;
    static char[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        visited  = new boolean[n][m];

        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<m; j++){
                board[i][j] = str.charAt(j);
            }
        }
        
        ans = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(board[i][j] != 'C'){
                    dfs(i, j);
                }
            }
        }

        System.out.println(ans);



    }

    static void dfs(int y, int x){
        int nx = x;
        int ny = y;

        while(true){
            visited[ny][nx] = true;

            if(board[ny][nx] == 'U'){
                if(visited[ny-1][nx]){
                    if(board[ny-1][nx] != 'C'){
                        ans++;
                    }
                    break;
                }
                else{
                    ny--;
                }
            }
            else if(board[ny][nx] == 'D'){
                if(visited[ny+1][nx]){
                    if(board[ny+1][nx] != 'C'){
                        ans++;
                    }
                    break;
                }
                else{
                    ny++;
                }
            }
            else if(board[ny][nx] == 'L'){
                if(visited[ny][nx-1]){
                    if(board[ny][nx-1] != 'C'){
                        ans++;
                    }
                    break;
                }
                else{
                    nx--;
                }
            }
            else if(board[ny][nx] == 'R'){
                if(visited[ny][nx+1]){
                    if(board[ny][nx+1] != 'C'){
                        ans++;
                    }
                    break;
                }
                else{
                    nx++;
                }
            }
        }


        while(true){
            if(board[y][x] == 'U'){
                board[y][x] = 'C';
                y--;
            }
            else if(board[y][x] == 'D'){
                board[y][x] = 'C';
                y++;
            }
            else if(board[y][x] == 'L'){
                board[y][x] = 'C';
                x--;
            }
            else if(board[y][x] == 'R'){
                board[y][x] = 'C';
                x++;
            }
            else{
                break;
            }
        }

    }
}