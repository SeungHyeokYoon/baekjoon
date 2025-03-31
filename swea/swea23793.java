import java.io.*;
import java.util.*;

public class swea23793{

    static int[] row;
    static int[] ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());
            row = new int[n];
            ans = new int[3];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<n; i++){
                row[i] = Integer.parseInt(st.nextToken()); 
                ans[row[i]]++;
            }

            for(int i = 1; i<n; i++){
                int num = Integer.parseInt(br.readLine());
                ans[num]++;

                for(int j = 1; j<n; j++){
                    int val = mex(num, row[j]);
                    ans[val]++;
                    num = val;
                    row[j] = val;
                }
            }

            sb.append(ans[0]).append(" ").append(ans[1]).append(" ").append(ans[2]).append('\n');

        }

        System.out.println(sb);
    }

    static int mex(int a, int b){
        int mask = (1 << a) | (1 << b);
        if ((mask & 1) == 0) return 0;
        if ((mask & 2) == 0) return 1;
        return 2;
    }


    
}