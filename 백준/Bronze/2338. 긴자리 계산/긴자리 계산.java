import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BigInteger A = new BigInteger(br.readLine().trim());
        BigInteger B = new BigInteger(br.readLine().trim());

        System.out.println(A.add(B));      // A + B
        System.out.println(A.subtract(B)); // A - B
        System.out.println(A.multiply(B)); // A * B
    }
}