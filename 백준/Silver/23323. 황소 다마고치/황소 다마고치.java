import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-->0){
			st = new StringTokenizer(br.readLine());

			long n = Long.parseLong(st.nextToken());
			long m = Long.parseLong(st.nextToken());
			long result = 0;

			while(n>=1){
				n >>= 1;
				result++;
			}
			sb.append(result+m).append("\n");
		}

		System.out.println(sb);
	}
}