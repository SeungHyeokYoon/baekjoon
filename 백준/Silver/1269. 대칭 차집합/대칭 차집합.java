    import java.io.*;
    import java.util.*;

    public class Main{
        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            HashSet<Integer> set = new HashSet<>();

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<n; i++){
                int a = Integer.parseInt(st.nextToken());
                set.add(a);
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<m; i++){
                int a = Integer.parseInt(st.nextToken());
                set.add(a);
            }

            int tot = n + m;
            int x = tot - set.size();

            System.out.println(tot - 2*x);
        }
    }