import java.io.*;
import java.util.*;

public class swea1240 {

    static char[][] number = {
        {'0','0','0','1','1','0','1'}, 
        {'0','0','1','1','0','0','1'}, 
        {'0','0','1','0','0','1','1'}, 
        {'0','1','1','1','1','0','1'}, 
        {'0','1','0','0','0','1','1'}, 
        {'0','1','1','0','0','0','1'}, 
        {'0','1','0','1','1','1','1'}, 
        {'0','1','1','1','0','1','1'}, 
        {'0','1','1','0','1','1','1'}, 
        {'0','0','0','1','0','1','1'}
    };

    static int n, m;
    static char[][] code;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=t; tc++){

            st = new StringTokenizer(br.readLine());

            sb.append("#").append(tc).append(" ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
    
            code = new char[n][m];
    
            int lastx = 0;
            int lasty = 0;
    
            for(int i = 0; i<n; i++){
                String str = br.readLine();
                for(int j = 0; j<m; j++){
                    code[i][j] = str.charAt(j);
                    if(code[i][j] == '1'){
                        lastx = j;
                        lasty = i;
                    }
                }
            }
    
            int[] nums = new int[8];
            int numsidx = 0;
            int idx = lastx-55;
            while(idx<=lastx){
    
                int num = -1;
                Loop1:
                for(int i = 0; i<10; i++){
                    for(int j = 0; j<7; j++){
                        if(code[lasty][idx+j] != number[i][j]){
                            break;
                        }
    
                        if(j == 6){
                            num = i;
                            break Loop1;
                        }
                    }
                }
                nums[numsidx++] = num;
                idx += 7;
            }
            
            int odd = nums[0] + nums[2] + nums[4] + nums[6];
            int even = nums[1] + nums[3] + nums[5] + nums[7];
            if((odd*3+even) % 10 == 0){
                sb.append(odd+even).append("\n");
            }
            else{
                sb.append(0).append("\n");
            }

        }

        System.out.println(sb);

    }

}
