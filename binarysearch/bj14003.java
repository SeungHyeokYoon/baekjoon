import java.io.*;
import java.util.*;

public class bj14003 {

    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n];
        int[] idxarr = new int[n];
        
        int length = lis(lis, idxarr);
        sb.append(length).append("\n");

        length--;
        ArrayList<Integer> newarr = new ArrayList<>();
        for(int i = n-1; i>=0; i--){
            if(idxarr[i] == length){
                newarr.add(i);
                length--;
            }
            if(length<0) break;
        }

        for(int i = newarr.size()-1; i>=0; i--){
            sb.append(arr[newarr.get(i)]).append(" ");
        }

        System.out.println(sb);

    }

    static int binarySearch(int[] lis, int left, int right, int target){
        int mid = -1;

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

    static int lis(int[] lis, int[] idxarr){
        lis[0] = arr[0];
        idxarr[0] = 0;
        int length = 1;

        for(int i = 1; i<n; i++){
            if(arr[i] > lis[length-1]){
                idxarr[i] = length;
                lis[length++] = arr[i];
            }
            else{
                int idx = binarySearch(lis, 0, length-1, arr[i]);
                lis[idx] = arr[i];
                idxarr[i] = idx;
            }
        }

        return length;
    }

}
