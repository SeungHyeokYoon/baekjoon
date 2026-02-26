    import java.io.*;
    import java.util.*;

    public class Main{

        static class point{
            int x;
            int y;

            point(int x, int y){
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o){
                point z = (point) o;
                return this.x == z.x && this.y == z.y;
            }

            @Override
            public int hashCode(){
                return Objects.hash(x+y);
            }
        }


        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            int m = Integer.parseInt(br.readLine());
            int[][] star = new int[m][2];
            
            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                star[i][0] = Integer.parseInt(st.nextToken());
                star[i][1] = Integer.parseInt(st.nextToken());
            }
            
            for(int i = m-1; i>0; i--){
                star[i][0] -= star[i-1][0];
                star[i][1] -= star[i-1][1];
            }
            
            int n = Integer.parseInt(br.readLine());
            HashSet<point> set = new HashSet<>();
            
            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                set.add(new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            for(point now : set){
                int x = now.x;
                int y = now.y;

                boolean can = true;

                for(int j = 1; j<m; j++){
                    x += star[j][0];
                    y += star[j][1];
                    if(set.contains(new point(x, y))) continue;

                    can = false;
                    break;
                }

                if(can){
                    System.out.println((now.x-star[0][0]) + " " + (now.y-star[0][1]));
                    return;
                }

            }

        }
    }