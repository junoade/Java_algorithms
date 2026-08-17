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
            if(tickets[cursor] > 0) {
                tickets[cursor]--;
                time++;
            }

            if(cursor == k && tickets[cursor] == 0) {
                break;
            }

            cursor = (cursor + 1) % L;
        }

        return time;
    }


    // closed form. 각 단계 비용이 균일, 종료 시점이 미리 결정(tickets[k] 라운드로)
    public int mathSolution(int[] tickets, int k) {
        int time = 0;
        for (int i = 0; i < tickets.length; i++) {
            // min(내가 사고 싶은 양, 내게 주어진 기회 횟수)
            // i <= k, k보다 앞이니, 마지막 라운드에서도 내차례가 온다. 기회 tickets[k]
            // i > k, k가 뒤에 위치. 마지막 라운드는 내 앞에서 끝난다. 기회 tickets[k] - 1
            time += (i <= k) ? Math.min(tickets[i], tickets[k])
                             : Math.min(tickets[i], tickets[k] - 1);
        }
        return time;
    }

    public static void main(String[] args) {
        Leet_BuyTickets obj = new Leet_BuyTickets();
        int answer = obj.timeRequiredToBuy(new int[]{5, 1, 1, 1}, 0);
        System.out.println(answer);
    }
}
