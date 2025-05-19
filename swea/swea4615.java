import java.io.*;
import java.util.*;

public class swea4615 {

    static int n, m;
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            int[][] board = new int[n][n];
            board[n/2-1][n/2-1] = 2;
            board[n/2][n/2] = 2;
            board[n/2-1][n/2] = 1;
            board[n/2][n/2-1] = 1;


            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int color = Integer.parseInt(st.nextToken());
                board[y][x] = color;

                for(int j = 0; j<8; j++){
                    int nowx = x + dx[j];
                    int nowy = y + dy[j];
                    boolean can = false;
                    while(nowx>=0 && nowy>=0 && nowx<n && nowy<n){
                        if(board[nowy][nowx] == 0){
                            break;
                        }

                        if(board[nowy][nowx] == color){
                            can = true;
                            break;
                        }

                        nowx += dx[j];
                        nowy += dy[j];
                    }

                    if(can){
                        nowx -= dx[j];
                        nowy -= dy[j];
                        while(board[nowy][nowx] != color){
                            board[nowy][nowx] = color;
                            nowx -= dx[j];
                            nowy -= dy[j];
                        }
                    }
                }
            }

            int black = 0;
            int white = 0;
            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    if(board[i][j] == 1) black++;
                    else if(board[i][j] == 2) white++;
                }
            }

            sb.append("#").append(tc).append(" ").append(black).append(" ").append(white).append("\n");
        }

        System.out.println(sb);
    }
}
