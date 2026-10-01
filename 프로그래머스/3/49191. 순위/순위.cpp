#include <string>
#include <vector>
#include <queue>
#include <set>

using namespace std;

int solution(int n, vector<vector<int>> results) {
    vector<vector<int>> adj(n+1);
    vector<int> indeg(n+1, 0);
    
    for(vector<int> &r : results){
        adj[r[0]].push_back(r[1]);
        indeg[r[1]]++;
    }
    
    queue<int> q;
    for(int i = 1; i<=n; i++) if(indeg[i] == 0) q.push(i);
    
    vector<int> topo;
    while(!q.empty()){
        int u = q.front();
        q.pop();
        
        topo.push_back(u);
        
        for(int v : adj[u]) if(--indeg[v] == 0) q.push(v);
    }
    
     vector<set<int>> anc(n + 1);
     for(int u : topo){
         for(int v : adj[u]){
              anc[v].insert(anc[u].begin(), anc[u].end());
             anc[v].insert(u);
         }
     }
    
    vector<set<int>> desc(n + 1);
    for(int i = (int)topo.size() - 1; i >= 0; i--){
        int u = topo[i];
        for(int v : adj[u]){
            desc[u].insert(desc[v].begin(), desc[v].end());
            desc[u].insert(v);
        }
    }
    
    
    int answer = 0;
    for(int i = 1; i <= n; i++){
        if((int)(anc[i].size() + desc[i].size()) == n - 1) answer++;
    }
    
    return answer;
}