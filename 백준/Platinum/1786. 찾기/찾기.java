import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[] pi = preprocessing(str2);

        ArrayList<Integer> ans = new ArrayList<>();

        int i = 0, j = 0;
        int size = str1.length();
        int goal = str2.length();

        while (i < size) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = pi[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            i++;

            if (j == goal) {
                ans.add(i - goal + 1); // 1-based index
                j = pi[j - 1];
            }
        }

        sb.append(ans.size()).append("\n");
        for (Integer a : ans) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }

    static int[] preprocessing(String str) {
        int size = str.length();
        int[] arr = new int[size];
        int idx = 0;

        for (int i = 1; i < size; i++) {
            while (idx > 0 && str.charAt(i) != str.charAt(idx)) {
                idx = arr[idx - 1];
            }
            if (str.charAt(i) == str.charAt(idx)) {
                idx++;
                arr[i] = idx;
            }
        }
        return arr;
    }
}
