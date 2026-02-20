import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());

		long[] arr = new long[n];
		for(int i = 0; i<n; i++){
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);

		long sum = arr[0];
		if(sum != 1){
			System.out.println(1);
			return;
		}

		for(int i = 1; i<n; i++){
			long now = arr[i];
			if(sum + 1 < now){
				System.out.println(sum+1);
				return;
			}
			sum += now;
		}

		System.out.println(sum+1);

	}


}