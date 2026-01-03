import java.io.*;
import java.util.*;

public class Main {

	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		divide(n, r, c);
		System.out.println(sum);
	}

	static void divide(int n, int r, int c){

		if(n == 0) return;

		int half = 1<<(n-1);
		int area = half*half;

		if(r < half){
			if(c < half){
				divide(n-1, r, c);
			}
			else{
				sum += area;
				divide(n-1, r, c-half);
			}
		}
		else{
			if(c < half){
				sum += area*2;
				divide(n-1, r-half, c);
			}
			else{
				sum += area*3;
				divide(n-1, r-half, c-half);
			}
		}

	}
}