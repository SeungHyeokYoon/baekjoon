import java.io.*;
import java.util.*;

public class Main{

    static int k;
    static int[] arr, ans;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ans = new int[6];

        while(true){
            String str = br.readLine();
            if(str.equals("0")) break;

            st = new StringTokenizer(str);

            k = Integer.parseInt(st.nextToken());
            arr = new int[k];

            for(int i = 0; i<k; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            backTrack(0, 0);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void backTrack(int now, int level){
        if(level == 6){
            for(int i = 0; i<6; i++){
                sb.append(ans[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = now; i<k; i++){
            ans[level] = arr[i];
            backTrack(i+1, level+1);
        }
    }

}