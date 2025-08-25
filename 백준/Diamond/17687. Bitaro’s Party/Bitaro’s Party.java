import java.io.*;
import java.util.*;

public class Main {

    static int n, m, q, X;
    static ArrayList<Integer>[] map, rev;     // rev 추가
    static int[] indegree;
    static List<PathInfo>[] longestPaths;
    static int[] topoPos;                     // 위상 순서 위치 저장
    static int[] topoOrder;                   // 위상 순서

    static class PathInfo implements Comparable<PathInfo>{
        int start, dist;
        PathInfo(int start, int dist){ this.start = start; this.dist = dist; }
        @Override public int compareTo(PathInfo o){ return o.dist - this.dist; }
        @Override public boolean equals(Object o){
            return (o instanceof PathInfo) && this.start == ((PathInfo)o).start;
        }
        @Override public int hashCode(){ return Integer.hashCode(start); }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        X = (int)Math.sqrt(n);

        map = new ArrayList[n+1];
        rev = new ArrayList[n+1];                 // init rev
        indegree = new int[n+1];
        longestPaths = new ArrayList[n+1];
        topoPos = new int[n+1];
        topoOrder = new int[n];

        for(int i = 1; i<=n; i++){
            map[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
            longestPaths[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start].add(end);
            rev[end].add(start);                 // 역간선
            indegree[end]++;
        }

        computeLongestPathAndTopo();             // longestPaths + topo 저장

        for(int i = 0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            HashSet<Integer> busy = new HashSet<>();
            int read = 0;
            while (read < Y) {
                if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
                busy.add(Integer.parseInt(st.nextToken()));
                read++;
            }

            int ans;
            if (Y >= X) {
                // 폴백: on-demand DP (역그래프로 T까지의 최장거리)
                ans = solveByOnDemandDP(T, busy);
            } else {
                // 빠른 경로: top X+1 후보 안에서 고르기
                int max = -1;
                for (PathInfo info : longestPaths[T]) {
                    if (!busy.contains(info.start)) {
                        max = Math.max(max, info.dist);
                    }
                }
                ans = max;
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

    @SuppressWarnings("unchecked")
    static void computeLongestPathAndTopo(){
        // Kahn: 위상순서 + longestPaths 전파(누적은 acc에 모아 끝에 한 번만 정규화)
        int[] indeg = Arrays.copyOf(indegree, indegree.length);
        Queue<Integer> que = new ArrayDeque<>();

        Map<Integer, Integer>[] acc = new HashMap[n + 1];
        for(int i = 1; i<=n; i++) acc[i] = new HashMap<>();

        int tp = 0;
        for(int i = 1; i<=n; i++){
            if(indeg[i] == 0){
                que.add(i);
                longestPaths[i].add(new PathInfo(i, 0)); // 자기 자신 0
            }
        }

        while(!que.isEmpty()){
            int cur = que.poll();

            // topo 기록
            topoOrder[tp] = cur;
            topoPos[cur] = tp++;
            
            for(int next : map[cur]){
                // cur은 이미 prune된 상태(top X+1)
                for(PathInfo info : longestPaths[cur]){
                    int nd = info.dist + 1;
                    acc[next].put(info.start, Math.max(acc[next].getOrDefault(info.start, -1), nd));
                }

                if(--indeg[next] == 0){
                    ArrayList<PathInfo> tmp = new ArrayList<>();
                    for(Map.Entry<Integer, Integer> e : acc[next].entrySet()){
                        tmp.add(new PathInfo(e.getKey(), e.getValue()));
                    }
                    tmp.add(new PathInfo(next, 0));          // 자기 자신 0 경로 보존
                    Collections.sort(tmp);
                    if(tmp.size() > X + 1) tmp.subList(X + 1, tmp.size()).clear();
                    longestPaths[next] = tmp;
                    acc[next].clear();

                    que.add(next);
                }
            }
        }
        // (tp == n) 보장: 입력이 DAG이므로
    }

    // Y ≥ X 일 때만 호출: 역그래프로 T까지의 최장거리 on-demand DP
    static int solveByOnDemandDP(int T, HashSet<Integer> busy){
        // 1) 역그래프에서 T로부터 거꾸로 reachable set 수집
        boolean[] vis = new boolean[n+1];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(T);
        vis[T] = true;
        ArrayList<Integer> reach = new ArrayList<>();
        reach.add(T);
        while(!dq.isEmpty()){
            int v = dq.poll();
            for(int p : rev[v]){
                if(!vis[p]){
                    vis[p] = true;
                    dq.add(p);
                    reach.add(p);
                }
            }
        }

        // 2) dp 초기화
        //    topo 내림차순(= 큰 topoPos → 작은 topoPos)으로 처리해야
        //    간선 p->v에서 v가 먼저 처리됨
        reach.sort((a,b) -> topoPos[b] - topoPos[a]);
        final int NEG = -1_000_000_000;
        int[] dp = new int[n+1];
        Arrays.fill(dp, NEG);
        dp[T] = 0;

        // 3) 전파: v 처리 후 부모 p 갱신
        for(int v : reach){
            if (dp[v] == NEG) continue; // T에 도달 불가한 가지
            for(int p : rev[v]){
                if (vis[p]) dp[p] = Math.max(dp[p], dp[v] + 1);
            }
        }

        // 4) 답: busy가 아닌 정점 중 최대
        int ans = -1;
        for(int u : reach){
            if (!busy.contains(u)) {
                ans = Math.max(ans, dp[u]);
            }
        }
        return ans;
    }
}
