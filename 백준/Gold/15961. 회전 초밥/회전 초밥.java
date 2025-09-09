import java.io.*;
import java.util.*;

public class Main {

    static int n, d, k, c;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+k-1];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        for(int i = n; i<n+k-1; i++){
            arr[i] = arr[i-n];
        }

        map.put(c, 1);
        for(int i = 0; i<k; i++){
            map.put(arr[i], map.containsKey(arr[i]) ? map.get(arr[i])+1 : 1);
        }


        int max = map.size();

        for(int i = 0; i<n-1; i++){
            if(map.get(arr[i]) == 1){
                map.remove(arr[i]);
            }
            else{
                map.put(arr[i], map.get(arr[i])-1);
            }

            map.put(arr[i+k], map.containsKey(arr[i+k]) ? map.get(arr[i+k])+1 : 1);
            max = Math.max(max, map.size());
        }

        System.out.println(max);




    }
}
