package daily.y2026;

public class Leet_BuyTickets {

    /**
     * <a href="https://leetcode.com/problems/time-needed-to-buy-tickets/description/">문제 링크</a>
     * @param tickets
     * @param k
     * @return
     */
    // 메모리 O(1), 시간복잡도 O(m*N); m: 요소들의 합
    public int timeRequiredToBuy(int[] tickets, int k) {
        final int L = tickets.length;
        int time = 0, cursor = 0;
        // k 번째 index 인 사람인 경우, 티켓 차감 후 0인지 체크하고 0 이면 종료.
        while(true) {
            int idx = cursor++ % L; // 0-based
            if(tickets[idx] <= 0) {
                continue;
            }

            tickets[idx]--;
            time++;
            if(idx == k && tickets[idx] == 0) {
                break;
            }
        }

        return time;
    }

    public static void main(String[] args) {
        Leet_BuyTickets obj = new Leet_BuyTickets();
        int answer = obj.timeRequiredToBuy(new int[]{5, 1, 1, 1}, 0);
        System.out.println(answer);
    }
}
