import java.io.*;
import java.util.*;

public class swea1206{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int i = 1; i<11; i++){
            int n = Integer.parseInt(br.readLine());
            int[] building = new int[n];
            st = new StringTokenizer(br.readLine());
            int count = 0;

            for(int j = 0; j<n; j++){
                building[j] = Integer.parseInt(st.nextToken());
            }

            for(int j = 2; j<n-2; j++){
                int room = Math.min(building[j]-building[j-2], Math.min(building[j]-building[j-1], Math.min(building[j]-building[j+1], building[j]-building[j+2])));

                if(room > 0){
                    count += room;
                }
            }

            System.out.println("#"+ i + ' ' + count);



        }
        
    }


    
}