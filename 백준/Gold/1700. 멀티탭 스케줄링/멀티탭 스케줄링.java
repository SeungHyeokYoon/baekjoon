import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍의 개수
        int K = Integer.parseInt(st.nextToken()); // 전기용품의 총 사용횟수
        
        int[] order = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }
        
        Set<Integer> plugged = new HashSet<>();
        int result = 0;
        
        for (int i = 0; i < K; i++) {
            int current = order[i];
            
            if (plugged.contains(current)) {  //이미 꽂혀있을 때 skip
                continue;
            }
            
            if (plugged.size() < N) {  //구멍이 넉넉할 때  꽂아주고 skip
                plugged.add(current);
            } else {
                int toRemove = -1;
                int farthest = -1;
                
                for (int plug : plugged) {  //꽂혀있는 것중에 가장 뒤늦게 나오는 걸 뽑음
                    int next = K;  // 기본값을 K로 설정 (더 이상 사용되지 않음을 의미)
                    for (int j = i + 1; j < K; j++) {
                        if (order[j] == plug) {
                            next = j;
                            break;
                        }
                    }
                    
                    if (next > farthest) {
                        farthest = next;
                        toRemove = plug;
                    }
                }
                
                plugged.remove(toRemove);
                plugged.add(current);
                result++;
            }
        }
        
        System.out.println(result);
        br.close();
    }
}