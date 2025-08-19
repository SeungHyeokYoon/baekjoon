import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long sum = 0;
        int target = 1;

        for(int i = 0; i<n;  i++){
            if(arr[i] < target) continue;
            sum += arr[i] - target;
            target++;
        }

        System.out.println(sum);

    }
    


}
