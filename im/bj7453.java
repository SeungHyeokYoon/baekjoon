import java.io.*;
import java.util.*;

public class bj7453 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][4];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] AB = new int[N * N];
        int[] CD = new int[N * N];
        int idx = 0;

        for(int i = 0; i < N; i++){
            for (int j = 0; j < N; j++) {
                AB[idx] = arr[i][0] + arr[j][1];
                CD[idx] = arr[i][2] + arr[j][3];
                idx++;
            }
        }

        Arrays.sort(CD);

        long ans = 0;

        for(int key : AB){
            int upper = upperBound(CD, -key);
            int lower = lowerBound(CD, -key);
            ans += (upper - lower);
        }

        System.out.println(ans);
    }

    static int upperBound(int[] arr, int find){
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] > find) {
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return right;
    }

    static int lowerBound(int[] arr, int find) {
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] >= find){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return right;
    }
}
