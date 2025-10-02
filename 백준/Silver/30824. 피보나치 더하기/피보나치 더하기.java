import java.util.*;

public class Main {
    static ArrayList<Long> fib = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 피보나치 수열 생성 (10^16 이하까지)
        fib.add(1L); // F1
        fib.add(1L); // F2
        while (true) {
            long next = fib.get(fib.size() - 1) + fib.get(fib.size() - 2);
            if (next > 1e16) break;
            fib.add(next);
        }

        int T = sc.nextInt();
        while (T-- > 0) {
            int k = sc.nextInt();
            long x = sc.nextLong();

            boolean possible = false;
            if (k == 1) {
                for (long f : fib) {
                    if (f == x) {
                        possible = true;
                        break;
                    }
                }
            } else if (k == 2) {
                for (long f1 : fib) {
                    if (f1 > x) break;
                    for (long f2 : fib) {
                        if (f1 + f2 > x) break;
                        if (f1 + f2 == x) {
                            possible = true;
                            break;
                        }
                    }
                    if (possible) break;
                }
            } else if (k == 3) {
                for (long f1 : fib) {
                    if (f1 > x) break;
                    for (long f2 : fib) {
                        if (f1 + f2 > x) break;
                        for (long f3 : fib) {
                            if (f1 + f2 + f3 > x) break;
                            if (f1 + f2 + f3 == x) {
                                possible = true;
                                break;
                            }
                        }
                        if (possible) break;
                    }
                    if (possible) break;
                }
            }

            System.out.println(possible ? "YES" : "NO");
        }
    }
}
