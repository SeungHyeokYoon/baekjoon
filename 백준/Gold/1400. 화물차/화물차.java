import java.io.*;
import java.util.*;

public class Main {

	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static int n, m, sx, sy, cnt;
	static char[][] map;
	static int[][] light;
	
	static class Point implements Comparable<Point>{
		int x, y, time;

		Point(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Point o){
			return this.time - o.time;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while(true){
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			if(n == 0 && m == 0) break;

			map = new char[n][m];
			cnt = 0;

			for(int i = 0; i<n; i++){
				String str = br.readLine();
				for(int j = 0; j<m; j++){
					map[i][j] = str.charAt(j);
					if(map[i][j] == 'A'){
						sx = j;
						sy = i;
					}
					else if(map[i][j] != '#' && map[i][j] != '.' && map[i][j] != 'B'){
						cnt++;
					}
				}
			}

			if(cnt != 0){
				light = new int[cnt][3];

				for(int i = 0; i<cnt; i++){
					st = new StringTokenizer(br.readLine());

					int num = Integer.parseInt(st.nextToken());
					String order = st.nextToken();
					int hor = Integer.parseInt(st.nextToken());
					int ver = Integer.parseInt(st.nextToken());

					light[i][0] = order.equals("-") ? 0 : 1;
					light[i][1] = hor;
					light[i][2] = ver;
				}
			}

			int ans = dijkstra();

			sb.append(ans != -1 ? ans : "impossible").append("\n");

			br.readLine();

		}

		System.out.println(sb);


	}

	static int dijkstra(){
		int[][] dist = new int[n][m];
		for(int i = 0; i<n; i++){
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		PriorityQueue<Point> pq = new PriorityQueue<>();

		pq.add(new Point(sx, sy, 0));

		while(!pq.isEmpty()){
			Point now = pq.poll();

			if(dist[now.y][now.x] < now.time) continue;

			for(int i = 0; i<4; i++){
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if(nx>=0 && ny>=0 && nx<m && ny<n && map[ny][nx] != '.'){
					if(map[ny][nx] == 'B') return now.time + 1;

					if(map[ny][nx] == '#' || map[ny][nx] == 'A'){
						if(dist[ny][nx] > now.time + 1){
							dist[ny][nx] = now.time + 1;
							pq.add(new Point(nx, ny, now.time + 1));
						}
					}
					else{ // this is number
						int traffic = map[ny][nx] - '0';

						if(i >= 2){ // hor 1
							int calval = timeCal(traffic, now.time, 1);
							if(dist[ny][nx] > now.time + 1 + calval){
								dist[ny][nx] = now.time + 1 + calval;
								pq.add(new Point(nx, ny, now.time + 1 + calval));
							}
						}
						else{ // ver 2
							int calval = timeCal(traffic, now.time, 2);
							if(dist[ny][nx] > now.time + 1 + calval){
								dist[ny][nx] = now.time + 1 + calval;
								pq.add(new Point(nx, ny, now.time + 1 + calval));
							}
						}
					}
				}
			}
		}


		return -1;
	}


	static int timeCal(int trafficNum, int nowTime, int dir){
		nowTime %= (light[trafficNum][1] + light[trafficNum][2]);

		int first = light[trafficNum][0] == 0 ? 1 : 2;
		int next = first == 1 ? 2 : 1;


		if(nowTime < light[trafficNum][first]){
			if(dir == first){
				return 0;
			}
			else{
				return light[trafficNum][first] - nowTime;
			}
		}
		else{
			if(dir == first){
				return light[trafficNum][1] + light[trafficNum][2] - nowTime;
			}
			else{
				return 0;
			}
		}
	}





}