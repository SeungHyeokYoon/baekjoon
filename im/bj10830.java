import java.io.*;
import java.util.*;

public class bj10830{

    static int[][] matrix;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());

        matrix = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken())%1000;
            }
        }

        int[][] ans = powerMatrix(matrix, b);

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                sb.append(ans[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);


        

    }

    static int[][] powerMatrix(int[][] mat, long b){
        if(b == 1){
            return mat;
        }

        int[][] cur = powerMatrix(mat, b/2);

        cur = pow(cur, cur);

        if(b%2 != 0){
            cur = pow(cur, matrix);
        }

        return cur;


    }

    static int[][] pow(int[][] a, int[][] b){
        int[][] cur = new int[n][n];
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                for(int k = 0; k<n; k++){
                    cur[i][j] += a[i][k]*b[k][j];
                    cur[i][j] %=1000;
                }
            }
        }

        return cur;
    }

    
}