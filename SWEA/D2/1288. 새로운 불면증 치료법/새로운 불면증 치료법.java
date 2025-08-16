import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=T; tc++){
            int n = Integer.parseInt(br.readLine());
            int visited = (1<<10)-1;

            int now = 0;
            int count = 1;
            while(true){
                char[] arr = String.valueOf(n*count).toCharArray();
                for(int i = 0; i<arr.length; i++){
                    now |= 1<<(arr[i] - '0');
                }

                if(visited == now) break;
                count++;
            }

            sb.append("#").append(tc).append(" ").append(count*n).append("\n");

        }

        System.out.println(sb);
    }
}
