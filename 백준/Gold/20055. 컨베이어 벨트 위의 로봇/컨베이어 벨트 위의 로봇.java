import java.io.*;
import java.util.*;

public class Main{

    static int n, k, start, end, days;
    static Belt[] conv;
    static Queue<Integer> q;

    static class Belt{
        int dur;
        boolean robo;

        Belt(int dur){
            this.dur = dur;
            robo = false;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        conv = new Belt[2*n];
        q = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<2*n; i++){
            conv[i] = new Belt(Integer.parseInt(st.nextToken()));
        }

        start = 0;
        end = n-1;

        while(true){
            days++;

            start = (start+2*n-1)%(2*n);
            end = (end+2*n-1)%(2*n);

            int size = q.size();

            for(int i = 0; i<size; i++){
                int now = q.poll();

                if(now == end){
                    conv[end].robo = false;
                    continue;
                }

                if(conv[(now+1)%(2*n)].dur > 0 && !conv[(now+1)%(2*n)].robo){

                    conv[(now+1)%(2*n)].robo = true;
                    conv[(now+1)%(2*n)].dur--;
                    conv[now].robo = false;
                    
                    now = (now+1)%(2*n);
                }

                if(now == end){
                    conv[end].robo = false;
                    continue;
                }
                q.add(now);
            }

            if(conv[start].dur > 0 && !conv[start].robo){
                conv[start].dur--;
                conv[start].robo = true;
                q.add(start);
            }

            int cnt = 0;

            for(int i = 0; i<2*n; i++){
                if(conv[i].dur == 0) cnt++;
            }

            if(cnt >= k){
                System.out.println(days);
                return;
            }
            
        }


    }


}