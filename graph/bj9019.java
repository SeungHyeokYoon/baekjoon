import java.io.*;
import java.util.*;

public class bj9019 {

    static int n, start, end;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            bfs();
            
        }
        
        System.out.println(sb);

    }

    static void bfs(){
        Queue<node> q = new LinkedList<>();
        visited = new boolean[10000];
        q.add(new node("", start));
        visited[start] = true;

        while(!q.isEmpty()){
            node now = q.poll();
            if(now.num == end){
                sb.append(now.str).append("\n");
                return;
            }

            int d1 = now.num/1000;
            int d2 = (now.num-d1*1000)/100;
            int d3 = (now.num-d1*1000-d2*100)/10;
            int d4 = now.num%10;

            int numD = now.num * 2 % 10000;
            if(!visited[numD]){
                visited[numD] = true;
                q.add(new node(now.str+"D", numD));
            }
            
            int numS = (now.num == 0) ? 9999 : now.num-1;
            if(!visited[numS]){
                visited[numS] = true;
                q.add(new node(now.str+"S", numS));
            }

            int numL = d2*1000+d3*100+d4*10+d1;
            if(!visited[numL]){
                visited[numL] = true;
                q.add(new node(now.str+"L", numL));
            }

            int numR  = d4*1000+d1*100+d2*10+d3;
            if(!visited[numR]){
                visited[numR] = true;
                q.add(new node(now.str+"R", numR));
            }

        }

    }

    static class node{
        String str;
        int num;

        node(String str, int num){
            this.str = str;
            this.num = num;
        }
    }


}
