package category.set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Programmers 월간 코드 챌린지, 없는 숫자 더하기
 *
 * ### 내가 푼 방식 : Set 구현체에 입력으로 받은 numbers를 넣음
 * 1. 순서가 중요하지 않았고, 반복되는 숫자가 없었음
 * 2. Set.contains() 을 통해 O(1) 시간 복잡도로 조회가 가능했기 때문
 * 3. 그 다음 0~9 범위의 숫자를 순회하며, 없는 숫자를 찾아 합을 구했음
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
