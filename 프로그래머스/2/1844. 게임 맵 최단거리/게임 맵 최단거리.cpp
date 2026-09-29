#include <vector>
#include <queue>


using namespace std;

struct node{
    int x;
    int y;
    int cnt;
    
    node(int x, int y, int cnt){
        this->x = x;
        this->y = y;
        this->cnt = cnt;
    }
};

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
int bfs(int n, int m, vector<vector<int>> &maps, vector<vector<bool>> &visited);

int solution(vector<vector<int>> maps)
{
    int n = maps.size();
    int m = maps[0].size();
    
    vector<vector<bool>> visited(n, vector<bool>(m));
    
    
    return bfs(n, m, maps, visited);
}


int bfs(int n, int m, vector<vector<int>> &maps, vector<vector<bool>> &visited){
    queue<node> q;
    
    visited[0][0] = true;
    q.push(node(0, 0, 1));
    
    while(!q.empty()){
        node &now = q.front();
        
        if(now.y == n-1 && now.x == m-1) return now.cnt;
        
        for(int i = 0; i<4; i++){
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];
            
            if(nx>=0 && ny>=0 && nx<m && ny<n && !visited[ny][nx] && maps[ny][nx] == 1){
                q.push(node(nx, ny, now.cnt+1));
                visited[ny][nx] = true;
            }
        }
        q.pop();
    }
    
    return -1;
    
    
}