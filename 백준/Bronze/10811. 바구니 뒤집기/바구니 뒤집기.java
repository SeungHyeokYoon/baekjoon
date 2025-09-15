import java.io.*;
import java.util.*;

public class Main{

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1];

        for(int i = 1; i<=n; i++){
            arr[i] = i;
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            while(to > from){
                int tmp = arr[from];
                arr[from] = arr[to];
                arr[to] = tmp;

                from++;
                to--;
            }
        }

        for(int i = 1; i<=n; i++){
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);

    }
}