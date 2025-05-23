import java.io.*;
import java.util.*;

public class swea4131 {

    static int n, x;
    static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc  = 1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            map = new int[n][n];

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = 0;

            for(int i = 0; i<n; i++){
                if(check(map[i])) ans++;

                int[] colArr = new int[n];
                for(int j = 0; j < n; j++){
                    colArr[j] = map[j][i];
                }

                if(check(colArr)) ans++;
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");

        }
        System.out.println(sb);

            
    }

    static boolean check(int[] arr){
        boolean[] visited = new boolean[n];
        int idx = 0;

        while(idx<n-1){
            if(arr[idx] < arr[idx+1]){
                if(arr[idx+1]-arr[idx] > 1){
                    return false;
                }

                int idxtemp = idx;

                for(int i = 0; i<x; i++){
                    if(idxtemp<0 || arr[idxtemp] != arr[idx] || visited[idxtemp]) return false;

                    visited[idxtemp] = true;
                    idxtemp--;
                }
            }
            else if(arr[idx] > arr[idx+1]){
                if(arr[idx]-arr[idx+1] > 1){
                    return false;
                }

                int idxtemp = idx+1;

                for(int i = 0; i<x; i++){
                    if(idxtemp>=n || arr[idxtemp] != arr[idx+1] || visited[idxtemp]) return false;

                    visited[idxtemp] = true;
                    idxtemp++;
                }
            }

            idx++;
        }


        return true;

    }

}
