import java.io.*;
import java.util.*;

/**
 * BOJ 9843 - LVM
 * 시뮬레이션: 스택 + 단일 레지스터, PC(프로그램 카운터) 기반 실행
 */
public class Main {
    static class Instr {
        String op;
        int arg;
        boolean hasArg;
        Instr(String op) { this.op = op; this.hasArg = false; }
        Instr(String op, int arg) { this.op = op; this.arg = arg; this.hasArg = true; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        Instr[] prog = new Instr[n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            // 안전 처리: 앞뒤 공백 제거 후 토큰화
            StringTokenizer st = new StringTokenizer(line.trim());
            String op = st.nextToken();
            if (op.equals("PUSH") || op.equals("IFZERO")) {
                int arg = Integer.parseInt(st.nextToken());
                prog[i] = new Instr(op, arg);
            } else {
                prog[i] = new Instr(op);
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int reg = 0;        // 단일 레지스터, 초기값 0
        int pc = 0;         // 프로그램 카운터 (0-based)

        while (true) {
            Instr ins = prog[pc];

            switch (ins.op) {
                case "PUSH": {
                    stack.push(ins.arg);
                    pc++;
                    break;
                }
                case "STORE": {
                    // 스택 top을 레지스터에 저장
                    reg = stack.pop();
                    pc++;
                    break;
                }
                case "LOAD": {
                    // 레지스터 값을 스택에 push
                    stack.push(reg);
                    pc++;
                    break;
                }
                case "PLUS": {
                    // 두 개 pop 후 합을 push
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a + b);
                    pc++;
                    break;
                }
                case "TIMES": {
                    // 두 개 pop 후 곱을 push
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a * b);
                    pc++;
                    break;
                }
                case "IFZERO": {
                    int v = stack.pop();
                    if (v == 0) {
                        pc = ins.arg; // 0부터 시작하는 n번째 명령어로 점프
                    } else {
                        pc++;
                    }
                    break;
                }
                case "DONE": {
                    System.out.println(stack.peek());
                    return; // 프로그램 종료
                }
                default:
                    // 문제 조건상 등장하지 않음
                    throw new IllegalStateException("Unknown instruction: " + ins.op);
            }
        }
    }
}
