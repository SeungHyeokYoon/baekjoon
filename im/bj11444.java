import java.io.*;
import java.util.*;

public class bj11444 {

    static long n;
    static long[][] originmatrix = {{1, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Long.parseLong(br.readLine());

        if(n == 1L|| n == 2L){
            System.out.println(1);
            return;
        }

        long[][] fibmatrix = divideconquer(originmatrix, n-2);

        System.out.println((fibmatrix[0][0] + fibmatrix[0][1])%1000000007);


    }

    static long[][] divideconquer(long[][] mat, long n){
        if(n == 1L){
            return mat;
        }

        long[][] cur = divideconquer(mat, n/2);

        cur = pow(cur, cur);

        if(n%2 != 0){
            cur = pow(cur, originmatrix);
        }

        return cur;
    }

    static long[][] pow(long[][] a, long[][] b){
        long[][] cur = new long[2][2];

        for(int i = 0; i<2; i++){
            for(int j = 0; j<2; j++){
                for(int k = 0; k<2; k++){
                    cur[i][j] += a[i][k]*b[k][j];
                    cur[i][j] %= 1000000007;
                }
            }
        }
        return cur;
    }
}
