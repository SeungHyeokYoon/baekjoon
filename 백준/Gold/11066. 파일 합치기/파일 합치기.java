    import java.io.*;
    import java.util.*;

    public class Main{

        static int[][] dp, val;
        static int n;


        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            while(T-->0){
                n = Integer.parseInt(br.readLine());
                st = new StringTokenizer(br.readLine());

                dp = new int[n][n];
                val = new int[n][n];

                for(int i = 0; i<n; i++){
                    int now = Integer.parseInt(st.nextToken());
                    val[i][i] = now;
                }

                for(int i = n-2; i>=0; i--){
                    for(int j = i+1; j<n; j++){
                        dp[i][j] = getMin(i, j);
                    }
                }

                sb.append(dp[0][n-1]).append("\n");
            }

            System.out.println(sb);

        }

        static int getMin(int start, int end){
            int min = Integer.MAX_VALUE;
            
            for(int i = start; i<end; i++){
                if(min > dp[start][i] + dp[i+1][end] + val[start][i] + val[i+1][end]){
                    min = dp[start][i] + dp[i+1][end] + val[start][i] + val[i+1][end];
                    val[start][end] = val[start][i] + val[i+1][end];
                }
                else if(min == dp[start][i] + dp[i+1][end] + val[start][i] + val[i+1][end]){
                    val[start][end] = Math.min(val[start][end], val[start][i] + val[i+1][end]);
                }
            }

            return min;
        }


    }