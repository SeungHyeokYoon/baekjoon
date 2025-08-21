import java.io.*;
import java.util.*;

public class Main {

	static PriorityQueue<Integer>[] graph;
	static boolean[] visited;
	static int n, m, k;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new PriorityQueue[n+1];
		visited = new boolean[n+1];
		parents = new int[n+1];

		for(int i = 1; i<=n; i++){
			graph[i] = new PriorityQueue<>();
			parents[i] = i;
		}

		for(int i = 0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());

			graph[v].add(u);
			graph[u].add(v);
		}

		st = new StringTokenizer(br.readLine());
		Queue<int[]> q = new ArrayDeque<>();
		Stack<Edge> stack = new Stack<>();
		Set<Edge> edgeSet = new HashSet<>();
		for(int i = 0; i<k; i++){
			int v = Integer.parseInt(st.nextToken());
			q.add(new int[]{v, 0});
			visited[v] = true;
		}

		while(!q.isEmpty()){
			int[] now = q.poll();

			while(!graph[now[0]].isEmpty()){
				int next = graph[now[0]].poll();
				if(!visited[next]){
					visited[next] = true;
					q.add(new int[]{next, now[1]+1});
				}

				Edge newEdge = new Edge(now[0], next, now[1]+1);
				if(edgeSet.add(newEdge)){
					stack.add(newEdge);
					if(!graph[now[0]].isEmpty() && graph[now[0]].peek() == next){
						stack.add(newEdge);
						graph[now[0]].poll();
					}
				}
			}
		}

		while(!stack.isEmpty()){
			Edge now = stack.pop();
			int start = find(now.start);
			int end = find(now.end);

			if(start == end){
				System.out.println(now.time);
				break;
			}

			parents[start] = end;
		}

	}

	static int find(int x){
		if(x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}

	static class Edge{
		int start;
		int end;
		int time;

		Edge(int start, int end, int time){
			this.start = start;
			this.end = end;
			this.time = time;
		}

		@Override
		public boolean equals(Object o){
			if(this == o) return true;
			if(o instanceof Edge){
				Edge now = (Edge) o;
				if(now.start == this.start && now.end == this.end) return true;
				if(now.start == this.end && now.end == this.start) return true;
			}
			return false;
		}

		@Override
        public int hashCode() {
            int a = Math.min(start, end);
            int b = Math.max(start, end);
            return Objects.hash(a, b);
        }

	}

}