    import java.io.*;
    import java.util.*;

    public class Main{


        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            String a = br.readLine();
            String b = br.readLine();
            String c = br.readLine();

            int[][][] dp = new int[a.length()+1][b.length()+1][c.length()+1];

            int al = a.length();
            int bl = b.length();
            int cl = c.length();


            for(int i = 1; i<=al; i++){
                for(int j = 1; j<=bl; j++){
                    for(int k = 1; k<=cl; k++){
                        if(a.charAt(i-1) == b.charAt(j-1) && b.charAt(j-1) == c.charAt(k-1)) dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                        else dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                    }
                }
            }

            System.out.println(dp[al][bl][cl]);

        }



    }