#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> parents;

struct cmp{
    bool operator()(const vector<int> &a, const vector<int> &b){
        return a[2] < b[2];
    }
};

int find(int x);

int solution(int n, vector<vector<int>> costs) {
    parents.assign(n, 0);
    for(int i = 0; i<n; i++) parents[i] = i;
    
    sort(costs.begin(), costs.end(), cmp());
    
    int cnt = 0;
    int idx = 0;
    int sum = 0;
    while(cnt < n-1){
        int ra = find(costs[idx][0]);
        int rb = find(costs[idx][1]);
        
        if(ra != rb){
            sum += costs[idx][2];
            cnt++;
            parents[ra] = rb;
        }
        idx++;
    }
    
    return sum;
    
}

int find(int x){
    if(parents[x] == x) return x;
    return parents[x] = find(parents[x]);
}