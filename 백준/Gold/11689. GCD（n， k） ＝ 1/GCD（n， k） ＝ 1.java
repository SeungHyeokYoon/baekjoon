import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
static long n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Long.parseLong(br.readLine());
		long pi = n;
		for(int i=2; i<= Math.sqrt(n); i++) {
			if(n%i == 0) pi = pi/i*(i-1);
			while(n%i == 0) n = n/i;
		}
		if(n != 1) pi = pi/n*(n-1);
		System.out.println(pi);
		}
}