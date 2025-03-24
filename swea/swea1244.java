import java.io.*;
import java.util.*;

public class swea1244{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int k = 1; k<=T; k++){
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            char[] arr = new char[str.length()];

            for(int i = 0; i<str.length(); i++){
                arr[i] = str.charAt(i);
            }

            char loc = 0;

            for(int i = 1; i<=n; i++){

                while(true){
                    if(loc == str.length()-1){
                        char temp = arr[str.length()-1];
                        arr[str.length()-1] = arr[str.length()-2];
                        arr[str.length()-2] = temp;
                        break;
                    }

                    char max = 0;
                    int idx = 0;
                    boolean dup = false;
                    for(int j = loc; j<str.length(); j++){
                        if(arr[j] > max){
                            max = arr[j];
                            idx = j;
                            dup = false;
                        }
                        else if(arr[j] == max){
                            idx = j;
                            dup = true;
                        }
                    }

                    if(dup && i<n && idx != loc){
                        int dupi = i;
                        int duploc = loc;
                        int dupidx = loc;
                        int min = arr[duploc++];
                        while(dupi++ <=n && duploc < str.length()){
                            if(arr[duploc] < min){
                                dupidx = duploc;

                            }
                        }
                        char temp = arr[dupidx];
                        arr[dupidx] = max;
                        arr[idx] = temp;
                        break;

                    }
                    else if(idx != loc){
                        char temp = arr[loc];
                        arr[loc] = max;
                        arr[idx] = temp;
                        loc++;
                        break;
                    }
                    else{
                        loc++;
                    }




                }
            }

            sb.append('#').append(k).append(' ');
            for(int i = 0; i<str.length(); i++){
                sb.append(arr[i]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }


    
}