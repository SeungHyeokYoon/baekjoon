import java.io.*;
import java.util.*;

public class swea1868 {

    static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};
    static char[][] board;
    static boolean[][] visited;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            n = Integer.parseInt(br.readLine());
            board = new char[n][n];
            visited = new boolean[n][n];

            for(int i = 0; i<n; i++){
                String str = br.readLine();
                for(int j = 0; j<n; j++){
                    board[i][j] = str.charAt(j);
                }

                int dd = str.charAt(0) - '0';
            }

            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    if(board[i][j] == '.'){
                        int count = 0;
                        for(int k = 0; k<8; k++){
                            int newx = j + dx[k];
                            int newy = i + dy[k];
                            if(newx>=0 && newy>=0 && newx<n && newy<n){
                                if(board[newy][newx] == '*') count++;
                            }
                        }

                        board[i][j] = (char)(count+'0');
                    }
                }
            }

            int count = 0;

            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    if(!visited[i][j] && board[i][j] == '0'){
                        count++;
                        dfs(j, i);
                    }
                }
            }

            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    if(!visited[i][j] && board[i][j] != '*'){
                        count++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");


        }

        System.out.println(sb);
    }

    static void dfs(int x, int y){

        visited[y][x] = true;

        for(int i = 0; i<8; i++){
            int newx = x + dx[i];
            int newy = y + dy[i];
            if(newx>=0 && newy>=0 && newx<n && newy<n){
                if(board[newy][newx] == '0' && !visited[newy][newx]){
                    dfs(newx, newy);
                }
                else{
                    visited[newy][newx] = true;
                }
            }
        }

    }
}
