import java.io.*;
import java.util.*;

public class bj5639{

    static StringBuilder sb = new StringBuilder();

    static class node{
        int value;
        node right;
        node left;

        node(int value){
            this.value = value;
            this.right = null;
            this.left = null;
        }

        void insert(int val){
            if(val < this.value){
                if(this.left == null){
                    this.left = new node(val);
                }
                else{
                    this.left.insert(val);
                }
            }
            else{
                if(this.right == null){
                    this.right = new node(val);
                }
                else{
                    this.right.insert(val);
                }
            }
        }

        void print(){
            if(this.left != null){
                this.left.print();
            }
            if(this.right != null){
                this.right.print();
            }

            sb.append(this.value).append('\n');
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        node root = null;

        while((str = br.readLine()) != null){

            int val = Integer.parseInt(str);
            if(root == null){
                root = new node(val);
            }
            else{
                root.insert(val);
            }
        }
        
        root.print();
        System.out.println(sb);
    }

    
}