import java.io.*;
import java.util.*;

public class bj1202 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n, k;
        long ans = 0;
        List<Jewel> jewels = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(m, v));
        }
        
        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        
        Collections.sort(jewels);
        Arrays.sort(bags);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0;
        
        for (int i = 0; i < k; i++) {
            while (idx < n && jewels.get(idx).weight <= bags[i]) {
                pq.offer(jewels.get(idx).value);
                idx++;
            }
            
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        
        System.out.println(ans);
    }
    
    static class Jewel implements Comparable<Jewel> {
        int weight, value;
    
        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    
        @Override
        public int compareTo(Jewel o) {
            return this.weight - o.weight;
        }
    }
}