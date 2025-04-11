import java.io.*;

public class bj9252 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] dp = new int[str1.length()+1][str2.length()+1];

        for(int i = 1; i<=str1.length(); i++){
            for(int j = 1; j<=str2.length(); j++){
                dp[i][j] = str1.charAt(i-1) == str2.charAt(j-1) ? dp[i-1][j-1]+1 : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        int cnt = dp[str1.length()][str2.length()];

        if(cnt == 0){
            System.out.println(0);
            return;
        }

        System.out.println(cnt);

        int y = str1.length();
        int x = str2.length();

        while(cnt > 0){
            if(str1.charAt(y-1) == str2.charAt(x-1)){
                sb.append(str1.charAt(y-1));
                x--;
                y--;
                cnt--;
            }
            else if(dp[y-1][x] >= dp[y][x-1]){
                y--;
            }
            else{
                x--;
            }
        }

        System.out.println(sb.reverse());
    }
}
