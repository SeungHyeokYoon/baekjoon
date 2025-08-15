import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        int cnt = 0;
        
        Arrays.sort(routes, new Comparator<>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[1] - b[1];
            }
        });
        
        int idx = 0;
        int camera = -30001;
        int size = routes.length;
        
        while(idx<size){
            if(routes[idx][0]>camera || camera>routes[idx][1]){
                camera = routes[idx][1];
                cnt++;
            }
            idx++;
        }
        
        
        return cnt;
    }
}