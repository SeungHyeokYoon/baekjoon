import java.io.*;
import java.util.*;

public class swea5658 {

    static Set<Integer> set;
    static int n, k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            String str = br.readLine();
            set = new HashSet<>();

            int idx = 0;
            int rotate = n / 4;
            
            for(int k = 0; k<rotate; k++){
                for(int i = 0; i<4; i++){
                    String str1 = "";
                    for(int j = 0; j<rotate; j++){
                        str1 += String.valueOf(str.charAt(idx%str.length()));   
                        idx++;
                    }

                    set.add(Integer.parseInt(str1, 16));
                }
                idx++;
            }

            ArrayList<Integer> arr = new ArrayList<>();
            for(Integer i : set){
                arr.add(i);
            }

            Collections.sort(arr, Collections.reverseOrder());



            sb.append("#").append(tc).append(" ").append(arr.get(k-1)).append("\n");
        }

        System.out.println(sb);


    }


}
