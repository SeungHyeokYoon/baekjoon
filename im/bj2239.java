import java.io.*;
import java.util.*;

public class bj2239 {

    static int[][] sudoku = new int[10][10];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i<=9; i++){
            String str = br.readLine();
            for(int j = 1; j<=9; j++){
                sudoku[i][j] = str.charAt(j-1) - '0';
            }
        }

        findandinput();

        for(int i = 1; i<=9; i++){
            for(int j = 1; j<=9; j++){
                sb.append(sudoku[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }

    static boolean findandinput(){
        
        for(int i = 1; i<=9; i++){
            for(int j = 1; j<=9; j++){
                if(sudoku[i][j] == 0){
                    for(int k = 1; k<=9; k++){
                        if(checkhorizontal(i, k) && checkvertical(j, k) && checksquare(j, i, k)){
                            sudoku[i][j] = k;
                            if(findandinput()){
                                return true;
                            }
                            sudoku[i][j] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    static boolean checkvertical(int x, int num){
        for(int i = 1; i<=9; i++){
            if(sudoku[i][x] == num){
                return false;
            }
        }

        return true;
    }

    static boolean checkhorizontal(int y, int num){
        for(int i = 1; i<=9; i++){
            if(sudoku[y][i] == num){
                return false;
            }
        }

        return true;
    }

    static boolean checksquare(int x, int y, int num){
        int startx = x - ((x-1)%3);
        int starty = y - ((y-1)%3);

        for(int i = startx; i<startx+3; i++){
            for(int j = starty; j<starty+3; j++){
                if(sudoku[j][i] == num){
                    return false;
                }

            }
        }

        return true;
    }
}
