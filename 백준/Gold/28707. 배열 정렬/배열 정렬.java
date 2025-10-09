import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static HashSet<Change> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;


		n = Integer.parseInt(br.readLine());
		int[] init = new int[n];
		st = new StringTokenizer(br.readLine());

		for(int i = 0; i<n; i++){
			init[i] = Integer.parseInt(st.nextToken());
		}

		m = Integer.parseInt(br.readLine());
		for(int i = 0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			set.add(new Change(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}

		bfs(init);


	}

	static void bfs(int[] init){
		PriorityQueue<arrrr> q = new PriorityQueue<>();
		HashMap<String, Integer> map = new HashMap<>();

		String initKey = Arrays.toString(init);
		map.put(initKey, 0);
		q.add(new arrrr(init, 0));

		while(!q.isEmpty()){
			arrrr now = q.poll();

			if(now.isSort()){
				System.out.println(now.cost);
				return;
			}

			for(Change element : set){
				int[] nextArr = now.arr.clone();
				int tmp = nextArr[element.idx1];
				nextArr[element.idx1] = nextArr[element.idx2];
				nextArr[element.idx2] = tmp;

				int nextCost = now.cost + element.cost;
				String key = Arrays.toString(nextArr);

				if(!map.containsKey(key) || map.get(key) > nextCost){
					map.put(key, nextCost);
					q.add(new arrrr(nextArr, nextCost));
				}
			}
		}

		System.out.println(-1);

	}

	static class Change{
		int idx1;
		int idx2;
		int cost;

		Change(int idx1, int idx2, int cost){
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.cost = cost;
		}
	}

	static class arrrr implements Comparable<arrrr>{
		int[] arr;
		int cost;

		arrrr(int[] arr, int cost){
			this.arr = arr;
			this.cost = cost;
		}

		public boolean isSort(){
			for(int i = 0; i<arr.length-1; i++){
				if(arr[i] > arr[i+1]) return false;
			}
			return true;
		}

		@Override
		public int compareTo(arrrr o) {
			return this.cost - o.cost;
		}


	}
}