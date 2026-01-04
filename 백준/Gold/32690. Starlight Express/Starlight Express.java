import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] parent, size;
    static int[] xs, ys;

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;

        // union by size
        if (size[a] < size[b]) {
            int t = a; a = b; b = t;
        }
        parent[b] = a;
        size[a] += size[b];
    }

    static long key(int x, int y) {
        // (x,y) -> long key
        return ((long) x << 32) ^ (y & 0xffffffffL);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine().trim());
        xs = new int[n];
        ys = new int[n];

        parent = new int[n];
        size = new int[n];

        // 좌표 -> "그 좌표를 가진 어떤 점 인덱스"
        HashMap<Integer, Integer> xRep = new HashMap<>();
        HashMap<Integer, Integer> yRep = new HashMap<>();

        // 이미 존재하는 (x,y) 체크용 (전부 한 컴포넌트일 때 새 점 찾기)
        HashSet<Long> occupied = new HashSet<>(Math.max(16, n * 2));

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xs[i] = x;
            ys[i] = y;
            occupied.add(key(x, y));

            Integer jx = xRep.get(x);
            if (jx != null) union(i, jx);

            Integer jy = yRep.get(y);
            if (jy != null) union(i, jy);

            // 맵 갱신: 이 좌표는 "현재 컴포넌트의 어떤 원소"를 가리키면 됨
            // 나중에 꺼낼 때 find()로 루트로 보정되니까 안전
            xRep.put(x, i);
            yRep.put(y, i);
        }

        // 컴포넌트별 크기 모으기
        HashMap<Integer, Integer> compSize = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int r = find(i);
            // size[r]는 루트에서만 의미 있음
            compSize.put(r, size[r]);
        }

        // 가장 큰/두 번째 컴포넌트 찾기
        int firstRoot = -1, secondRoot = -1;
        int firstSize = 0, secondSize = 0;

        for (Map.Entry<Integer, Integer> e : compSize.entrySet()) {
            int r = e.getKey();
            int s = e.getValue();

            if (s > firstSize) {
                secondSize = firstSize; secondRoot = firstRoot;
                firstSize = s; firstRoot = r;
            } else if (s > secondSize) {
                secondSize = s; secondRoot = r;
            }
        }

        // 전부 한 컴포넌트면: 기존에 없던 (x, y) 하나 찾기
        if (secondRoot == -1) {
            int x = xs[firstRoot]; // 아무 x나
            int y = -1_000_000_000; // 블로그 풀이에서 자주 쓰는 안전한 시작값 :contentReference[oaicite:1]{index=1}
            while (occupied.contains(key(x, y))) y++;
            System.out.println(x + " " + y);
            return;
        }

        // 두 컴포넌트 이상이면: 가장 큰 그룹의 x + 두 번째 그룹의 y
        System.out.println(xs[firstRoot] + " " + ys[secondRoot]);
    }
}
