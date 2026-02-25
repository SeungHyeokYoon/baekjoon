    import java.io.*;
    import java.util.*;

    public class Main{

        static class Tong{
            boolean type; // false is character and true is number
            char c;
            String num;    
            int zerocnt;
        }


        static class Window implements Comparable<Window>{
            String str;
            ArrayList<Tong> arr;

            Window(String str){
                arr = new ArrayList<>();
                this.str = str;

                int idx = 0;
                int size = str.length();
                while(idx<size){
                    char c = str.charAt(idx);
                    Tong now = new Tong();

                    if(c >= 'A'){
                        now.c = c;
                        now.type = false;
                        arr.add(now);
                        idx++;
                    }
                    else{
                        int nidx = idx;
                        boolean flag = true;
                        while(nidx < size && str.charAt(nidx) <= '9'){
                            if(flag && str.charAt(nidx) == '0'){
                                now.zerocnt++;
                                idx++;
                            }
                            else{
                                flag = false;
                            }
                            nidx++;
                        }

                        now.num = str.substring(idx, nidx);
                        now.type = true;
                        arr.add(now);
                        idx = nidx;
                    }
                }


            }

            @Override
            public int compareTo(Window o){
                int idx = 0;
                int size1 = this.arr.size();
                int size2 = o.arr.size();
                
                while(idx < size1 && idx < size2){
                    Tong now1 = this.arr.get(idx);
                    Tong now2 = o.arr.get(idx);
                    idx++;

                    if(now1.type){ // this is num
                        if(now2.type){

                            String nnow1 = now1.num;
                            String nnow2 = now2.num;

                            while(nnow1.length() != nnow2.length()){
                                if(nnow1.length() > nnow2.length()) nnow2 = "0" + nnow2;
                                else nnow1 = "0" + nnow1;
                            }

                            if(nnow1.equals(nnow2)){
                                if(now1.zerocnt == now2.zerocnt) continue;
                                return now1.zerocnt - now2.zerocnt;
                            }
                            else{
                                return nnow1.compareTo(nnow2);
                            }
                        }
                        else{
                            return -1;
                        }
                    }
                    else{ // now1 is character
                        if(now2.type){
                            return 1;
                        }
                        else{
                            if(now1.c == now2.c) continue;
                            char aa = Character.toLowerCase(now1.c);
                            char bb = Character.toLowerCase(now2.c);

                            if(aa == bb){
                                return now1.c - now2.c;
                            }
                            else{
                                return aa - bb;
                            }

                        }
                    }

                }

                if(size1 > idx) return 1;
                else if(size2 > idx) return -1;
                else return 0;
            }

        }


        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();

            int n = Integer.parseInt(br.readLine());

            Window[] array = new Window[n];
            for(int i = 0; i<n; i++){
                array[i] = new Window(br.readLine());
            }

            Arrays.sort(array);

            for(Window now : array){
                sb.append(now.str).append("\n");
            }

            System.out.println(sb);
            
        }
    }