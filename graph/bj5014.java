import java.io.*;
import java.util.*;

public class bj5014 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[F+1];

        Queue<floor> q = new LinkedList<>();

        q.add(new floor(S, 0));
        visited[S] = true;

        while(!q.isEmpty()){
            floor now = q.poll();
            if(now.num == G){
                System.out.println(now.depth);
                return;
            }

            if(now.num-D>=1 && !visited[now.num-D]){
                q.add(new floor(now.num-D, now.depth+1));
                visited[now.num-D] = true;
            }

            if(now.num+U<=F && !visited[now.num+U]){
                q.add(new floor(now.num+U, now.depth+1));
                visited[now.num+U] = true;
            }
        }

        System.out.println("use the stairs");
    }

    static class floor{
        int num;
        int depth;

        floor(int num, int depth){
            this.num = num;
            this.depth = depth;
        }
    }
}
