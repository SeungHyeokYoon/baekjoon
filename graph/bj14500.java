import java.io.*;
import java.util.*;

public class bj14500 {

    static int n ,m, max;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        paper = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                visited[i][j] = true;
                dfs(j, i, 1, paper[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(max);

    }

    static void dfs(int x, int y, int depth, int value){
        if(depth == 4){
            max = Math.max(value, max);
            return;
        }

        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0 && ny>=0 && nx<m && ny<n && !visited[ny][nx]){

                if(depth == 2) {
					visited[ny][nx] = true;
					dfs(x, y, depth+1, value+paper[ny][nx]);
					visited[ny][nx] = false;
				}

                visited[ny][nx] = true;
                dfs(nx, ny, depth+1, value+paper[ny][nx]);
                visited[ny][nx] = false;
            }
        }
    }
}
