import java.io.*;
import java.util.*;

public class bj9935{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        Stack<component> s = new Stack<>();
        String str = br.readLine();
        String bomb = br.readLine();
        char[] bombstr = new char[bomb.length()];

        for(int i = 0; i<bomb.length(); i++){
            bombstr[i] = bomb.charAt(i);
        }

        s.add(new component('0', -2));
        //if str length is 1?
        for(int i = 0; i<str.length(); i++){
            component cur = s.peek();
            if(cur.num == -1 || cur.num == -2){
                if(str.charAt(i) == bombstr[0]){
                    if(bomb.length() != 1){
                        s.add(new component(str.charAt(i), 0));
                    }
                }
                else{
                    s.add(new component(str.charAt(i), -1));
                }
            }
            else{
                if(str.charAt(i) == bombstr[cur.num+1]){
                    if(cur.num+2 == bomb.length()){
                        for(int j = 0; j<bomb.length()-1; j++){
                            s.pop();
                        }
                    }
                    else{
                        s.add(new component(str.charAt(i), cur.num+1));
                    }
                }
                else if(str.charAt(i) == bombstr[0]){
                    s.add(new component(str.charAt(i), 0));
                }
                else{
                    s.add(new component(str.charAt(i), -1));
                }
            }
        }

        if(s.peek().num == -2){
            System.out.println("FRULA");
            return;
        }
        else{
            int size = s.size()-1;
            char[] ans = new char[size];
            for(int i = size-1; i>=0; i--){
                ans[i] = s.pop().value;
                
            }
            for(int i = 0; i<size; i++){
                sb.append(ans[i]);
            }
            System.out.println(sb);

        }



    }

    static class component{
        char value;
        int num;

        component(char value, int num){
            this.value = value;
            this.num = num;
        }
    }
    
}