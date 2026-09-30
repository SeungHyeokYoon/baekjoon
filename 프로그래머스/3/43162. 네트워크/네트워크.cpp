#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<bool> visited;
int cnt, m;

void bfs(int idx, vector<vector<int>> &computers);

int solution(int n, vector<vector<int>> computers) {
    
    cnt = 0;
    m = n;
    visited.assign(m, false);
    
    for(int i = 0; i<m; i++){
        if(!visited[i]) bfs(i, computers);
    }
    
    
    return cnt;
}

void bfs(int idx, vector<vector<int>> &computers){
    cnt++;
    
    visited[idx] = true;
    queue<int> q;
    q.push(idx);
    
    while(!q.empty()){
        int now = q.front();
        q.pop();
        
        for(int i = 0; i<m; i++){
            if(now == i) continue;
            if(computers[now][i] == 1 && !visited[i]){
                visited[i] = true;
                q.push(i);
            }
        }
    }
}