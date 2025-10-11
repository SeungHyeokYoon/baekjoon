import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> idMap = new HashMap<>();
        
        for(int i = 0; i<id_list.length; i++){
            idMap.put(id_list[i], i);
        }
        
        Set<String> set = new HashSet<>();
        
        for(int i = 0; i<report.length; i++){
            set.add(report[i]);
        }
        
        
        Map<String, Integer> cnt = new HashMap<>();
        for(String str : set){
            String[] strs = str.split(" ");
            if(!cnt.containsKey(strs[1])){
                cnt.put(strs[1], 1);
            }    
            else{
                cnt.put(strs[1], cnt.get(strs[1])+1);
            }
        }
        
        Set<String> ban = new HashSet<>();
        
        for(Map.Entry<String, Integer> entry : cnt.entrySet()){
            if(entry.getValue() >= k) ban.add(entry.getKey());
        }
        
        int[] ans = new int[id_list.length];
        
        for(String str : set){
            String[] strs = str.split(" ");
            if(ban.contains(strs[1])){
                int idx = idMap.get(strs[0]);
                ans[idx]++;
            }
        }
        
        return ans;
        
        
        
        
        
    }
}