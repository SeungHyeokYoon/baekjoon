import java.io.*;
import java.util.*;

public class swea4130 {

    static int[][] magnet;
    static int m1base, m2base, m3base, m4base, m1con2, m2con1, m2con3, m3con2, m3con4, m4con3;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc  = 1; tc<=T; tc++){

            int k = Integer.parseInt(br.readLine());
            magnet = new int[4][8];

            for(int i = 0; i<4; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<8; j++){
                    magnet[i][j]  = Integer.parseInt(st.nextToken());
                }
            }

            m1base = m2base = m3base = m4base = 0;
            m1con2 = m2con3 = m3con4 = 2;
            m2con1 = m3con2 = m4con3 = 6;


            while(k-->0){
                st = new StringTokenizer(br.readLine());
                rot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            int sum = 0;
            sum = (magnet[0][m1base] == 1)? sum+1 : sum;
            sum = (magnet[1][m2base] == 1)? sum+2 : sum;
            sum = (magnet[2][m3base] == 1)? sum+4 : sum;
            sum = (magnet[3][m4base] == 1)? sum+8 : sum;
            
            sb.append("#").append(tc).append(" ").append(sum).append("\n");

        }
        System.out.println(sb);

            
    }

    static void rot(int magnetnum, int dir){

        int[] rotate = new int[4];

        if(magnetnum == 1){
            rotate[0] = dir;
            if(magnet[0][m1con2] != magnet[1][m2con1]){
                rotate[1] = -dir;
                if(magnet[1][m2con3] != magnet[2][m3con2]){
                    rotate[2] = dir;
                    if(magnet[2][m3con4] != magnet[3][m4con3]){
                        rotate[3] = -dir;
                    }
                }

            }
        }
        else if(magnetnum == 2){
            rotate[1] = dir;
            if(magnet[0][m1con2] != magnet[1][m2con1]){
                rotate[0] = -dir;
            }
            if(magnet[1][m2con3] != magnet[2][m3con2]){
                rotate[2] = -dir;
                if(magnet[2][m3con4] != magnet[3][m4con3]){
                    rotate[3] = dir;
                }
            }
        }
        else if(magnetnum == 3){
            rotate[2] = dir;
            if(magnet[2][m3con4] != magnet[3][m4con3]){
                rotate[3] = -dir;
            }
            if(magnet[1][m2con3] != magnet[2][m3con2]){
                rotate[1] = -dir;
                if(magnet[0][m1con2] != magnet[1][m2con1]){
                    rotate[0] = dir;
                }
            }
        }
        else{
            rotate[3] = dir;
            if(magnet[2][m3con4] != magnet[3][m4con3]){
                rotate[2] = -dir;
                if(magnet[1][m2con3] != magnet[2][m3con2]){
                    rotate[1] = dir;
                    if(magnet[0][m1con2] != magnet[1][m2con1]){
                        rotate[0] = -dir;
                    }
                }

            }
        }

        m1base  = (m1base-rotate[0]+8)%8;
        m2base  = (m2base-rotate[1]+8)%8;
        m3base  = (m3base-rotate[2]+8)%8;
        m4base  = (m4base-rotate[3]+8)%8;

        m1con2 = (m1con2-rotate[0]+8)%8;
        m2con3 = (m2con3-rotate[1]+8)%8;
        m2con1 = (m2con1-rotate[1]+8)%8;
        m3con4 = (m3con4-rotate[2]+8)%8;
        m3con2 = (m3con2-rotate[2]+8)%8;
        m4con3 = (m4con3-rotate[3]+8)%8;
        
    }

}
