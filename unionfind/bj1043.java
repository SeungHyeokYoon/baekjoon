import java.io.*;
import java.util.*;

public class bj1043{

    static int n, m;
    static int[] parent;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int personcnt = Integer.parseInt(st.nextToken());
        if(personcnt == 0){
            System.out.println(m);
            return;
        }

        parent = new int[n+1];
        for(int i = 0; i<personcnt; i++){


            parent[Integer.parseInt(st.nextToken())] = 1;
        }

        for(int i = 0; i<m; i++){

        }



    }
}