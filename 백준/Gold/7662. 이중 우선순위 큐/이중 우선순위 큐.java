import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-->0){
			int n = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>();

			while(n-->0){
				st = new StringTokenizer(br.readLine());
				String op = st.nextToken();
				int val = Integer.parseInt(st.nextToken());

				if(op.equals("I")){
					map.put(val, map.getOrDefault(val, 0) + 1);
				}
				else{
                    if (map.isEmpty()) continue;
					int key = (val == -1) ? map.firstKey() : map.lastKey();
                    int cnt = map.get(key);

                    if (cnt == 1) map.remove(key);
                    else map.put(key, cnt - 1);
				}

			}

			if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(' ').append(map.firstKey()).append('\n');
            }
		}

		System.out.print(sb);

	}


}