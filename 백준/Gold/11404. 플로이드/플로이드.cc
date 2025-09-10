#include <iostream>
#define INF 987654321
using namespace std;

int n, m;
int arr[101][101];


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int a, b, c;
    cin >> n >> m;

    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            if(i == j){
                arr[i][j] = 0;
            }
            else{
                arr[i][j] = INF;
            }
        }
    }

    for(int i = 0; i<m; i++){
        cin >> a >> b >> c;
        arr[a][b] = min(arr[a][b], c);
    }


    for(int i = 1; i<=n; i++){
        for(int j = 1; j<=n; j++){
            for(int k = 1; k<=n; k++){
                arr[j][k] = min(arr[j][k], arr[j][i] + arr[i][k]);
            }
        }
    }

    for(int i = 1; i<=n; i++){
        for(int j = 1; j<=n; j++){
            if(arr[i][j] == INF) cout << "0 ";
            else cout << arr[i][j] << ' ';
        }
        cout << '\n';
    }


}