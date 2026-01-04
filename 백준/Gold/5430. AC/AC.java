import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		Loop:
		while(T-->0){
			String op = br.readLine();
			int oplen = op.length();
			int n = Integer.parseInt(br.readLine());
			String str = br.readLine();
			st = new StringTokenizer(str.substring(1, str.length()-1), ",");

			String[] arr = new String[n];
			for(int i = 0; i<n; i++){
				arr[i] = st.nextToken();
			}

			int start = 0;
			int end = n-1;
			int now = start;

			for(int i = 0; i<oplen; i++){
				if(op.charAt(i) == 'D'){
					if(start > end){
						sb.append("error\n");
						continue Loop;
					}

					if(now == start){
						now++;
						start++;
					}
					else{
						now--;
						end--;
					}
				}
				else{
					if(now == start) now = end;
					else now = start;
				}
			}

			sb.append("[");

			if(now == start){
				for(int i = start; i<=end; i++){
					sb.append(arr[i]);
					if(i != end) sb.append(",");
				}
			}
			else{
				for(int i = end; i>=start; i--){
					sb.append(arr[i]);
					if(i != start) sb.append(",");
				}
			}

			sb.append("]\n");
		}

		System.out.println(sb);
	}






}