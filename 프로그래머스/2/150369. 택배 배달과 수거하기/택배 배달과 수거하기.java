import java.util.*;

class Solution {
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        Queue<int[]> del = new ArrayDeque<>();
        Queue<int[]> pick = new ArrayDeque<>();
        
        for(int i = n-1; i>=0; i--){
            if(deliveries[i] > 0) del.add(new int[]{i+1, deliveries[i]});
            if(pickups[i] > 0) pick.add(new int[]{i+1, pickups[i]});
        }
        
        
        while(true){
            int delval = 0;
            int pickval = 0;
            
            if(del.size() != 0){
                delval = del.peek()[0] * 2;
                int val = cap;
                while(true){
                    if(del.peek()[1] > val){
                        del.peek()[1] -= val;
                        break;
                    }
                    
                    val -= del.poll()[1];
                    
                    if(del.isEmpty() || val == 0) break;
                }
            }
            
            if(pick.size() != 0){
                pickval = pick.peek()[0] * 2;
                int val = cap;
                while(true){
                    if(pick.peek()[1] > val){
                        pick.peek()[1] -= val;
                        break;
                    }
                    
                    val -= pick.poll()[1];
                    
                    if(pick.isEmpty() || val == 0) break;
                }
            }
            
            if(delval + pickval == 0) break;
            
            answer += Math.max(delval, pickval);
        }
        
        return answer;
    }
}