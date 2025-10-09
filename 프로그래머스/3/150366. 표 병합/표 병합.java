import java.util.*;

class Solution {
    
    int[] parents;
    String[] database;
    ArrayList<String> ans;
    Stack<Integer> stack;
    
    
    public String[] solution(String[] commands) {
        
        ans = new ArrayList<>();
        parents = new int[2500];
        database = new String[2500];
        stack = new Stack<>();
        
        for(int i = 0; i<2500; i++) parents[i] = i;
        
        for(String com : commands){
            String[] comm = com.split(" ");
            
            if(comm[0].equals("UPDATE")){
                if(comm.length == 4){
                    int r = Integer.parseInt(comm[1]) - 1;
                    int c = Integer.parseInt(comm[2]) - 1;
                    
                    int goal = find(r*50+c);
                    database[goal] = comm[3];
                }
                else{
                    for(int i = 0; i<2500; i++){
                        if(parents[i] == i && database[i] != null && database[i].equals(comm[1])){
                            database[i] = comm[2];
                        }
                    }
                }
            }
            else if(comm[0].equals("MERGE")){
                int r1 = Integer.parseInt(comm[1]) - 1;
                int c1 = Integer.parseInt(comm[2]) - 1;
                int r2 = Integer.parseInt(comm[3]) - 1;
                int c2 = Integer.parseInt(comm[4]) - 1;
                
                int goal1 = find(r1*50+c1);
                int goal2 = find(r2*50+c2);
                
                if(goal1 != goal2){
                    String val1 = database[goal1];
                    String val2 = database[goal2];
                    
                    parents[goal2] = goal1;
                    
                    if(val1 == null && val2 != null){
                        database[goal1] = val2;
                    }
                }
            }
            else if(comm[0].equals("UNMERGE")){
                int r = Integer.parseInt(comm[1]) - 1;
                int c = Integer.parseInt(comm[2]) - 1;
                
                int goal = find(r*50+c);
                String now = null;
                if(database[goal] != null) now = database[goal];
                
                for(int i = 0; i<2500; i++){
                    if(find(i) == goal){
                        stack.push(i);
                        database[i] = null;
                    }
                }
                
                while(!stack.isEmpty()){
                    int ss = stack.pop();
                    parents[ss] = ss;
                }
                
                database[r*50+c] = now;
            }
            else{
                int r = Integer.parseInt(comm[1]) - 1;
                int c = Integer.parseInt(comm[2]) - 1;
                
                int goal = find(r*50+c);
                
                if(database[goal] == null) ans.add("EMPTY");
                else ans.add(database[goal]);
            }
            
        }
        
        return ans.toArray(new String[0]);
        
    }
    
    int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    
    
    
}