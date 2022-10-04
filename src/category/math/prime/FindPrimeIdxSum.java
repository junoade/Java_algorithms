package category.math.prime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FindPrimeIdxSum {
    public static void solution(int[] list){
        int sum = 0;
        for(int i = 0; i < list.length; i++){
            if(isPrime(i))
                sum += list[i];
        }
        System.out.println(sum);
    }

    public static boolean isPrime(int n){
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] list = new int[T];
        for(int i = 0; i<T; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }


    }
}
