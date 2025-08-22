import java.io.*;
import java.util.*;

public class Main {

	static int n, size;
	static int[] arr, position;
	static HashSet<Integer> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-->0){
			String str = br.readLine();
			size = str.length();
			n = Integer.parseInt(str);
			arr = new int[size];
			position = new int[size - 1];
			set = new HashSet<>();

			boolean zeroOne = true;
			for(int i = 0; i<arr.length; i++){
				arr[i] = str.charAt(i) - '0';
				if(arr[i] != 0 && arr[i] != 1) zeroOne = false;
			}
			
			if(zeroOne){
				sb.append("Hello, BOJ 2023!").append("\n");
				continue;
			}
	
			backTrack(0);
			
			ArrayList<Integer> sortArr = new ArrayList<>(set);
			Collections.sort(sortArr);

			int m = 1;
			int count = 0;
			while(true){
				long powVal = 0;
				for(int i = 0; i<size; i++){
					powVal += (long)Math.pow(arr[i], m);
				}

				if(powVal > n) break;

				int idx = Collections.binarySearch(sortArr, (int)powVal);
				if(idx >= 0){
					count++;
				}

				m++;
			}

			sb.append(count).append("\n");

		}

		System.out.println(sb);

	}

	static void backTrack(int idx){
		if(idx == size-1){
			int plusVal = 0;
			int nowVal = arr[0];
			for(int i = 0; i<size-1; i++){
				if(position[i] == 1){
					plusVal += nowVal;
					nowVal = arr[i+1];
				}
				else{
					nowVal = nowVal * 10 + arr[i + 1];
				}
			}

			plusVal += nowVal;

			set.add(plusVal);
			return;
		}

		position[idx] = 0;
        backTrack(idx + 1);

        position[idx] = 1;
        backTrack(idx + 1);

	}




}