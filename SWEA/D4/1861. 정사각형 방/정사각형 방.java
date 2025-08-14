import java.io.*;
import java.util.*;
 
public class Solution {

    static int n;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){

            n = Integer.parseInt(br.readLine());
            Point[] idxArr = new Point[n*n+1];

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    idxArr[Integer.parseInt(st.nextToken())] = new Point(j, i);
                }
            }

            int max = 1;
            int length = 1;
            int roomNum = 1;
            int maxRoom = 1;
            for(int i = 2; i<=n*n; i++){
                if(isNext(idxArr[i-1], idxArr[i])){
                    length++;
                }
                else{
                    if(max<length){
                        max = length;
                        maxRoom = roomNum;
                    }
                    roomNum = i;
                    length = 1;
                }
            }

            if(max < length){
                max = length;
                maxRoom = roomNum;
            }

            sb.append("#").append(tc).append(" ").append(maxRoom).append(" ").append(max).append("\n");
        }
 
        System.out.print(sb);
    }

    static boolean isNext(Point prev, Point now){
        if(now.x == prev.x){
            if(now.y+1 == prev.y || now.y == prev.y+1) return true;
        }
        else if(now.y == prev.y){
            if(now.x+1 == prev.x || now.x == prev.x+1) return true;
        }

        return false;
    }

    static class Point{
        int x, y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }



 

}