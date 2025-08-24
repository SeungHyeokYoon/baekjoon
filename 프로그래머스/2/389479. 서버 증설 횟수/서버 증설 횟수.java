import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Queue<Integer> q = new ArrayDeque<>();
        
        for(int i = 0; i<24; i++){
            
            while(!q.isEmpty() && q.peek() == i){
                q.poll();
            }
            
            int create = players[i]/m - q.size();
            for(int j = 0; j<create; j++){
                q.add(i+k);
                answer++;
            }
        }
        return answer;
    }
}