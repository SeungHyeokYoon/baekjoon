import java.io.*;
import java.util.*;

public class Solution {

    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            ArrayList<Integer> arr = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            arr.add(Integer.parseInt(st.nextToken()));

            for(int i = 1; i<n; i++){
                int now = Integer.parseInt(st.nextToken());
                if(now > arr.get(arr.size()-1)) arr.add(now);
                else{
                    int idx = Collections.binarySearch(arr, now);
                    if(idx < 0) idx = -idx - 1;
                    arr.set(idx, now);
                }
            }


            sb.append("#").append(tc).append(" ").append(arr.size()).append("\n");
        }

        System.out.print(sb);
    }



}