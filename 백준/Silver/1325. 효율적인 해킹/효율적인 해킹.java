import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static ArrayList<Integer>[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new ArrayList[n+1];
		for(int i = 1; i<=n; i++){
			arr[i] = new ArrayList<>();
		}


		for(int i = 0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());

			arr[from].add(to);
		}

		int[] count = new int[n+1];
		int max = 0;
		for(int i = 1; i<=n; i++){
			count[i] = bfs(i);
			max = Math.max(max, count[i]);
		}

		for(int i = 1; i<=n; i++){
			if(count[i] == max){
				sb.append(i).append(" ");
			}
		}

		System.out.println(sb);
	}

	static int bfs(int start){
		boolean[] visited = new boolean[n+1];
		Queue<Integer> q = new ArrayDeque<>();

		visited[start] = true;
		q.add(start);
		int cnt = 1;

		while(!q.isEmpty()){
			int now = q.poll();

			for(Integer next : arr[now]){
				if(!visited[next]){
					q.add(next);
					visited[next] = true;
					cnt++;
				}
			}
		}

		return cnt;
	}




}