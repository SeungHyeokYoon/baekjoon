import java.io.*;
import java.util.*;

public class swea5653{

    static int k, n, m;
    static cell[][] board;
    static Queue<celltemp> q = new LinkedList<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            board = new cell[n+k][m+k];

            for(int i = k/2; i<n+k/2; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = k/2; j<m+k/2; j++){
                    int now = Integer.parseInt(st.nextToken());
                    if(now != 0){
                        board[i][j] = new cell(now);
                    }
                }
            }

            for(int ktemp = 0; ktemp<k; ktemp++){
                timeflow();
            }
            
            int ans = 0;
            for(int i = 0; i<n+k; i++){
                for(int j = 0; j<m+k; j++){
                    if(board[i][j] == null) continue;
                    if(board[i][j].activate == 1 || board[i][j].activate == 2) ans++;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");

        }

        System.out.println(sb);
    }

    static void timeflow(){
        for(int i = 0; i<n+k; i++){
            for(int j = 0; j<m+k; j++){
                if(board[i][j] == null || board[i][j].activate == 3) continue;

                board[i][j].count++;

                if(board[i][j].activate == 1 && board[i][j].count == board[i][j].init){
                    board[i][j].activate = 2;
                    board[i][j].count = 0;
                }
                else if(board[i][j].activate == 2 && board[i][j].count == 1){
                    expansion(j, i, board[i][j].init);
                }

                if(board[i][j].activate == 2 && board[i][j].count == board[i][j].init){
                    board[i][j].activate = 3;
                }

            }
        }

        while(!q.isEmpty()){
            celltemp now = q.poll();
            if(board[now.y][now.x] == null){
                board[now.y][now.x] = new cell(now.init);
            }
            else{
                if(board[now.y][now.x].init < now.init){
                    board[now.y][now.x].init = now.init;
                }
            }
        }
    }

    static void expansion(int x, int y, int init){
        for(int i = 0; i<4; i++){
            if(board[y+dy[i]][x+dx[i]] == null){
                q.add(new celltemp(x+dx[i], y+dy[i], init));
            }
        }

        
    }

    static class celltemp{
        int x;
        int y;
        int init;

        celltemp(int x, int y, int init){
            this.x = x;
            this.y = y;
            this.init = init;
        }
    }


    static class cell{
        int activate;
        int init;
        int count;

        cell(int init){
            this.activate = 1;
            this.init = init;
            this.count = 0;
        }
    }
}