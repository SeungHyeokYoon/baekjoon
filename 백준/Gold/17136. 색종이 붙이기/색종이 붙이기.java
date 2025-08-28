import java.io.*;
import java.util.*;

public class Main {

    static int[][] paper = new int[10][10];
    static int[] paperCount = {0, 5, 5, 5, 5, 5};
    static int min = Integer.MAX_VALUE;
    static Stack<int[]> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

    }

    static void dfs(int y){
        if(y == 10){
            min = Math.min(min, stack.size());
            return;
        }

        for(int x = 0; x<10; x++){
            if(paper[y][x] == 1){
                for(int size = 5; size>0; size--){
                    if(paperCount[size] > 0 && isAvailable(x, y, size)){
                        for(int i = y; i<y+size; i++){
                            for(int j = x; j<x+size; j++){
                                paper[i][j] = 0;
                            }
                        }
                        paperCount[size]--;
                        stack.push(new int[]{x, y, size});
                        dfs(y);

                        int[] last = stack.pop();
                        for(int i = last[1]; i<last[1]+last[2]; i++){
                            for(int j = last[0]; j<last[0]+last[2]; j++){
                                paper[i][j] = 1;
                            }
                        }
                        paperCount[size]++;
                    }
                }
                return;
            }
        }

        dfs(y+1);


    }

    static boolean isAvailable(int x, int y, int size) {
        if(x + size > 10 || y + size > 10) return false;

        for(int i = y; i<y+size; i++){
            for(int j = x; j<x+size; j++){
                if(paper[i][j] == 0) return false;
            }
        }
        return true;
    }

}
