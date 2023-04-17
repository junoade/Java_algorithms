package category.bruteforce;

public class P_HIndex {
    // h = 1000부터 가능한 모든 경우를 탐색
    public int solution(int[] citations) {
        final int L = citations.length;

        // 1000 * 1000
        for(int h = L; h >= 1; h--) {
            if(isValid(citations, h)){
                return h;
            }
        }

        return 0;
    }

    private boolean isValid(int[] arr, int h) {
        int count = 0;
        // O(N), N = 1000
        for(int e : arr) {
            if(e >= h) {
                count++;
            }
        }
        return count >= h;
    }
}
