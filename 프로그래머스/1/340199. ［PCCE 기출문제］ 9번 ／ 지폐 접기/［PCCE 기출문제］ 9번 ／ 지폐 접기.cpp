#include <string>
#include <vector>

using namespace std;

int solution(vector<int> wallet, vector<int> bill) {
    int big = max(wallet[0], wallet[1]);
    int small = min(wallet[0], wallet[1]);
    
    int ans = 0;
    
    while(true){
        int min =  std::min(bill[0], bill[1]);
        int max =  std::max(bill[0], bill[1]);
        
        if(max<=big && min<=small) break;
        
        bill[0] = min;
        bill[1] = max/2;
        ans++;
    }
    
    return ans;
}