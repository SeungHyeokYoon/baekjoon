import java.io.*;
import java.util.*;

public class bj17144{

    static int r, c, t, air;
    static int[][][] room;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        room = new int[2][r][c];

        for(int i = 0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<c; j++){
                room[0][i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 2; i<r; i++){
            if(room[0][i][0] == -1){
                air = i;
                break;
            }
        }

        while(t-- > 0){
            diffusion();
            aircondition();
        }

        int ans = 0;
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                if(room[0][i][j] > 0){
                    ans += room[0][i][j];
                }
            }
        }

        System.out.println(ans);
    }
    
    static void diffusion(){
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                if(room[0][i][j] > 0){
                    for(int k = 0; k<4; k++){
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        if(nx >= 0 && ny >= 0 && nx < c && ny < r){
                            if((nx == 0 && ny == air) || (nx == 0 && ny == air+1)) continue;

                            room[1][ny][nx] += room[0][i][j]/5;
                            room[1][i][j] -= room[0][i][j]/5;
                        }
                    }
                }
            }
        }

        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                room[0][i][j] += room[1][i][j];
                room[1][i][j] = 0;
            }
        }
    }

    static void aircondition(){
        int x = 0;
        int y = air-1;
        while(true){
            if(x == 1 && y == air){
                room[0][y][x] = 0;
                break;
            }
            else if(x == 0 && y == 0){
                room[0][y][x] = room[0][y][x+1];
                x++;
            }
            else if(x == c-1 && y == 0){
                room[0][y][x] = room[0][y+1][x];
                y++;
            }
            else if(x == c-1 && y == air){
                room[0][y][x] = room[0][y][x-1];
                x--;
            }
            else if(x == 0){
                room[0][y][x] = room[0][y-1][x];
                y--;
            }
            else if(y == 0){
                room[0][y][x] = room[0][y][x+1];
                x++;
            }
            else if(x == c-1){
                room[0][y][x] = room[0][y+1][x];
                y++;
            }
            else if(y == air){
                room[0][y][x] = room[0][y][x-1];
                x--;
            }
        }

        x = 0;
        y = air+2;

        while(true){
            if(x == 1 && y == air+1){
                room[0][y][x] = 0;
                break;
            }
            else if(x == 0 && y == r-1){
                room[0][y][x] = room[0][y][x+1];
                x++;
            }
            else if(x == c-1 && y == r-1){
                room[0][y][x] = room[0][y-1][x];
                y--;
            }
            else if(x == c-1 && y == air+1){
                room[0][y][x] = room[0][y][x-1];
                x--;
            }
            else if(x == 0){
                room[0][y][x] = room[0][y+1][x];
                y++;
            }
            else if(y == r-1){
                room[0][y][x] = room[0][y][x+1];
                x++;
            }
            else if(x == c-1){
                room[0][y][x] = room[0][y-1][x];
                y--;
            }
            else if(y == air+1){
                room[0][y][x] = room[0][y][x-1];
                x--;
            }
        }
    }
}