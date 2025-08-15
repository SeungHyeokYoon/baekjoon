import java.util.*;

class Solution {
    
    HashMap<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> path = new LinkedList<>();
    
    public String[] solution(String[][] tickets) {
        
        for(String[] ticket : tickets){
            if(graph.containsKey(ticket[0])){
                graph.get(ticket[0]).add(ticket[1]);
            }
            else{
                graph.put(ticket[0], new PriorityQueue<>());
                graph.get(ticket[0]).add(ticket[1]);
            }
        }
        
        dfs("ICN");
        
        return path.toArray(new String[0]);
    
        
    }
    
    void dfs(String start){
        PriorityQueue<String> end = graph.get(start);
        
        while(end != null && !end.isEmpty()){
            dfs(end.poll());
        }
        
        path.addFirst(start);
    }
}