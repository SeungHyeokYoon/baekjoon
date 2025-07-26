import java.io.*;
import java.util.*;

public class bj2568 {

    static int n;
    static int[][] wire;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        wire = new int[n][2];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            wire[i][0] = Integer.parseInt(st.nextToken());
            wire[i][1] = Integer.parseInt(st.nextToken());
        }


        Arrays.sort(wire, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });

        int[] lis = new int[n];
        int[] idxarr = new int[n];
        int length = lis_dp(lis, idxarr);
        
        sb.append(n-length).append("\n");

        int idx = length-1;
        int[] array = new int[length];
        for(int i = n-1; i>=0; i--){
            if(idxarr[i] == idx){
                array[idx] = i;
                idx--;
            }
            if (idx < 0) break;
        }
        
        idx = 0;

        for(int i = 0; i<n; i++){
            if(idx < length && array[idx] == i){
                idx++;
            }
            else{
                sb.append(wire[i][0]).append("\n");
            }
        }


        System.out.println(sb);
    }

    static int binarySearch(int[] lis, int left, int right, int target){
        int mid = 0;

        while(left<right){
            mid = (left+right)/2;
            
            if(lis[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid;
            }

        }

        return right;
    }

    static int lis_dp(int[] lis, int[] idxarr){
        lis[0] = wire[0][1];
        idxarr[0] = 0;
        int len = 1;
        for(int i = 1; i<n; i++){
            if(lis[len-1] < wire[i][1]){
                idxarr[i] = len;
                lis[len++] = wire[i][1];
            }
            else{
                int idx = binarySearch(lis, 0, len-1, wire[i][1]);
                lis[idx] = wire[i][1];
                idxarr[i] = idx;
            }
        }

        return len;
    }
}




