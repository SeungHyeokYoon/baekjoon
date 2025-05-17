import java.io.*;
import java.util.*;

public class swea1225 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t<=10; t++){

            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[8];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<8; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int idx = 0;
            Loop:
            while(true){
                for(int i = 1; i<6; i++){
                    arr[idx] -= i;
                    if(arr[idx] <= 0){
                        arr[idx] = 0;
                        idx++;
                        idx %= 8;
                        break Loop;
                    }

                    idx++;
                    idx %= 8;
                }
            }

            sb.append("#").append(t).append(" ");
            for(int i = 0; i<8; i++){
                sb.append(arr[(idx+i)%8]).append(" ");
            }
            sb.append("\n");


        }

        System.out.println(sb);
    }
}
