    import java.io.*;
    import java.util.*;

    public class Main{

        static final int[] dx = {0, 0, 1, -1};
        static final int[] dy = {1, -1, 0, 0};
        static int n, q, len, sum;
        static int[][][] map;
        static boolean[][]temp;

        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            len = 1 << n;

            map = new int[2][len][len];
            temp = new boolean[len][len];

            for(int i = 0; i<len; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<len; j++){
                    map[0][i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while(q-->0){
                int l = Integer.parseInt(st.nextToken());
                Storm(l, idx);
                //this time, idx is old and idx+1 is new
                idx = (idx+1)&1;
                Melt(idx);
            }


            boolean[][] visited = new boolean[len][len];
            int max = 0;
            for(int i = 0; i<len; i++){
                for(int j = 0; j<len; j++){
                    if(!visited[i][j] && map[idx][i][j] != 0){
                        max = Math.max(max, BFS(idx, j, i, visited));
                    }
                }
            }

            System.out.println(sum);
            System.out.println(max);


        }


        static void Storm(int l, int idx){

            int size = 1 << l;
            
            for(int i = 0; i<len; i+=size){
                for(int j = 0; j<len; j+=size){
                    for(int y = 0; y<size; y++){
                        for(int x = 0; x<size; x++){
                            map[(idx+1)&1][i+x][j+size-1-y] = map[idx][i+y][j+x];
                        }
                    }
                }
            }
        }

        static void Melt(int idx){
            for(int i = 0; i<len; i++){
                for(int j = 0; j<len; j++){
                    if(map[idx][i][j] == 0) continue;

                    int cnt = 0;

                    for(int k = 0; k<4; k++){
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        
                        if(nx>=0 && ny>=0 && nx<len && ny<len){
                            if(map[idx][ny][nx] > 0) cnt++;
                        }
                    }

                    if(cnt <= 2) temp[i][j] = true;
                }
            }

            for(int i = 0; i<len; i++){
                for(int j = 0; j<len; j++){
                    if(temp[i][j]){
                        map[idx][i][j]--;
                        temp[i][j] = false;
                    }
                }
            }
        }


        static int BFS(int idx, int x, int y, boolean[][] visited){
            Queue<int[]> q = new ArrayDeque<>();

            int value = 0;
            q.add(new int[]{x, y});
            visited[y][x] = true;

            while(!q.isEmpty()){
                int[] now = q.poll();
                sum += map[idx][now[1]][now[0]];
                value++;

                for(int i = 0; i<4; i++){
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];

                    if(nx>=0 && ny>=0 && nx<len && ny<len){
                        if(!visited[ny][nx] && map[idx][ny][nx] != 0){
                            q.add(new int[]{nx, ny});
                            visited[ny][nx] = true;
                        }
                    }
                }
            }

            return value;
        }

    }