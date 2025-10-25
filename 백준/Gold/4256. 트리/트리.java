import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[] pre, in;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while(T-->0){
			n = Integer.parseInt(br.readLine());

			pre = new int[n];
			in = new int[n];

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<n; i++){
				pre[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<n; i++){
				in[i] = Integer.parseInt(st.nextToken());
			}

			postOrder(0, 0, n);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void postOrder(int root, int start, int end){
		if(root == n) return;
		int r = pre[root];

		for(int i = start; i<end; i++){
			if(r == in[i]){
				postOrder(root+1, start, i);
				postOrder(root+(i-start+1), i+1, end);
				sb.append(r).append(" ");
				return;
			}
		}
	}


}