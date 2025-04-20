import java.io.*;
import java.util.*;

public class bj2263 {

    static int n;
    static int[] inorder, postorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        inorder = new int[n];
        for(int i = 0; i<n; i++){
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        postorder = new int[n];
        for(int i = 0; i<n; i++){
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        preorder(0, n-1, 0, n-1);
        System.out.println(sb);
    }

    static void preorder(int inorderLeft, int inorderRight, int postorderLeft, int postorderRight){
        if(inorderLeft > inorderRight || postorderLeft > postorderRight) return;

        int root = postorder[postorderRight];
        int rootidx = searchIdx(root, inorderLeft, inorderRight);
        int leftTreeSize = rootidx - inorderLeft;
        sb.append(root).append(' ');

        preorder(inorderLeft, rootidx-1, postorderLeft, postorderLeft+leftTreeSize-1);
        preorder(rootidx+1, inorderRight, postorderLeft+leftTreeSize, postorderRight-1);


    }

    static int searchIdx(int x, int left, int right){
        for(int i = left; i<=right; i++){
            if(inorder[i] == x){
                return i;
            }
        }
        return -1;
    }




}
