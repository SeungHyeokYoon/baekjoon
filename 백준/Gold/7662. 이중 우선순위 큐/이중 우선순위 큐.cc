#include <iostream>
#include <queue>
#include <unordered_map>
#include <vector>
using namespace std;

priority_queue<int> rg;
priority_queue<int, vector<int>, greater<int>> rl;
unordered_map<int, int> cnt;


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int n, m, a;
    char c;
    cin >> n;

    for(int i = 0; i<n; i++){

        cnt.clear();
        while(!rl.empty()){rl.pop();}
        while(!rg.empty()){rg.pop();}


        cin >>m;

        for(int j = 0; j<m; j++){
            cin >> c >> a;
            if(c == 'I'){  
                rg.push(a);
                rl.push(a);
                cnt[a]++;
            }
            else{
                if(a == 1){
                    while(!rg.empty() && cnt[rg.top()] == 0){
                        rg.pop();
                    }
                    
                    if(rg.empty()){
                        continue;
                    }

                    cnt[rg.top()]--;
                    rg.pop();
                }
                else{
                    while(!rl.empty() && cnt[rl.top()] == 0){
                        rl.pop();
                    }
                    
                    if(rl.empty()){
                        continue;
                    }

                    cnt[rl.top()]--;
                    rl.pop();
                }
                

            }

        }

        while(!rg.empty() && cnt[rg.top()] == 0){
            rg.pop();
        }

        while(!rl.empty() && cnt[rl.top()] == 0){
            rl.pop();
        }



        if(rg.empty()){
            cout << "EMPTY\n";
        }
        else{
            cout << rg.top() << " " << rl.top() << "\n";
        }


    }

}