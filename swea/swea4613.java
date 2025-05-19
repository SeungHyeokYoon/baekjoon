import java.io.*;
import java.util.StringTokenizer;

public class swea4613 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] colorcount = new int[n][3];

            for(int i = 0; i<n; i++){
                String str = br.readLine();
                for(int j = 0; j<m; j++){
                    char now = str.charAt(j);
                    if(now == 'W') colorcount[i][0]++;
                    else if(now == 'B') colorcount[i][1]++;
                    else colorcount[i][2]++;
                }
            }

            int base = colorcount[0][1] + colorcount[0][2] + colorcount[n-1][0] + colorcount[n-1][1];
            int min = Integer.MAX_VALUE;
            n -= 2;

            for(int i = 0; i<n; i++){
                for(int j = 1; j<=n-i; j++){
                    int sum = 0;
                    int idx = 1;
                    for(int k = 0; k<i; k++){
                        sum += (colorcount[idx][1] + colorcount[idx][2]);
                        idx++;
                    }
                    for(int k = 0; k<j; k++){
                        sum += (colorcount[idx][0] + colorcount[idx][2]);
                        idx++;
                    }
                    for(int k = 0; k<n-i-j; k++){
                        sum += (colorcount[idx][0] + colorcount[idx][1]);
                        idx++;
                    }

                    min = Math.min(min, sum);
                }
            }

            sb.append("#").append(tc).append(" ").append(min + base).append("\n");
        }
        System.out.println(sb);
    }
}
