import java.io.*;
import java.util.*;

public class swea14510 {

    static int twos, days, sum;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            int n = Integer.parseInt(br.readLine());
            int[] height = new int[n];
            st = new StringTokenizer(br.readLine());
            sb.append("#").append(tc).append(" ");

            int max = 0;
            for(int i = 0; i<n; i++){
                height[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, height[i]);
            }

            sum = 0;
            twos = 0;
            days  = 0;
            for(int i = 0; i<n; i++){
                height[i] = max - height[i];
                if(height[i]%2 != 0){
                    twos++;
                    days+=2;
                    height[i]--;
                }
                sum += height[i];
            }
            days = (days>0) ? days - 1 : 0;
            twos = (twos>0) ? twos - 1 : 0;
            if(sum <= twos*2){
                sb.append(days).append("\n");
                continue;
            }

            if(days > 0){
                sum -= twos*2;
                if(sum%6 == 0){
                    sb.append((sum/6)*4 + days).append("\n");
                }
                else if(sum%6 == 2){
                    sb.append((sum/6)*4 + 1 + days).append("\n");
                }
                else if(sum%6 == 4){
                    sb.append((sum/6)*4 + 3 + days).append("\n");
                }
            }
            else{
                if(sum%6 == 0){
                    sb.append((sum/6)*4 + days).append("\n");
                }
                else if(sum%6 == 2){
                    sb.append((sum/6)*4 + 2 + days).append("\n");
                }
                else if(sum%6 == 4){
                    sb.append((sum/6)*4 + 3 + days).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}
