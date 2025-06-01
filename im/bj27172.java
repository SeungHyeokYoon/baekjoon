import java.io.*;
import java.util.*;

public class bj27172 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] num = new int[n];
        int max = 0;
        for(int i = 0; i<n; i++){
            num[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, num[i]);
        }

        int[] nums = new int[max+1];
        for(int i = 0; i<n; i++){
            nums[num[i]] = i+1;
        }
        
        int[] score = new int[n];

        for(int i = 0; i<n; i++){
            int now = num[i]*2;
            while(now<=max){
                if(nums[now] != 0){
                    score[i]++;
                    score[nums[now]-1]--;
                }
                now += num[i];
            }
        }

        for(int i : score){
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
