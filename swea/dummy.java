import java.io.*;
import java.util.*;

public class dummy{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for(int h = 1; h<=n; h++){
            st = new StringTokenizer(br.readLine());
            int max = 0;
            int min = Integer.MAX_VALUE;
            int sum = 0;

            for(int i =0; i<10; i++){
                int random = Integer.parseInt(st.nextToken());
                if(random > max){
                    max = random;
                }
                if(random < min){
                    min = random;
                }

                sum += random;

            }

            sum -= max;
            sum -= min;

            if(sum % 8 >= 4){
                sum = sum/8 + 1;
            }
            else{
                sum = sum/8;
            }

            System.out.println('#' + h + ' ' + sum);

        }

    }


    
}