    import java.io.*;
    import java.util.*;

    public class Main{

        static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        static Queue<int[]> q;
        static int n, m, k;
        static Fire[][] map;

        static class Fire{
            int mass, v, flag, cnt, dir;

            Fire(int mass, int v, int flag){
                this.mass = mass;
                this.v = v;
                this.flag = flag;
            }
        }

        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new Fire[n][n];
            q = new ArrayDeque<>();
            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    map[i][j] = new Fire(0, 0, 0);
                }
            }

            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine());

                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                int m = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                

                q.add(new int[]{r, c, m, s, d});
            }

            while(k-->0){

                int size = q.size();
                for(int i = 0; i<size; i++){
                    int[] now = q.poll();
                    int nx = (now[1] + dx[now[4]]*now[3] + 1100*n)%n;
                    int ny = (now[0] + dy[now[4]]*now[3] + 1100*n)%n;

                    map[ny][nx].cnt++;
                    map[ny][nx].mass += now[2];
                    map[ny][nx].v += now[3];
                    map[ny][nx].flag |= (now[4]%2 == 0 ? 2 : 1);
                    map[ny][nx].dir = now[4];

                    q.add(new int[]{nx, ny});
                }

                for(int i = 0; i<size; i++){
                    int[] now = q.poll();
                    Fire f = map[now[1]][now[0]];
                    if(f.cnt == 0) continue;
                    if(f.cnt == 1){
                        q.add(new int[]{now[1], now[0], f.mass, f.v, f.dir});
                    }
                    else{
                        int nmass = f.mass/5;
                        if(nmass > 0){
                            if(f.flag == 3){
                                q.add(new int[]{now[1], now[0], f.mass/5, f.v/f.cnt, 1});
                                q.add(new int[]{now[1], now[0], f.mass/5, f.v/f.cnt, 3});
                                q.add(new int[]{now[1], now[0], f.mass/5, f.v/f.cnt, 5});
                                q.add(new int[]{now[1], now[0], f.mass/5, f.v/f.cnt, 7});
                            }
                            else{
                                q.add(new int[]{now[1], now[0], f.mass/5, f.v/f.cnt, 0});
                                q.add(new int[]{now[1], now[0], f.mass/5, f.v/f.cnt, 2});
                                q.add(new int[]{now[1], now[0], f.mass/5, f.v/f.cnt, 4});
                                q.add(new int[]{now[1], now[0], f.mass/5, f.v/f.cnt, 6});
                            }
                        }

                    }

                    f.cnt = 0;
                    f.mass = 0;
                    f.flag = 0;
                    f.v = 0;
                }
            }

            int ans = 0;

            while(!q.isEmpty()){
                ans += q.poll()[2];
            }

            System.out.println(ans);


        }

    }