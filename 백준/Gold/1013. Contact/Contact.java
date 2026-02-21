import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-->0){
			String line = br.readLine();
			sb.append(line.matches("(100+1+|01)+")?"YES\n":"NO\n");
		}
		
		System.out.println(sb);

	}


}