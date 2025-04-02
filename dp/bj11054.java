import java.io.*;
import java.util.*;

public class bj11054 {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n];
        int[] lds = new int[n];
        lis = lis(arr, lis, n);
        lds = lds(arr, lds, n);

        int ans = 0;
        for(int i = 0; i<n; i++){
            lis[i] += lds[i];
            if(lis[i] > ans){
                ans = lis[i];
            }
        }

        System.out.println(ans-1);

    }

    static int[] lis(int[] arr, int[] lis, int n){
        for(int i = 0; i<n; i++){
            lis[i] = 1;
            for(int j = 0; j<i; j++){
                if(arr[j] < arr[i]){
                    lis[i] = Math.max(lis[i], lis[j]+1);
                }
            }
        }
        return lis;
    }

    static int[] lds(int[] arr, int[] lds, int n){
        for(int i = n-1; i>=0; i--){
            lds[i] = 1;
            for(int j = n-1; j>i; j--){
                if(arr[j] < arr[i]){
                    lds[i] = Math.max(lds[i], lds[j]+1);
                }
            }
        }
        return lds;
    }
}
