import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[] arr;
    static int[][] tree; //zero : 0, plus : 1, minus : 2

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){
            String str = br.readLine();
            if(str == null || str.equals("")) break;
            st = new StringTokenizer(str);

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            arr = new int[n+1];
            tree = new int[4*n+1][3];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i<=n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i<3; i++){
                init(1, n, 1, i);
            }

            while(k-->0){
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(cmd.equals("C")){
                    int prev = trans(arr[a]);
                    int next = trans(b);
                    if(prev != next){
                        update(1, n, 1, a, -1, prev);
                        update(1, n, 1, a, 1, next);
                    }
                    arr[a] = b;
                }
                else{
                    int zeroCnt = sum(1, n, 1, a, b, 0);
                    if(zeroCnt != 0){
                        sb.append(0);
                        continue;
                    }

                    int minusCnt = sum(1, n, 1, a, b, 2);
                    if(minusCnt%2 == 0) sb.append("+");
                    else sb.append("-");

                }

            }

            sb.append("\n");

        }
        System.out.println(sb);
    }

    static int trans(int num){
        if(num  == 0) return 0;
        if(num > 0) return 1;
        return 2;
    }

    static int init(int start, int end, int node, int flag){
        if(start == end){
            if(flag == 0 && arr[start] == 0) return tree[node][0] = 1;
            if(flag == 1 && arr[start] > 0) return tree[node][1] = 1;
            if(flag == 2 && arr[start] < 0) return tree[node][2] = 1;
            return 0;
        }
        int mid = (start+end)/2;

        return tree[node][flag] = init(start, mid, node*2, flag) + init(mid+1, end, node*2+1, flag);
    }

    static int sum(int start, int end, int node, int left, int right, int flag){
        if(end < left || start > right) return 0;
        if(left <= start && end <= right) return tree[node][flag];

        int mid = (start+end)/2;
        return sum(start, mid, node*2, left, right, flag) + sum(mid+1, end, node*2+1, left, right, flag);
    }

    static void update(int start, int end, int node, int idx, int diff, int flag){ //diff is +1 or -1
        if(idx < start || idx > end) return;
        
        tree[node][flag] += diff;

        if(start == end) return;
        int mid = (start+end)/2;
        update(start, mid, node*2, idx, diff, flag);
        update(mid+1, end, node*2+1, idx, diff, flag);
    }



}
