#include <string>
#include <vector>
#include <queue>

using namespace std;


struct server{
    int endTime;
    int cnt;
    
    server(int endTime, int cnt){
        this->endTime = endTime;
        this->cnt = cnt;
    }
};

int solution(vector<int> players, int m, int k) {
    
    int nowServer = 0;
    int cnt = 0;
    queue<server> q;
    
    for(int i = 0; i<24; i++){
        if(!q.empty()){
            if(q.front().endTime == i){
                nowServer -= q.front().cnt;
                q.pop();
            }
        }
        
        if(players[i] >= (nowServer+1)*m){
            int addServer = 0;
            
            while(players[i] >= (nowServer+1 + addServer)*m){
                addServer++;
            }
            
            q.push(server(i+k, addServer));
            nowServer += addServer;
            cnt += addServer;
        }
        
        
    }
    
    return cnt;
}