#include <iostream>
#include <algorithm>
using namespace std;



int main(){
    int n;
    cin >> n;

    int *p = new int[n+1];
    p[0] = 0;
    p[1] = 0;
    p[2] = 1;
    p[3] = 1;

    for(int i = 4; i<=n; i++){
        if(i%2 == 0 && i%3 == 0){
            p[i] = min(p[i/3], p[i/2])+1;
        }
        else if(i%2 == 0){
            p[i] = min(p[i/2], p[i-1]) + 1;
        }
        else if(i%3 == 0){
            p[i] = min(p[i/3], p[i-1]) + 1;
        }
        else{
            p[i] = p[i-1] + 1;
        }
    }

    cout << p[n];


    

}