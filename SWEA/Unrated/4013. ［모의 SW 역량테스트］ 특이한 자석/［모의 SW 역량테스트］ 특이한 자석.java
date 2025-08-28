import java.io.*;
import java.util.*;
 
public class Solution {

    static int[][] nuts = new int[4][8];
    static int k;
    static int[][] idx = new int[4][4];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            k = Integer.parseInt(br.readLine());

            for(int i = 0; i<4; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<8; j++){
                    nuts[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            idx[0][1] = idx[1][2] = idx[2][3] = 2;
            idx[1][0] = idx[2][1] = idx[3][2] = 6;

            for(int i = 0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());

                rotate(num, dir, 0);
            }


            int result = 0;
            if(nuts[0][(idx[0][1]+6)%8] == 1) result += 1;
            if(nuts[1][(idx[1][2]+6)%8] == 1) result += 2;
            if(nuts[2][(idx[2][3]+6)%8] == 1) result += 4;
            if(nuts[3][(idx[3][2]+2)%8] == 1) result += 8;

            
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.print(sb);
    }

    static void rotate(int num, int dir, int from){


        if(from == 0){
            if(num != 0 && nuts[num][idx[num][num-1]] != nuts[num-1][idx[num-1][num]]){
                rotate(num-1, dir*-1, -1);
            }
            if(num != 3 && nuts[num][idx[num][num+1]] != nuts[num+1][idx[num+1][num]]){
                rotate(num+1, dir*-1, 1);
            }
        }
        else if(from == -1){
            if(num != 0 && nuts[num][idx[num][num-1]] != nuts[num-1][idx[num-1][num]]){
                rotate(num-1, dir*-1, -1);
            }
        }
        else{
            if(num != 3 && nuts[num][idx[num][num+1]] != nuts[num+1][idx[num+1][num]]){
                rotate(num+1, dir*-1, 1);
            }
        }

        if(num != 3) idx[num][num+1] = (idx[num][num+1] - dir + 8) % 8;
        if(num != 0) idx[num][num-1] = (idx[num][num-1] - dir + 8) % 8;
    }

}