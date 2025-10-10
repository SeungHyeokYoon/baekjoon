class Solution {
    
    int register = 0;
    int salesCost = 0;
    int[] emo, discount;
    int[][] user;
    int esize, usize;
    
    public int[] solution(int[][] users, int[] emoticons) {
        emo = emoticons;
        user = users;
        esize = emo.length;
        usize = user.length;
        discount = new int[esize];
        
        backTrack(0);
        

        return new int[]{register, salesCost};
        
        
    }
    
    
    void backTrack(int idx){
        if(idx == esize){
            
            int reg = 0;
            int cost = 0;
            
            for(int i = 0; i<usize; i++){
                int sum = 0;
                boolean join = false;
                for(int j = 0; j<esize; j++){
                    if(user[i][0] <= discount[j]){
                        sum += emo[j] * (100-discount[j])/100;
                        if(sum >= user[i][1]){
                            reg++;
                            join = true;
                            break;
                        }
                    }
                }
                
                if(!join) cost += sum;
            }
             
            if(reg > register){
                register = reg;
                salesCost = cost;
            }
            else if(reg == register && cost > salesCost) salesCost = cost;
            
            return;
        }           
            
        for(int i = 1; i<=4; i++){
            discount[idx] = i*10;
            backTrack(idx+1);
        }
        
        
        
    }
    
    
    
    
    
}