import java.io.*;
import java.util.*;

public class bj14620 {

    static int n, price;
    static int[][] ground;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        ground = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                ground[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        price = Integer.MAX_VALUE;
        flower(0, 0);

        System.out.println(price);


    }

    static void flower(int depth, int sum){

        if(depth == 3){
            price = Math.min(price, sum);
            return;
        }


        for(int i = 1; i<n-1; i++){
            for(int j = 1; j<n-1; j++){
                if(ground[i][j] == -1 || ground[i-1][j] == -1 || ground[i][j-1] == -1 || ground[i+1][j] == -1 || ground[i][j+1] == -1){
                    continue;
                }

                int[] num = {ground[i][j], ground[i-1][j], ground[i+1][j], ground[i][j-1], ground[i][j+1]};

                ground[i][j] = -1;
                ground[i-1][j] = -1;
                ground[i+1][j] = -1;
                ground[i][j-1] = -1;
                ground[i][j+1] = -1;

                flower(depth+1, sum + num[0] + num[1] + num[2] + num[3] + num[4]);

                ground[i][j] = num[0];
                ground[i-1][j] = num[1];
                ground[i+1][j] = num[2];
                ground[i][j-1] = num[3];
                ground[i][j+1] = num[4];
            }
        }
    }



}
