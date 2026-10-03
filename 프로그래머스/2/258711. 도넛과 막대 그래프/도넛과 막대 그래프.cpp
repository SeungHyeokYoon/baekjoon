#include <string>
#include <vector>
#include <algorithm>
#include <set>
#include <queue>

using namespace std;

vector<int> inMap(1000001);
vector<int> outMap(1000001);
vector<vector<int>> graph(1000001);
vector<bool> visited;

int bfs(int node);

vector<int> solution(vector<vector<int>> edges) {
    
    int maxNode = -1;
    
    for(int i = 0; i<(int)edges.size(); i++){
        outMap[edges[i][0]]++;
        inMap[edges[i][1]]++;
        
        maxNode = max({maxNode, edges[i][0], edges[i][1]});
        
        graph[edges[i][0]].push_back(edges[i][1]);
    }
    
    visited.assign(maxNode+1, false);
    
    int sNode = -1;
    set<int> candidat;
    for(int i = 1; i<=maxNode; i++){
        if(inMap[i] == 0){
            if(outMap[i] >= 2){
                sNode = i;
            }
            else{
                candidat.insert(i);
            }
        }
    }
    
    int dounut = 0;
    int stick = 0;
    int eight = 0;
    
    
    if(sNode != -1){
        for(int node : graph[sNode]){
            int result = bfs(node);
            
            if(result == 0) dounut++;
            else if(result == 1) stick++;
            else eight++;
        }
    }
    
    //stick += candidat.size();
    
    vector<int> answer;
    answer.push_back(sNode);
    answer.push_back(dounut);
    answer.push_back(stick);
    answer.push_back(eight);
    
    return answer;
}


int bfs(int node){
    int visitCnt = 0;
    queue<int> q;
    visited[node] = true;
    q.push(node);
    
    while(!q.empty()){
        int now = q.front(); q.pop();
        
        for(int next : graph[now]){
            if(visited[next]){
                visitCnt++;
                continue;
            }
            
            q.push(next);
            visited[next] = true;
        }
    }
    
    if(visitCnt == 0) return 1;
    else if(visitCnt == 1) return 0;
    else return 2;
    
}