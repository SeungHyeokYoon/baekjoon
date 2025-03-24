import java.io.*;
import java.util.*;

public class swea23792{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        Loop1:
        while(T-->0){
            int n = Integer.parseInt(br.readLine());
            if(n == 1){
                System.out.println(1);
                continue;
            }
            int[] arr = new int[n];
            point[] list = new point[n-1];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                if(i > 0){
                    if(arr[i] == arr[i-1]){
                        System.out.println(-1);
                        continue Loop1;
                    }
                    list[i-1] = new point(arr[i-1], arr[i]);
                }
            }

            Arrays.sort(arr);
            int max = 0;
            for(int i = 0; i<2*n-1; i++){
                boolean is = false;
                if(i%2 == 0){
                    int value = arr[i/2];
                    int count = 0;
                    for(int j = 0; j<n-1; j++){
                        if(value <= Math.max(list[j].y1, list[j].y2) && value >= Math.min(list[j].y1, list[j].y2)){
                            if(is){
                                if(list[j-1].y2 == list[j].y1 && list[j].y1 == value){
                                    is = false;
                                }
                                else{
                                    is = true;
                                    count++;
                                }
                            }
                            else{
                                is = true;
                                count++;
                            }
                        }
                        else{
                            is = false;
                        }
                    }
                    max = Math.max(count, max);
                    
                }
                else{
                    if(arr[i/2] == arr[(i+1)/2]){
                        continue;
                    }
                    double value = (double)(arr[i/2] + arr[(i+1)/2])/2;
                    int count = 0;
                    for(int j = 0; j<n-1; j++){
                        if(value <= Math.max(list[j].y1, list[j].y2) && value >= Math.min(list[j].y1, list[j].y2)){
                            count++;
                        }
                    }
                    max = Math.max(count, max);
                }

            }

            System.out.println(max);

            


        }
    }

    static class point{
        int y1;
        int y2;

        point(int y1, int y2){
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    
}