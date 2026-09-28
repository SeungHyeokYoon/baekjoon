#include<string>
#include <iostream>
#include <stack>

using namespace std;

bool solution(string s)
{
    
    stack<char> sta;
    
    for(int i = 0; i<s.size(); i++){
        char c = s[i];
        
        if(c == '(') sta.push('(');
        else{
            if(sta.empty()) return false;
            else if(sta.top() == ')') return false;
            else sta.pop();
        }
    }
    
    if(sta.empty()) return true;
    else return false;

}