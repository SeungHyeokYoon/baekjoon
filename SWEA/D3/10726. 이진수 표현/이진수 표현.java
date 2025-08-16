import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            int check = (1<<n) - 1;

            if((m&check) == check){
                sb.append("#").append(tc).append(" ").append("ON").append("\n");
            }
            else{
                sb.append("#").append(tc).append(" ").append("OFF").append("\n");
            }



        }

        System.out.println(sb);
    }
}
