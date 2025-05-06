import java.io.*;
import java.util.*;

public class swea1218 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t<=10; t++){
            int n = Integer.parseInt(br.readLine());
            Stack<Character> s = new Stack<>();
            String str = br.readLine();
            s.add('0');

            for(int i = 0; i<n; i++){
                char now = str.charAt(i);
                if(now == ')' && s.peek() == '('){
                    s.pop();
                }
                else if(now == '}' && s.peek() == '{'){
                    s.pop();
                }
                else if(now == ']' && s.peek() == '['){
                    s.pop();
                }
                else if(now == '>' && s.peek() == '<'){
                    s.pop();
                }
                else{
                    s.add(now);
                }

            }

            sb.append("#" + t + " ");

            if(s.size() == 1){
                sb.append("1").append('\n')
            }
            else{
                sb.append("0").append('\n');
            }
            
        }

        System.out.println(sb);
    }
}
