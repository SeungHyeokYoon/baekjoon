#include <string>
#include <vector>
#include <queue>

using namespace std;

struct truck{
    int end;
    int weight;
};


int solution(int bridge_length, int weight, vector<int> truck_weights) {
    
    int endIdx = truck_weights.size();
    int idx = 0;
    int time = 0;
    
    queue<truck> q;
    
    int nowWeight = 0;
    
    while(idx<endIdx){
        time++;
        
        if(!q.empty() && q.front().end == time){
            nowWeight -= q.front().weight;
            q.pop();
        }
        
        if(nowWeight + truck_weights[idx] <= weight){
            q.push({time + bridge_length, truck_weights[idx]});
            nowWeight += truck_weights[idx++];
            
        }
        
    }
    
    
    
    return q.back().end;
}