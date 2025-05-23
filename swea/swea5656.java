import java.io.*;
import java.util.*;

public class swea5656{

    static int n, w, h, min;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            int[][] bricks = new int[h][w];

            for(int i = 0; i<h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<w; j++){
                    bricks[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            collision(bricks, 0);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }

        System.out.println(sb);
    }

    static void collision(int[][] origin, int depth){
        
        if(depth == n){
            countbricks(origin);
            return;
        }

        for(int i = 0; i<w; i++){
            int[][] clonebricks = copyMap(origin);
            int y = 0;
            while(y<h && clonebricks[y][i] == 0){
                y++;
            }

            if(y != h){
                dfs(i, y,  clonebricks);
                applyGravity(clonebricks);
            }

            collision(clonebricks, depth+1);

        }
    }


    static void dfs(int x, int y, int[][] matrix){

        int num = matrix[y][x];
        matrix[y][x] = 0;

        for(int i = 1; i<num; i++){
            for(int j = 0; j<4; j++){
                int nx = x + dx[j]*i;
                int ny = y + dy[j]*i;
                if(nx>=0 && ny>=0 && nx<w && ny<h){
                    if(matrix[ny][nx] != 0){
                        dfs(nx, ny, matrix);
                    }
                }
            }
        }

    }

    static void countbricks(int[][] origin){
        
        int count = 0;
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                if(origin[i][j] != 0) count++;
            }
        }

        min = Math.min(min, count);

    }

    static int[][] copyMap(int[][] map){
        int[][] newMap = new int[h][w];
        for(int i = 0; i < h; i++){
            newMap[i] = map[i].clone();
        }
        return newMap;
    }

    static void applyGravity(int[][] map){
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i<w; i++){
            for(int j = h-1; j>=0; j--){
                if(map[j][i] > 0){
                    q.add(map[j][i]);
                    map[j][i] = 0;
                }
            }

            int idx = h-1;
            while(!q.isEmpty()){
                map[idx--][i] = q.poll();
            }
        }
    }

}