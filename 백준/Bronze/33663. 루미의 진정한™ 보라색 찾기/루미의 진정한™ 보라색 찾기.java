import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // H 범위
        st = new StringTokenizer(br.readLine());
        double Hlo = Double.parseDouble(st.nextToken());
        double Hhi = Double.parseDouble(st.nextToken());
        // S 범위
        st = new StringTokenizer(br.readLine());
        double Slo = Double.parseDouble(st.nextToken());
        double Shi = Double.parseDouble(st.nextToken());
        // V 범위
        st = new StringTokenizer(br.readLine());
        double Vlo = Double.parseDouble(st.nextToken());
        double Vhi = Double.parseDouble(st.nextToken());
        // RGB 값 입력
        st = new StringTokenizer(br.readLine());
        double R = Double.parseDouble(st.nextToken());
        double G = Double.parseDouble(st.nextToken());
        double B = Double.parseDouble(st.nextToken());

        // V = max(R, G, B)
        double V = Math.max(R, Math.max(G, B));
        // m = min(R, G, B)
        double m = Math.min(R, Math.min(G, B));

        // S 계산: S = 255 * (V - m) / V
        double S;
        if (V == 0) {
            // V가 0이면 색이 모두 검정(=0)이기 때문에 차이가 0 → S = 0
            S = 0;
        } else {
            S = 255.0 * (V - m) / V;
        }

        // H 계산
        double H = 0.0;
        if (V == m) {
            // 색이 회색 계열 (무채색) 이면 H는 정의되지 않지만
            // 보통 임의로 0으로 처리하거나 특별 케이스를 둠
            H = 0.0;
        } else {
            if (V == R) {
                H = 60.0 * (G - B) / (V - m);
            } else if (V == G) {
                H = 120.0 + 60.0 * (B - R) / (V - m);
            } else { // V == B
                H = 240.0 + 60.0 * (R - G) / (V - m);
            }
            if (H < 0) {
                H += 360.0;
            }
        }

        // 판별
        boolean ok = true;
        // Hlo ≤ H ≤ Hhi
        if (!(Hlo <= H && H <= Hhi)) ok = false;
        // Slo ≤ S ≤ Shi
        if (!(Slo <= S && S <= Shi)) ok = false;
        // Vlo ≤ V ≤ Vhi
        if (!(Vlo <= V && V <= Vhi)) ok = false;

        if (ok) {
            System.out.println("Lumi will like it.");
        } else {
            System.out.println("Lumi will not like it.");
        }

        br.close();
    }
}
