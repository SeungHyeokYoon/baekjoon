#include <string>
#include <vector>

using namespace std;

int solution(vector<int> schedules, vector<vector<int>> timelogs, int startday) {
    int cnt = schedules.size();
    
    for(int i = 0; i<cnt; i++){
        int newnum = schedules[i]+10;
        
        if(newnum%100 >= 60){
            schedules[i] = newnum + 40;
        }
        else{
            schedules[i] = newnum;
        }
    }
    
    int ans = 0;
    
    for(int i = 0; i<cnt; i++){
        bool can = true;
        for(int j = 0; j<7; j++){
            
            int day = (startday + j)%7;
            
            if(day%6 == 0 || day%7 == 0) continue;
            if(timelogs[i][j] > schedules[i]){
                can = false;
                break;
            }
        }
        
        if(can) ans++;
    }
    
    return ans;
}