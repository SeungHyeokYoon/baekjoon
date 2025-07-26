import java.io.*;
import java.util.*;

public class bj2143 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long t = Long.parseLong(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        
        a[0] = Integer.parseInt(st.nextToken());
        for(int i=1; i<n; i++){
			a[i] = Integer.parseInt(st.nextToken()) + a[i-1];

		}

        int m = Integer.parseInt(br.readLine());
		int[] b = new int[m];
		st = new StringTokenizer(br.readLine());

        b[0] = Integer.parseInt(st.nextToken());
		for(int i=1; i<m; i++){
			b[i] = Integer.parseInt(st.nextToken()) + b[i-1];
		}

        int asize = n*(n+1)/2;
        int bsize = m*(m+1)/2;

        long[] suma = new long[asize];
        long[] sumb = new long[bsize];
        int idx=0;

        for(int i=0; i<n; i++) {
			for(int j=i; j<n; j++) {
				int av = a[j];
				if(i>0) av -= a[i-1];
				suma[idx++] = av;
			}
		}
        
		idx=0;
		for(int i=0; i<m; i++) {
			for(int j=i; j<m; j++) {
				int bv = b[j];
				if(i>0) bv -= b[i-1];
				sumb[idx++] = bv;
			}
		}

        Arrays.sort(suma);
		Arrays.sort(sumb);

        int left =0;
		int right = bsize-1;
        long cnt=0;

        while(left<asize && right>-1){
            long aa = suma[left];
            long bb = sumb[right];
            long sum = aa + bb;
            if(sum == t){
                long aaa =0, bbb =0;

                while(left<asize && aa==suma[left] ){
                    left++;
                    aaa++;
                }

                while(right>-1 && bb==sumb[right]){
                    right--;
                    bbb++;
                }
                
                cnt += aaa*bbb;
            }
            else if(sum > t){
                right--;
            }
            else{
                left++;
            }
        }

        System.out.println(cnt);
    }
}
