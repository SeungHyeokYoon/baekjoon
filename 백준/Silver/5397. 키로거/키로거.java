import java.io.*;
import java.util.*;

public class Main{

    static class Node{
        Node prev;
        Node next;
        char c;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            String str = br.readLine();

            Node first = new Node();
            Node last = new Node();

            first.next = last;
            last.prev = first;
            Node cursor = first;

            for(int i = 0; i<str.length(); i++){
                char c = str.charAt(i);

                if(c == '<'){
                    if(cursor != first){
                        cursor = cursor.prev;
                    } 
                }
                else if(c == '>'){
                    if(cursor.next != last){
                        cursor = cursor.next;
                    }
                }
                else if(c == '-'){
                    if(cursor != first){
                        cursor.prev.next = cursor.next;
                        cursor.next.prev = cursor.prev;
                        cursor = cursor.prev;
                    }
                }
                else{
                    Node node = new Node();
                    node.c = c;
                    node.next = cursor.next;
                    node.prev = cursor;

                    cursor.next.prev = node;
                    cursor.next = node;

                    cursor = node;
                }

            }

            cursor = first.next;
            while(cursor != last){
                sb.append(cursor.c);
                cursor = cursor.next;
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

}