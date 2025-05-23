import java.io.*;
import java.util.*;

public class swea2806 {

    static int n, cnt;
    static int[][] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            n = Integer.parseInt(br.readLine());

            board = new int[n][n];
            cnt = 0;
            putQ(0);

            sb.append("#").append(tc).append(" ").append(cnt).append("\n");

        }

        System.out.println(sb);
    }

    static void putQ(int y){
        if(y == n){
            cnt++;
            return;
        }

        for(int i = 0; i<n; i++){
            if(check(i, y)){
                board[y][i] = 1;
                putQ(y+1);
                board[y][i] = 0;
            }
        }


    }


    static boolean check(int x, int y){
        for(int i = 0; i<n; i++){
            if(board[i][x] == 1) return false;
            if(board[y][i] == 1) return false;
        }

        int x1 = x-1;
        int y1 = y-1;

        while(x1>=0 && y1>=0){
            if(board[y1][x1] == 1) return false;
            x1--;
            y1--;
        }

        int x2 = x+1;
        int y2 = y+1;

        while(x2<n && y2<n){
            if(board[y2][x2] == 1)return false;
            x2++;
            y2++;
        }

        int x3 = x+1;
        int y3 = y-1;

        while(x3<n && y3>=0){
            if(board[y3][x3] == 1)return false;
            x3++;
            y3--;
        }

        int x4 = x-1;
        int y4 = y+1;

        while(x4>=0 && y4<n){
            if(board[y4][x4] == 1)return false;
            x4--;
            y4++;
        }

        return true;

    }
}
