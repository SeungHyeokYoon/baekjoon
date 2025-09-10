import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        
        int size = cards.length;
        int[] visited = new int[size/2 + 1];
        PriorityQueue<Integer> zero = new PriorityQueue<>();
        PriorityQueue<Integer> remain = new PriorityQueue<>();
        Arrays.fill(visited, -1);
        
        for(int i = 0; i<size/3; i++){
            if(cards[i] > size/2) cards[i] = size+1-cards[i];
            if(visited[cards[i]] == -1) visited[cards[i]] = 0;
            else zero.add(0);
        }
        
        int round = 0;
        int idx = size/3;
        while(idx < size){
            round++;
            for(int i = 0; i<2; i++){
                if(cards[idx] > size/2) cards[idx] = size+1-cards[idx];
                if(visited[cards[idx]] == -1) visited[cards[idx]] = round;
                else if(visited[cards[idx]] == 0) zero.add(round);
                else remain.add(round);
                idx++;
            }
        }
        
        int answer = 1;
        while(answer <= round){
            if(!zero.isEmpty()){
                if(zero.peek() <= answer){
                    if(zero.poll() != 0){
                        coin--;
                        if(coin < 0) break;
                    }
                    answer++;
                    continue;
                }
            }
            
            if(remain.poll() <= answer){
                coin -= 2;
                if(coin < 0) break;
            }
            else break;
            
            answer++;
        }
        
        
        return answer;
    }
}