import java.io.*;
import java.util.*;

public class Main{

    static int d, p, maxval, max;
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        d = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        map = new HashMap<>();

        maxval = (int)Math.pow(10, d) - 1;
        max = -1;

        map.put(1, 0);

        backTrack(1, 0);

        System.out.println(max);
    }

    static void backTrack(int val, int depth){

        if(depth == p){
            max = Math.max(val, max);
            return;
        }

        for(int i = 2; i<10; i++){
            int newval = val * i;

            if(newval > maxval) break;

            if(!map.containsKey(newval)){
                map.put(newval, 1<<(depth+1));
            }
            else{
                int value = map.get(newval);
                if((value & 1<<(depth+1)) != 0){
                    continue;
                }
                else{
                    value |= 1<<(depth+1);
                    map.put(newval, value);
                }
            }

            backTrack(newval, depth+1);
        }
    }

}