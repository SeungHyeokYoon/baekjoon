import java.io.*;
import java.util.*;

public class swea23793{
    
    static int N;
    static long[] CNT;
    static ArrayList<ArrayList<Byte>> MAP;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());
            CNT = new long[3];
            MAP = new ArrayList<>();

            MAP.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                byte val = Byte.parseByte(st.nextToken());
                MAP.get(0).add(val);
                CNT[val]++;
            }

            for (int i = 1; i < N; i++) {
                byte val = Byte.parseByte(br.readLine());
                ArrayList<Byte> row = new ArrayList<>();
                row.add(val);
                CNT[val]++;
                MAP.add(row);
            }
        }

    }
    
}