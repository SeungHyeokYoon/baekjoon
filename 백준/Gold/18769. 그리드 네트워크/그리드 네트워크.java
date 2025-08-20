import java.io.*;
import java.util.*;

public class Main {

	static int[] parents;
	static int r, c;
	static PriorityQueue<Edge> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while(T-->0){
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			q = new PriorityQueue<>();
			parents = new int[r*c];

			for(int i = 0; i<r; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<c-1; j++){
					int idx = i*c+j;
					q.add(new Edge(idx, idx+1, Integer.parseInt(st.nextToken())));
				}
			}

			for(int i = 0; i<r-1; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<c; j++){
					int idx = i*c+j;
					q.add(new Edge(idx, idx+c, Integer.parseInt(st.nextToken())));
				}
			}

			for(int i = 0; i<r*c; i++){
				parents[i] = i;
			}

			int cnt = r*c-1;
			int sum = 0;
			while(cnt>0){
				Edge now = q.poll();
				int start = find(now.start);
				int end = find(now.end);

				if(start != end){
					parents[end] = start;
					cnt--;
					sum += now.weight;
				}
			}

			sb.append(sum).append("\n");

		}

		System.out.println(sb);
	}

	static int find(int x){
		if(x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}

	static class Edge implements Comparable<Edge>{
		int start, end, weight;

		Edge(int start, int end, int weight){
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

		
	}


}