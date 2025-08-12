import java.util.*;

class Solution {
    
    int[] operands;
    String[] operators;
    int n, s;
    int[][] maxDp, minDp;
    
    public int solution(String arr[]) {
        
        n = arr.length;
        s = n/2 + 1;
        operands = new int[s];
        operators = new String[n/2];
        maxDp = new int[s][s];
        minDp = new int[s][s];
        
        for(int i = 0; i<s; i++){
            Arrays.fill(maxDp[i], Integer.MIN_VALUE);
            Arrays.fill(minDp[i], Integer.MAX_VALUE);
        }
        
        
        int operandIdx = 0;
        int operatorIdx = 0;
        
        for(int i = 0; i<n; i++){
            if(i%2 == 0){
                operands[operandIdx++] = Integer.parseInt(arr[i]);
            }
            else{
                operators[operatorIdx++] = arr[i];
            }
        }
                
        return findMax(0, s-1);
    }
    
    public int findMax(int start, int end){
        if(start == end) return operands[start];
        if(maxDp[start][end] != Integer.MIN_VALUE) return maxDp[start][end];
        
        int max = Integer.MIN_VALUE;
        for(int i = start; i<end; i++){
            if(operators[i].equals("+")){
                max = Math.max(max, findMax(start, i) + findMax(i+1, end));
            }
            else{
                max = Math.max(max, findMax(start, i) - findMin(i+1, end));
            }
        }
        
        return maxDp[start][end] = max;
        
    }
    
    public int findMin(int start, int end){
        if(start == end) return operands[start];
        if(minDp[start][end] != Integer.MAX_VALUE) return minDp[start][end];
        
        int min = Integer.MAX_VALUE;
        for(int i = start; i<end; i++){
            if(operators[i].equals("+")){
                min = Math.min(min, findMin(start, i) + findMin(i+1, end));
            }
            else{
                min = Math.min(min, findMin(start, i) - findMax(i+1, end));
            }
        }
        
        return minDp[start][end] = min;
    }
    
    
}