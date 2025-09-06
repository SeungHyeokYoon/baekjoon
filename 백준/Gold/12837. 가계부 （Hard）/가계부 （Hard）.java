import java.io.*;
import java.util.*;

public class Main{

    static int n, q;
    static long[] tree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        tree = new long[4*n];

        for(int i = 0; i<q; i++){
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                update(1, n, 1, a, b);
            }
            else{
                sb.append(sum(1, n, 1, a, b)).append("\n");
            }
        }

        System.out.println(sb);

    }

    static long sum(int start, int end, int node, int left, int right){
        if(end < left || start > right) return 0;
        if(left <= start && end <= right) return tree[node];

        int mid = (start+end)/2;

        return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
    }

    static void update(int start, int end, int node, int idx, long diff){
        if(idx < start || idx > end) return;

        tree[node] += diff;

        if(start == end) return;
        int mid = (start+end)/2;
        update(start, mid, node*2, idx, diff);
        update(mid+1, end, node*2+1, idx, diff);
    }
}