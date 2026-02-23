import java.io.*;
import java.util.*;

public class Main{

    static class building{
        int idx, height;

        building(int idx, int height){
            this.idx = idx;
            this.height = height;
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Stack<building> s = new Stack<>();



        st = new StringTokenizer(br.readLine());

        boolean can = false;

        for(int i = 0; i<n; i++){
            int now = Integer.parseInt(st.nextToken());

            if(s.isEmpty()){
                sb.append("0 ");
                s.add(new building(0, now));
            }
            else if(s.peek().height > now){
                sb.append(s.peek().idx + 1).append(" ");
                s.add(new building(i, now));
                can = true;
            }
            else if(s.peek().height <= now){
                while(!s.isEmpty() && s.peek().height <= now){
                    s.pop();
                }

                if(s.isEmpty()) sb.append("0 ");
                else{
                    sb.append(s.peek().idx + 1).append(" ");
                    can = true;
                }

                s.add(new building(i, now));
            }

        }

        if(can){
            System.out.println(sb);
        }
        else{
            System.out.println(0);
        }

    }


}