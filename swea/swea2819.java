import java.io.*;
import java.util.*;

public class swea2819 {

    static int[][] board;
    static Set<Integer> set;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for(int t = 1; t<=n; t++){
            board = new int[4][4];
            set = new HashSet<>();

            for(int i = 0; i<4; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<4; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i<4; i++){
                for(int j = 0; j<4; j++){
                    dfs(j, i, 0, 0);
                }
            }

            sb.append("#" + t + " ").append(set.size()).append('\n');

        }

        System.out.println(sb);
    }

    static void dfs(int x, int y, int depth, int value){
        if(depth == 7){
            set.add(value);
            return;
        }

        int newval = value + board[y][x] * (int)Math.pow(10, depth);

        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<4 && ny<4){
                dfs(nx, ny, depth+1, newval);
            }
        }
    }
}
