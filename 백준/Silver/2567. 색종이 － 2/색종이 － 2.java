import java.io.*;
import java.util.*;

public class Main {

	static int[][] paper;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());

		paper = new int[101][101];

		for(int i = 0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for(int j = y; j<y+10; j++){
				for(int k = x; k<x+10; k++){
					paper[j][k] = 1;
				}
			}
		}

		int ans = 0;

		for(int i = 0; i<101; i++){
			for(int j = 0; j<101; j++){
				if(paper[i][j] == 1){
					ans += check(j, i);
				}
			}
		}

		System.out.println(ans);


	}

	static int check(int x, int y){
		int sum = 0;
		for(int i = 0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<101 && ny>=0 && ny<101){
				if(paper[ny][nx] == 0) sum++;
			}
		}

		return sum;
	}
}