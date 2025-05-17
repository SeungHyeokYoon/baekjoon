import java.io.*;
import java.util.*;

public class swea1767 {

    static int n, connected, num;
    static int[][] board;
    static Stack<chip> stack;
    static int[] dx = {0 ,0 ,1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().strip());

        for(int tc = 1; tc<=T; tc++){
            n = Integer.parseInt(br.readLine().strip());

            board = new int[n][n];
            stack = new Stack<>();

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine().strip());
                for(int j = 0; j<n; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if(board[i][j] == 1){
                        if(i != 0 && j != 0 && i != n-1 && j != n-1){
                            stack.push(new chip(j, i));
                        }
                    }
                }
            }

            connected = 0;
            num = Integer.MAX_VALUE;

            connecting(0, 0);

            sb.append("#").append(tc).append(" ").append(num).append("\n");





        }   

        System.out.println(sb);

    }


    static void connecting(int cellcount, int linecount){
        if(stack.isEmpty()){
            if(connected < cellcount){
                connected = cellcount;
                num = linecount;
            }
            else if(connected == cellcount){
                num = Math.min(num, linecount);
            }
            return;
        }

        chip now = stack.pop();
        
        for(int i = 0; i<4; i++){
            int nowx = now.x;
            int nowy = now.y;
            boolean can = true;
            
            while(true){
                nowx += dx[i];
                nowy += dy[i];

                if(nowx>=0 && nowy>=0 && nowx<n && nowy<n){
                    if(board[nowy][nowx] != 0){
                        can = false;
                        break;
                    }
                }
                else{
                    break;
                }
            }

            int newlinecount = 0;

            if(can){
                while(true){
                    nowx -= dx[i];
                    nowy -= dy[i];
                    if(board[nowy][nowx] != 0){
                        break;
                    }

                    board[nowy][nowx] = 2;
                    newlinecount++;
                }
            }

            connecting(can?cellcount+1:cellcount, linecount+newlinecount);

            if(can){
                while(true){
                    nowx += dx[i];
                    nowy += dy[i];
                    if(nowx>=0 && nowy>=0 && nowx<n && nowy<n){
                        board[nowy][nowx] = 0;
                    }
                    else{
                        break;
                    }
                }
            }
            
        }

        stack.push(now);
    }

    static class chip{
        int x;
        int y;

        chip(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
