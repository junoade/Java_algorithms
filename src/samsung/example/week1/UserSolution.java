package samsung.example.week1;

class UserSolution {
    public final static int N = 4;
    public static long next = 1;

    public void doUserImplementation(int guess[]) {
        // Implement a user's implementation function
        //
        // The array of guess[] is a return array that
        // is your guess for what digits[] would be.

        // user가 처음 비밀번호 추측
        int length = guess.length;

        guess = mkrand(guess);
        Solution.Result result = Solution.query(guess);

        do {
            // miss 가 있다면, 시스템 비밀번호와 user의 처음 비밀번호 각 자릿수를 swap 한다
            // hit 이 있다면, 해당 인덱스를 바뀌지 않도록 한다.
            int next_hit=0;
            int next_miss=0;
            if(result.miss==0 && result.hit ==0){
                guess = mkrand(guess);
            }else if(result.miss>0){

            }else if(result.hit>0){

            }


            printArr(guess);
            //result = Solution.query(guess);
        }while(result.hit != guess.length);
    }

    public int myrand(int length) {
        int temp = 0;
        next = next * 1103515245 + 12345;
        temp = (int) (next / 65536) % length;
        if (temp < 0)
            temp *= -1;
        return temp;
    }

    public int[] mkrand(int[] arr){ // 초기 비밀번호 추측하기
        for (int i = 0; i < arr.length; i++) {
            arr[i] = myrand(10);
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    i--; // 큰놈 다시 뽑아주기
                }
            }
        }
        return arr;
    }

    public void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }
}