import java.io.*;
import java.util.*;
//time fail
public class bj30805{
    static int n, m;
    static int[][] dp;
    static int[] a, b;
    static ArrayList<Integer> str;
    static ArrayList<Integer> ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        a = new int[n+1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i<=n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m+1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i<=m; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1][m+1];

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=m; j++){
                if(a[i] == b[j]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        str = new ArrayList<>();
        ans = new ArrayList<>();

        findLCS(m, n);

        sb.append(ans.size()).append('\n');
        for(int i = 0; i<ans.size(); i++){
            sb.append(ans.get(i)).append(' ');
        }

        System.out.println(sb);



    }

    static void findLCS(int x, int y){
        if(x == 0 || y == 0){
            compareans();
            return;
        }

        if(a[y] == b[x]){
            str.add(a[y]);
            findLCS(x-1, y-1);
            str.remove(str.size()-1);
        }
        else{
            if(dp[y][x] == dp[y-1][x]){
                findLCS(x, y-1);
            }
            if(dp[y][x] == dp[y][x-1]){
                findLCS(x-1, y);
            }
        }


    }

    static void compareans(){
        if(str.size() == 0){
            return;
        }

        ArrayList<Integer> lds = new ArrayList<>();
        int idx = str.size();
        
        while(idx >  0){
            int max = 0;
            int pos = 0;
            for(int i = 0; i<idx; i++){
                if(str.get(i) >= max){
                    max = str.get(i);
                    pos = i;
                }
            }
            lds.add(max);
            idx = pos;
        }

        if(ans.size() == 0){
            for(int i = 0; i<lds.size(); i++){
                ans.add(lds.get(i));
            }
        }
        else{
            int i = 0;
            boolean same = true;
            while(lds.size() != i && ans.size() != i){
                if(lds.get(i) > ans.get(i)){
                    ans.clear();
                    ans.addAll(lds);
                    return;
                }
                else if(lds.get(i) < ans.get(i)){
                    same = false;
                    break;
                }
                i++;
            }

            if(same){
                if(lds.size() > ans.size()){
                    ans.clear();
                    ans.addAll(lds);
                    return;
                }
            }
        }

    }
}