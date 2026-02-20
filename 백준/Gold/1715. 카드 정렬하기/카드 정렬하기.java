import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		if(n == 1){
			System.out.println(0);
			return;
		}

		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		for(int i = 0; i<n; i++){
			pq.add(Long.parseLong(br.readLine()));
		}

		long ans = 0;

		while(true){
			long a = pq.poll();
			long b = pq.poll();

			ans += a+b;

			if(pq.isEmpty()) break;

			pq.add(a+b);
		}

		System.out.println(ans);

	}


}