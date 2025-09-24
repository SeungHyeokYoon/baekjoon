import java.io.*;
import java.util.*;

public class Main {

    static int c, n, q;
    static Trie color;
    static HashSet<String> name;

    static class TrieNode {
        boolean isEnd;
        TrieNode[] next;

        TrieNode() {
            next = new TrieNode[26];
            isEnd = false;
        }
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String str) {
            int length = str.length();
            TrieNode now = root;

            for (int i = 0; i < length; i++) {
                char c = str.charAt(i);
                int idx = c - 'a';
                if (now.next[idx] == null) {
                    now.next[idx] = new TrieNode();
                }
                now = now.next[idx];
            }
            now.isEnd = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        color = new Trie();
        name = new HashSet<>();

        while (c-- > 0) {
            color.insert(br.readLine());
        }

        while (n-- > 0) {
            name.add(br.readLine());
        }

        q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            String str = br.readLine();
            int strlen = str.length();
            int idx = 0;
            TrieNode colNode = color.root;

            boolean can = true;
            while (true) {
                char c = str.charAt(idx);
                int cidx = c - 'a';

                if (colNode.next[cidx] == null) {
                    can = false;
                    break;
                }

                colNode = colNode.next[cidx];
                idx++;
                if (idx == strlen) {
                    can = false;
                    break;
                }

                if (colNode.isEnd) {
                    if (name.contains(str.substring(idx)))
                        break;
                }
            }

            if (can)
                sb.append("Yes\n");
            else
                sb.append("No\n");
        }

        System.out.println(sb);
    }
}
