import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[] coin, check;
	static boolean[][] dp;



	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		coin = new int[n];
		st = new StringTokenizer(br.readLine());
		
		int sum = 0;
		for(int i = 0; i<n; i++){
			coin[i] = Integer.parseInt(st.nextToken());
			sum += coin[i];
		}

		m = Integer.parseInt(br.readLine());
		check = new int[m];
		st = new StringTokenizer(br.readLine());

		for(int i = 0; i<m; i++){
			check[i] = Integer.parseInt(st.nextToken());
		}

		dp = new boolean[n][sum+1];
		dp[0][0] = true;
		dp[0][coin[0]] = true;

		for(int i = 1; i<n; i++){
			for(int j = 0; j<=sum; j++){
				if(dp[i-1][j]){
					dp[i][j] = true;
					dp[i][Math.abs(j-coin[i])] = true;
					dp[i][j+coin[i]] = true;
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i<m; i++){
			if(check[i] <= sum && dp[n-1][check[i]]) sb.append("Y ");
			else sb.append("N ");
		}

		System.out.println(sb);
		
	}


}