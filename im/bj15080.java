import java.io.*;
import java.util.*;

public class bj15080 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " : ");
        int h1 = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int s1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " : ");
        int h2 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int s2 = Integer.parseInt(st.nextToken());

        int time1 = h1*3600 + m1*60 + s1;
        int time2 = h2*3600 + m2*60 + s2;

        if(time2 < time1){
            System.out.println(time2-time1+3600*24);
        }
        else{
            System.out.println(time2-time1);
        }

    }
}
