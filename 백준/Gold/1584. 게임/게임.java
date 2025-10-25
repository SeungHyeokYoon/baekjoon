import java.io.*;
import java.util.*;

public class Main {

	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static int n, m;
	static int[][] map = new int[501][501];

	static class Point{
		int x, y, damage;

		Point(int x, int y, int damage){
			this.x = x; this.y = y; this.damage = damage;
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		while(n-->0){
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for(int i = Math.min(y1, y2); i<=Math.max(y1, y2); i++){
				for(int j = Math.min(x1, x2); j<=Math.max(x1, x2); j++){
					map[i][j] = 1;
				}
			}
		}

		n = Integer.parseInt(br.readLine());

		while(n-->0){
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for(int i = Math.min(y1, y2); i<=Math.max(y1, y2); i++){
				for(int j = Math.min(x1, x2); j<=Math.max(x1, x2); j++){
					map[i][j] = -1;
				}
			}
		}

		System.out.println(dijkstra());

	}

	static int dijkstra(){
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>(){
			@Override
			public int compare(Point o1, Point o2) {
				return o1.damage - o2.damage;
			}
		});

		int[][] dist = new int[501][501];
		for(int i = 0; i<=500; i++){
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		pq.add(new Point(0, 0, 0));
		dist[0][0] = 0;

		while(!pq.isEmpty()){
			Point now = pq.poll();

			if(dist[now.y][now.x] < now.damage) continue;



			for(int i = 0; i<4; i++){
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if(nx>=0 && ny>=0 && nx<=500 && ny<=500 && map[ny][nx] >= 0){
					if(nx == 500 && ny == 500){
						return now.damage + map[ny][nx];
					}


					if(dist[ny][nx] > map[ny][nx] + now.damage){
						dist[ny][nx] = map[ny][nx] + now.damage;
						pq.add(new Point(nx, ny, dist[ny][nx]));
					}
				}
			}

		}

		return -1;

	}










}