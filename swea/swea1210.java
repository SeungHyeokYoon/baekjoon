import java.io.*;
import java.util.*;

public class swea1210{

    static int[][] ladder;
    static int nowx, nowy;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i<=10; i++){
            String str = br.readLine();
            ladder = new int[100][100];

            for(int j = 0; j<100; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k<100; k++){
                    ladder[99-j][k] = Integer.parseInt(st.nextToken());
                }
            }

            for(int j = 0; j<100; j++){
                if(ladder[0][j] == 2){
                    nowx = j;
                }
            }

            nowy = 0;
            while(nowy < 100){
                if(nowx-1 >= 0 && ladder[nowy][nowx-1] == 1){
                    nowx--;
                }
                else if(nowx+1 < 100 && ladder[nowy][nowx+1] == 1){
                    nowx++;
                }
                else{
                    nowy++;
                }

            }

            System.out.println("#" + i + " " + nowx);


        }
    }


    
}