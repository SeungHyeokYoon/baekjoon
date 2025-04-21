import java.io.*;
import java.util.*;

public class bj16946 {

    static int n, m, idx, cnt;
    static int[][] map, copymap;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static ArrayList<Integer> blank = new ArrayList<>();
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        copymap = new int[n][m];
        
        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<m; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }
        
        idx = 0;
        blank.add(0);

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(map[i][j] == 0 && copymap[i][j] == 0){
                    idx++;
                    cnt = 0;
                    dfs(i, j);
                    blank.add(cnt);
                }
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(map[i][j] == 1){

                    int ans = 1;

                    for(int k = 0; k<4; k++){
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if(nx >= 0 && ny >= 0 && nx < m && ny < n){
                            set.add(copymap[ny][nx]);
                        }
                    }

                    for(Integer in : set){
                        ans += blank.get(in);
                    }
                    set.clear();

                    sb.append(ans % 10);

                }
                else{
                    sb.append('0');
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);

        
    }

    static void dfs(int y, int x){
        copymap[y][x] = idx;
        cnt++;
        
        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < m && ny < n && map[ny][nx] == 0 && copymap[ny][nx] == 0){
                dfs(ny, nx);
            }
        }
    }
}
