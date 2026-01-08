import java.io.*;
import java.util.*;

public class Main{

    static int n;
    static char[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        int left = 10000;
        int right = -1;
        int top = 10000;
        int bottom = -1;

        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<n; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'G'){
                    left = Math.min(left, j);
                    top = Math.min(top, i);;
                    right = Math.max(right, j);
                    bottom = Math.max(bottom, i);
                }
            }
        }

        int cnt = 0;

        if(left != right) cnt += Math.min(right, n-left-1);
        if(top != bottom) cnt += Math.min(bottom, n-top-1);

        System.out.println(cnt);
    }


}