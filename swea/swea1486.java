import java.io.*;
import java.util.*;

public class swea1486{

    static int n, b;
    static Integer[] heights;
    static int min;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            heights = new Integer[n];
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                heights[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(heights, Collections.reverseOrder());

            min = Integer.MAX_VALUE;

            choose(0, 0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
            
        }

        System.out.println(sb);
    }

    static void choose(int idx, int sum){
        if(sum >= b){
            min = Math.min(min, sum-b);
            return;
        }

        if(idx == n) return;

        choose(idx+1, sum+heights[idx]);
        choose(idx+1, sum);
    }

}