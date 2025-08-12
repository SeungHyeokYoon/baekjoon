class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int max = 0;
        int[] dp = new int[n];
        
        //첫 번째 집을 훔쳤을 때 
        dp[0] = 0;
        dp[1] = money[0];
        for(int i = 1; i<n-1; i++){
            dp[i+1] = Math.max(dp[i], dp[i-1] + money[i]);
        }
        
        max = dp[n-1];
        
        //첫 번째 집 안훔쳤을 때 
        dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 1; i<n; i++){
            dp[i+1] = Math.max(dp[i], dp[i-1] + money[i]);
        }
        
        max = Math.max(dp[n], max);
        
        
        
        return max;
    }
}