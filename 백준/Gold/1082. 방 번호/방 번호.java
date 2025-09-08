import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] price = new int[n];

        st = new StringTokenizer(br.readLine());

        int min = Integer.MAX_VALUE;
        int idx = -1;
        for(int i = 0; i<n; i++){
            price[i] = Integer.parseInt(st.nextToken());
            if(min >= price[i]){
                min = price[i];
                idx = i;
            }
        }

        int M = Integer.parseInt(br.readLine());
        int num = M/min;
        int[] arr = new int[num];
        for(int i = 0; i<num; i++){
            arr[i] = idx;
        }
        M -= num*min;
        

        if(idx == 0){
            int nextMin = Integer.MAX_VALUE;
            for(int i = 0; i<n; i++){
                if(price[i] != min && nextMin >= price[i]){
                    arr[0] = i;
                    nextMin = price[i];
                }
            }   

            while(M - (price[arr[0]] - min) < 0){
                num--;
                M += min;
            }

            if(num <= 0){
                System.out.println(0);
                return;
            }
        }

        int now = 0;

        while(now < num){
            boolean can = false;
            for(int i = n-1; i>=idx; i--){
                if(M >= price[i] - min){
                    can = true;
                    arr[now++] = i;
                    M -= price[i] - min;
                    break;
                }
            }

            if(!can) break;
        }

        for(int i = 0; i<num; i++){
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}

