import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};
    static int n, m;
    static char[][] prison;
    static int[][][] dp;
    static int[][] prisoner;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b){
            return a[2] - b[2];
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            prison = new char[n+2][m+2];
            prisoner = new int[3][2];
            dp = new int[3][n+2][m+2];

            int idx = 1;
            for(int i = 1; i<=n; i++){
                String str = br.readLine();
                for(int j = 1; j<=m; j++){
                    prison[i][j] = str.charAt(j-1);
                    if(prison[i][j] == '$'){
                        prisoner[idx][0] = j;
                        prisoner[idx][1] = i;
                        idx++;
                        prison[i][j] = '.';
                    }
                }
            }

            for(int i = 0; i<3; i++){
                bfs(i);
            }

            int min = Integer.MAX_VALUE;

            for(int i = 0; i<n+2; i++){
                for(int j = 0; j<m+2; j++){
                    if(prison[i][j] == '*') continue;
                    int sum = 0;
                    for(int k = 0; k<3; k++){
                        sum += dp[k][i][j];
                    }
                    if(prison[i][j] == '#') sum -= 2;

                    min = Math.min(sum, min);
                }
            }
            System.out.println(min);
        }
    }

    static void bfs(int idx){
        pq.add(new int[]{prisoner[idx][0], prisoner[idx][1], 0});
        for(int i = 0; i<n+2; i++){
            Arrays.fill(dp[idx][i], Integer.MAX_VALUE);
        }

        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if(dp[idx][now[1]][now[0]] < now[2]) continue;

            for(int i = 0; i<4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx>=0 && ny>=0 && nx<m+2 && ny<n+2 && prison[ny][nx] != '*'){
                    int add = (prison[ny][nx] == '#') ? 1 : 0;
                    if(dp[idx][ny][nx] > now[2] + add){
                        dp[idx][ny][nx] = now[2] + add;
                        pq.add(new int[]{nx, ny, dp[idx][ny][nx]});
                    }
                }
            }
        }
    }


}

