import java.io.*;
import java.util.*;

public class bj16236 {

    static int n, nowx, nowy, cnt, size, time;
    static int[][] ocean;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        ocean = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                ocean[i][j] = Integer.parseInt(st.nextToken());
                if(ocean[i][j] == 9){
                    nowx = j;
                    nowy = i;
                    ocean[i][j] = 0;
                }
            }
        }

        size = 2;
        cnt = 0;
        time = 0;

        while(true){
            if(!BFS(nowy, nowx)){
                break;
            }
        }

        System.out.println(time);

    }

    static boolean BFS(int y, int x){
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];

        q.add(new Node(x, y, 0));
        visited[y][x] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(ocean[cur.y][cur.x] < size && ocean[cur.y][cur.x] != 0){
                ocean[cur.y][cur.x] = 0;
                time += cur.depth;
                cnt++;
                if(cnt == size){
                    size++;
                    cnt = 0;
                }
                nowx = cur.x;
                nowy = cur.y;
                return true;
            }
            else if(ocean[cur.y][cur.x] == size || ocean[cur.y][cur.x] == 0){
                visited[cur.y][cur.x] = true;
                for(int i = 0; i<4; i++){
                    int newx = cur.x + dx[i];
                    int newy = cur.y + dy[i];
                    if(newx >= 0 && newy >= 0 && newx < n && newy < n && !visited[newy][newx] && ocean[newy][newx] <= size){
                        q.add(new Node(newx, newy, cur.depth+1));
                        visited[newy][newx] = true;
                    }
                }

            } 
    }

        return false;


        
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int depth;

        Node(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth =depth;
        }

        @Override
        public int compareTo(Node n){
            if(this.depth != n.depth){
                return this.depth - n.depth;
            }
            else if(this.y != n.y){
                return this.y - n.y;
            }
            else{
                return this.x - n.x;
            }
        }
    }
}
