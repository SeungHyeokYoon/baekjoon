import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n+1];

        for(int i = 2; i<=n; i++){
            if(i%3 == 0 && i%2 == 0) arr[i] = Math.min(Math.min(arr[i/3], arr[i/2]), arr[i-1]) + 1;
            else if(i%3 == 0) arr[i] = Math.min(arr[i/3], arr[i-1]) + 1;
            else if(i%2 == 0) arr[i] = Math.min(arr[i/2], arr[i-1]) + 1;
            else arr[i] = arr[i-1]+1;
        }
        System.out.println(arr[n]);
    }
}