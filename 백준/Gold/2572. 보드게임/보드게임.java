import java.io.*;
import java.util.*;

public class Main {

	static int n, m, k;
	static char[] arr;
	static ArrayList<Edge>[] graph;
	static int[][] dp;

	static class Edge{
		int end;
		char color;

		Edge(int end, char color){
			this.end = end;
			this.color = color;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = br.readLine().replace(" ", "").toCharArray();

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new ArrayList[m+1];
		for(int i = 1; i<=m; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i<k; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);

			graph[u].add(new Edge(v, c));
			graph[v].add(new Edge(u, c));
		}

		dp = new int[m+1][n+1];
		for (int i = 1; i <= m; i++) Arrays.fill(dp[i], -1);

		System.out.println(bfs());
	}

	static int bfs(){
		Queue<int[]> q = new ArrayDeque<>(); // nownode, now score, cnt
		int max = 0;

		dp[1][0] = 0;
		q.add(new int[]{1, 0, 0});

		while(!q.isEmpty()){
			int[] now = q.poll();
			int node = now[0], score = now[1], cnt = now[2];

			if(score < dp[node][cnt]) continue;

			if(cnt == n){
				max = Math.max(max, score);
				continue;
			}

			char need = arr[cnt];

			for(Edge next : graph[node]){
				int ns = score + (next.color == need ? 10 : 0);
				int nc = cnt + 1;

				if(ns > dp[next.end][nc]){
					dp[next.end][nc] = ns;
					q.add(new int[]{next.end, ns, nc});
				}
			}
		}

		return max;
	}
}