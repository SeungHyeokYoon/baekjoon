#include <string>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

string solution(string number, int k) {
    
    stack<char> stk;
    
    for(char c : number){
        while(k > 0 && !stk.empty() && stk.top() < c){
            stk.pop();
            k--;
        }
        stk.push(c);
    }
    
    while(k > 0){
        stk.pop();
        k--;
    }
    
    string answer;
    while(!stk.empty()){
        answer += stk.top();
        stk.pop();
    }
    
    reverse(answer.begin(), answer.end());
    return answer;
}