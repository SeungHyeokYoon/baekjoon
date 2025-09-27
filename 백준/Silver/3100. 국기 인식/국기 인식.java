import java.io.*;

public class Main {
    static final int H = 6, W = 9;
    static char[][] grid = new char[H][W];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        

        for (int i = 0; i < H; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < W; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        
        int horizontal = solveHorizontal();
        int vertical = solveVertical();
        
        System.out.println(Math.min(horizontal, vertical));
    }
    

    static int solveHorizontal() {
        int[] topChanges = calcCostRows(0, 1);
        int[] midChanges = calcCostRows(2, 3);
        int[] botChanges = calcCostRows(4, 5);
        
        int ans = Integer.MAX_VALUE;
        for (int t = 0; t < 26; t++) {
            for (int m = 0; m < 26; m++) {
                if (m == t) continue;
                for (int b = 0; b < 26; b++) {
                    if (m == b) continue;
                    ans = Math.min(ans, topChanges[t] + midChanges[m] + botChanges[b]);
                }
            }
        }
        return ans;
    }
    

    static int solveVertical() {
        int[] leftChanges = calcCostCols(0, 2);
        int[] midChanges  = calcCostCols(3, 5);
        int[] rightChanges = calcCostCols(6, 8);
        
        int ans = Integer.MAX_VALUE;
        for (int l = 0; l < 26; l++) {
            for (int m = 0; m < 26; m++) {
                if (m == l) continue;
                for (int r = 0; r < 26; r++) {
                    if (m == r) continue;
                    ans = Math.min(ans, leftChanges[l] + midChanges[m] + rightChanges[r]);
                }
            }
        }
        return ans;
    }
    

    static int[] calcCostRows(int start, int end) {
        int[] count = new int[26];
        int total = (end - start + 1) * W;
        
        for (int i = start; i <= end; i++) {
            for (int j = 0; j < W; j++) {
                count[grid[i][j] - 'A']++;
            }
        }
        
        int[] res = new int[26];
        for (int c = 0; c < 26; c++) {
            res[c] = total - count[c];
        }
        return res;
    }
    

    static int[] calcCostCols(int start, int end) {
        int[] count = new int[26];
        int total = (end - start + 1) * H;
        
        for (int j = start; j <= end; j++) {
            for (int i = 0; i < H; i++) {
                count[grid[i][j] - 'A']++;
            }
        }
        
        int[] res = new int[26];
        for (int c = 0; c < 26; c++) {
            res[c] = total - count[c];
        }
        return res;
    }
}
