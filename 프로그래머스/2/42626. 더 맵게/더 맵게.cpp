#include <string>
#include <vector>
#include <queue>
#include <functional>

using namespace std;




int solution(vector<int> scoville, int K) {
    
    priority_queue<int, vector<int>, greater<int>> pq;
    int cnt = 0;
    
    for(int i : scoville) pq.push(i);
    
    while(pq.top() < K){
        if(pq.size() == 1) return -1;
        
        int a = pq.top();
        pq.pop();
        int b = pq.top();
        pq.pop();
        
        pq.push(a+b*2);
        cnt++;
    }
    
    return cnt;
}