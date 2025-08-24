import java.util.*;

class Solution {
    
    public int solution(int[][] info, int n, int m) {
        int size = info.length;
        
        int[][] dp = new int[n][size+1];
        
        int bSum = 0;
        for(int i = 0; i<size; i++){
            bSum += info[i][1];
        }
        
        for(int i = 0; i<n; i++) dp[i][0] = bSum;
        
        for(int i = 0; i<n; i++){
            for(int j = 1; j<=size; j++){
                if(info[j-1][0] <= i){
                    dp[i][j] = Math.min(dp[i][j-1], dp[i-info[j-1][0]][j-1] - info[j-1][1]);    
                }
                else{
                    dp[i][j] = dp[i][j-1];
                }
            }
            if(dp[i][size] < m) return i;
        }
        
        return -1;
    }
    

    
}