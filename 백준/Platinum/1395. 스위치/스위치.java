    import java.io.*;
    import java.util.*;

    public class Main{

        static int n, m;
        static int[] tree, lazy;

        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            tree = new int[n*4];
            lazy = new int[n*4];


            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine());

                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(cmd == 0){
                    update(1, 1, n, a, b);
                }
                else{
                    sb.append(sum(1, 1, n, a, b)).append("\n");
                }
            }
            System.out.println(sb);
        }

        static void propagation(int start, int end, int node){
            if(lazy[node] % 2 == 1){
                tree[node] = end - start + 1 - tree[node];

                if(start != end){
                    lazy[node*2]++;
                    lazy[node*2+1]++;
                }

                lazy[node] = 0;
            }
        }


        static int sum(int node, int start, int end, int left, int right){
            propagation(start, end, node);
            if(end < left || right < start) return 0;

            if(left <= start && end <= right) return tree[node];

            int mid = (start + end) >> 1;

            return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
        }

        static void update(int node, int start, int end, int left, int right){
            propagation(start, end, node);

            if(end < left || right < start) return;

            if(left <= start && end <= right){
                tree[node] = end - start + 1 - tree[node];
                if(start != end){
                    lazy[node*2]++;
                    lazy[node*2+1]++;
                }
                return;
            }

            int mid = (start + end) >> 1;
            update(node*2, start, mid, left, right);
            update(node*2+1, mid+1, end, left, right);

            tree[node] = tree[node*2] + tree[node*2+1];
        }


    }