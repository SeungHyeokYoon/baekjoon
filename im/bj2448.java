import java.io.*;
import java.util.*;

public class bj2448{

    static String[] star;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        star = new String[n];
        star[0] = "  *  ";
        star[1] = " * * ";
        star[2] = "*****";

        for(int i = 1; 3*Math.pow(2, i)<=n; i++){
            starmaker(i);
        }
        
        for(int i = 0; i<n; i++){
            sb.append(star[i]).append('\n');
        }

        System.out.println(sb);
    }


    static void starmaker(int i){
        int bot = (int)(3*Math.pow(2, i));
        int mid = bot/2;

        for(int j = mid; j<bot; j++){
            star[j] = star[j-mid] + " " + star[j-mid];
        }

        String blank = " ".repeat(mid);
        for(int j = 0; j<mid; j++){
            star[j] = blank + star[j] + blank;
        }
    }
    
}