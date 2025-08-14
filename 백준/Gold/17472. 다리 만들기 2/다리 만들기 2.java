import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[][] map;
	static PriorityQueue<Bridge> connections;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static Queue<Node> q;
	static int[] parents;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		connections = new PriorityQueue<>();
		q = new ArrayDeque<>();

		for(int i = 0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<m; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int idx = 2;
		for(int i = 0; i<n; i++){
			for(int j = 0; j<m; j++){
				if(map[i][j] == 1){
					dfs(j, i, idx);
					idx++;
				}
			}
		}

		parents = new int[idx];
		for(int i = 0; i<idx; i++){
			parents[i] = i;
		}

		while(!q.isEmpty()){
			Node now = q.poll();

			int x = now.x + dx[now.dir];
			int y = now.y + dy[now.dir];

			int length = 0;

			while(x>=0 && y>=0 && x<m && y<n){
				int nowNum = map[y][x];
				if(nowNum == now.start){
					break;
				}
				else if(nowNum == 0){
					length++;
					x += dx[now.dir];
					y += dy[now.dir];
				}
				else{
					if(length > 1){
						connections.add(new Bridge(now.start, nowNum, length));
					}
					break;
				}
			}
		}

		int ans = 0;

		int cnt = idx - 3;

		while(!connections.isEmpty()){
			Bridge now = connections.poll();

			int start = find(now.start);
			int end = find(now.end);

			if(start != end){
				ans += now.weight;
				cnt--;
				union(start, end);

				if(cnt == 0){
					break;
				}
			}
		}

		if(cnt == 0){
			System.out.println(ans);
		}
		else{
			System.out.println(-1);
		}

	}

	static int find(int x){
		if(x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}

	static void union(int x, int y){
		x = find(x);
		y = find(y);
		parents[y] = x;
	}

	static void dfs(int x, int y, int idx){
		map[y][x] = idx;

		for(int i = 0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx>=0 && ny>=0 && nx<m && ny<n){
				if(map[ny][nx] == 1){
					dfs(nx, ny, idx);
				}
				else if(map[ny][nx] == 0){
					q.add(new Node(x, y, i, idx));
				}
			}
		}
	}

	static class Node{
		int x, y, dir, start;

		Node(int x, int y, int dir, int start){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.start = start;
		}
	}

	static class Bridge implements Comparable<Bridge>{
		int start;
		int end;
		int weight;

		Bridge(int start, int end, int weight){
			this.start = start;
			this.end  = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.weight - o.weight;
		}
	}


}