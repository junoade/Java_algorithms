package category.set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Programmers
 */
public class AddNotExistNum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(",");
        int[] numbers = new int[inputs.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(inputs[i]);
        }
        System.out.println(solution(numbers));
    }

    public static int solution(int[] numbers) {
        int result = 0;

        /* 주어진 numbers 배열에 대해 Set을 만듬*/
        Set<Integer> set = new HashSet<>();
        for (int num : numbers) {
            set.add(num);
        }

        /**
         * Set<Integer> set = new HashSet<>(Arrays.asList(numbers));
         * Primitive 타입인 int[] 를 바로 이렇게 주면, 의도한대로 안됨. 조심하자
         * Arrays.asList() 는 Referenced Type에 대해서만,
         * Set<Integer> set2 = Arrays.stream(numbers).boxed().collect(Collectors.toSet());
         *
         * https://stackoverflow.com/questions/12030661/java-int-array-to-hashsetinteger
         */


        /* 0 ~ 9 까지 없는 숫자들을 찾아서, 더함 O(1) */
        for (int i = 0; i < 10; i++) {
            if (!set.contains(i)) {
                result += i;
            }
        }


        return result;
    }
}
