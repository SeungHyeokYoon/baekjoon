    import java.io.*;
    import java.util.*;

    public class Main{

        static final int[] dx = {1, 0, -1, 0};
        static final int[] dy = {0, -1, 0, 1};
        static final double[] value = {0.01, 0.01, 0.07, 0.07, 0.02, 0.02, 0.1, 0.1, 0.05};
        static final int[][][] db = {
            {
                {-1, -1},
                {-1, 1},
                {0, -1},
                {0, 1},
                {0, -2},
                {0, 2},
                {1, -1},
                {1, 1},
                {2, 0},
                {1, 0}
            },
            {
                {-1, 1},
                {1, 1},
                {-1, 0},
                {1, 0},
                {-2, 0},
                {2, 0},
                {-1, -1},
                {1, -1},
                {0, -2},
                {0, -1}
            },
            {
                {1, 1},
                {1, -1},
                {0, 1},
                {0, -1},
                {0, 2},
                {0, -2},
                {-1, 1},
                {-1, -1},
                {-2, 0},
                {-1, 0}
            },
            {
                {-1, -1},
                {1, -1},
                {-1, 0},
                {1, 0},
                {-2, 0},
                {2, 0},
                {-1, 1},
                {1, 1},
                {0, 2},
                {0, 1}
            }
        };

        static int n, x, y, ans, idx = 2;
        static int[][] map;

        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            x = y = n/2;

            Loop:
            while(true){
                for(int i = 0; i<idx/2; i++){
                    x += dx[idx%4];
                    y += dy[idx%4];

                    Wind(x, y, idx%4);
                    if(x == 0 && y == 0) break Loop;
                }

                idx++;
            }

            System.out.println(ans);

        }


        static void Wind(int x, int y, int dir){
            int origin = map[y][x];
            int remain = origin;

            for(int i = 0; i<9; i++){
                int nx = x + db[dir][i][0];
                int ny = y + db[dir][i][1];
                int val = (int)(origin*value[i]);

                if(nx>=0 && ny>=0 && nx<n && ny<n){
                    map[ny][nx] += val;
                }
                else{
                    ans += val;
                }

                remain -= val;
            }

            int nx = x + db[dir][9][0];
            int ny = y + db[dir][9][1];

            if(nx>=0 && ny>=0 && nx<n && ny<n){
                map[ny][nx] += remain;
            }
            else{
                ans += remain;
            }

            map[y][x] = 0;

        }

    }