import java.io.*;
import java.util.*;

public class Main {

	static boolean[][] board = new boolean[101][101];
	static final int[] dx = {1, 0, -1, 0};
	static final int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		while(n-->0){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());

			draw(x, y, dir, gen);
		}

		int cnt = 0;

		for(int i = 0; i<100; i++){
			for(int j = 0; j<100; j++){
				if(board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1]) cnt++;
			}
		}

		System.out.println(cnt);

	}

	static void draw(int x, int y, int dir, int gen){

		ArrayList<Integer> arr = new ArrayList<>();

		int idx = 0;
		board[y][x] = true;
		arr.add(dir);

		for(int i = 0; i<=gen; i++){

			int size = arr.size();

			for(; idx<size; idx++){
				x += dx[arr.get(idx)];
				y += dy[arr.get(idx)];
				board[y][x] = true;
			}

			if(i != gen){
				for(int j = size-1; j>=0; j--){
					arr.add((arr.get(j)+1)%4);
				}
			}
		}

	}
}