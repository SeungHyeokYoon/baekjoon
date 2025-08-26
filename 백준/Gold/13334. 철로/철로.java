import java.io.*;
import java.util.*;

public class Main {

    static class Line {
        long s, e;
        Line(long a, long b) {
            s = Math.min(a, b);
            e = Math.max(a, b);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Line> lines = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            lines.add(new Line(a, b));
        }
        long d = Long.parseLong(br.readLine());

        // 끝점 기준 오름차순, 같으면 시작점 오름차순
        lines.sort((x, y) -> {
            if (x.e == y.e) return Long.compare(x.s, y.s);
            return Long.compare(x.e, y.e);
        });

        // 현재 end를 오른쪽 경계로 두고, 시작점들을 보관하는 최소힙
        PriorityQueue<Long> heap = new PriorityQueue<>();
        int ans = 0;

        for (Line L : lines) {
            // 길이가 d를 넘는 선분은 고려 불가
            if (L.e - L.s > d) continue;

            // 현재 끝점 L.e에 맞춰 [L.e - d, L.e] 윈도우 유지
            heap.offer(L.s);
            long left = L.e - d;

            // 윈도우 밖(왼쪽)인 시작점 제거
            while (!heap.isEmpty() && heap.peek() < left) {
                heap.poll();
            }
            ans = Math.max(ans, heap.size());
        }

        System.out.println(ans);
    }
}