import java.io.*;
import java.util.*;

public class bj9184 {

    static int[][][] memory;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        memory  = new int[21][21][21];

        

        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1) return;
            System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a, b, c));
        }

    }

    static int w(int a, int b, int c){
        if(a<=0 || b<=0 || c<=0) return 1;
        else if(inRange(a, b, c) && memory[a][b][c] != 0) return memory[a][b][c];
        else if(a>20 || b>20 || c>20){
            return memory[20][20][20] = w(20, 20, 20);
        } 
        else if(a<b && b<c){
            return memory[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        }
        else{
            return memory[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
        }
    }

    static boolean inRange(int a, int b, int c){
		return 0 <= a && a <= 20 && 0 <= b && b <= 20 && 0 <= c && c <= 20; 
	}
}
