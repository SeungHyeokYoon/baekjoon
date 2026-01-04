import java.io.*;
import java.util.*;

public class Main {

	static final int[] dx = {0, 0, 0, 0, 1, -1};
	static final int[] dy = {0, 0, 1, -1, 0, 0};
	static final int[] dz = {1, -1, 0, 0, 0, 0};

	static class tomato{
		int x, y, z;

		tomato(int x, int y, int z){
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		int[][][] box = new int[h][n][m];
		int yet = 0;
		Queue<tomato> q = new ArrayDeque<>();

		for(int i = 0; i<h; i++){
			for(int j = 0; j<n; j++){
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k<m; k++){
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if(box[i][j][k] == 0) yet++;
					else if(box[i][j][k] == 1) q.add(new tomato(k, j, i));
				}
			}
		}


		int day = -1;

		while(!q.isEmpty()){
			day++;
			int size = q.size();

			for(int i = 0; i<size; i++){
				tomato now = q.poll();

				for(int j = 0; j<6; j++){
					int nx = now.x + dx[j];
					int ny = now.y + dy[j];
					int nz = now.z + dz[j];

					if(nx>=0 && nx<m && ny>=0 && ny<n && nz>=0 && nz<h){
						if(box[nz][ny][nx] == 0){
							q.add(new tomato(nx, ny, nz));
							box[nz][ny][nx] = 1;
							yet--;
						}
					}
				}				
			}
		}

		if(yet == 0){
			System.out.println(day);
		}
		else{
			System.out.println(-1);
		}
	}
}