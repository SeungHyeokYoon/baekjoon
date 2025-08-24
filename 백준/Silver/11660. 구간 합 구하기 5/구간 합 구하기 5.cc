#include <iostream>

using namespace std;

int map[1025][1025] = {0};

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    
    int n, m, a;
    int x1, x2, y1, y2;
    cin >> n >> m;

    for(int i = 1; i<=n; i++){
        for(int j = 1; j<=n; j++){
            cin >> a;
            map[i][j] = map[i-1][j] + map[i][j-1] + a - map[i-1][j-1];
        }
    }

    for(int i = 0; i<m; i++){
        int sum = 0;
        cin >> x1 >> y1 >> x2 >> y2;

        sum = map[x2][y2] - map[x1 - 1][y2] - map[x2][y1 - 1] + map[x1 - 1][y1 - 1]; 

        cout << sum << '\n';

    }
 

}