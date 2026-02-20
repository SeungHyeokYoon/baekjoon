import java.io.*;
import java.util.*;

public class Main{

    static int n, m, x, y, k;
    static int[][] map;
    static int[] dice;
    static int[] point;

    static final int[] dx = {0, 1, -1, 0, 0};
    static final int[] dy = {0, 0, 0, -1, 1};

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dice = new int[7];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<k; i++){
            play(Integer.parseInt(st.nextToken()));
        }

        System.out.println(sb);

    }

    static void play(int dir){
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 0 || ny < 0 || nx >= m || ny >= n) return;

        x = nx;
        y = ny;

        int t = dice[6];

        if(dir == 1){
            dice[6] = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[4];
            dice[4] = t;
        }
        else if(dir == 2){
            dice[6] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[3];
            dice[3] = t;
        }
        else if(dir == 3){
            dice[6] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[2];
            dice[2] = t;
        }
        else if(dir == 4){
            dice[6] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[5];
            dice[5] = t;
        }

        if(map[y][x] == 0){
            map[y][x] = dice[1];
        } else {
            dice[1] = map[y][x];
            map[y][x] = 0;
        }

        sb.append(dice[6]).append("\n");
    }
}