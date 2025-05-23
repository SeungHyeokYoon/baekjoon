import java.io.*;
import java.util.*;


public class bj11048 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] maze = new int[n][m];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                int a = (i-1>=0) ? maze[i-1][j] + maze[i][j] : maze[i][j];
                int b = (j-1>=0) ? maze[i][j-1] + maze[i][j] : maze[i][j];
                int c = (i-1>=0 && j-1>=0) ? maze[i-1][j-1] + maze[i][j] : maze[i][j];

                maze[i][j] = Math.max(Math.max(a, b), c);
            }
        }

        System.out.println(maze[n-1][m-1]);

    }

}
