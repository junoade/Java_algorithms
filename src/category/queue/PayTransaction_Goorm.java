package category.queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * --------------------------------------------------------------<br/>
 * <b> 구름 4주차 1번 체크카드 문제 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * - 예약 조건 중 결제하는 부분, 예약하는 부분 판별 잘하기
 * - 돈이 충분한 경우 예약하더라도 바로 결제 가능하므로,
 * - 돈이 들어온 경우 반복해서 예약 결제를 처리 가능한지 확인 후 수행
 * --------------------------------------------------------------<br/>
 * <b> 채점 15/15 </b>
 * --------------------------------------------------------------
 */
public class PayTransaction_Goorm {
    static int totalMoney;
    static Queue<Integer> payReserves;

    static void transaction(String cmd, int k) {
        switch (cmd) {
            case "deposit":
                deposit(k);
                payReservation();
                // System.out.println("[DEBUG] deposit then " + totalMoney);
                break;
            case "reservation":
                reservation(k);
                // System.out.println("[DEBUG] pay or reserve then " + totalMoney);
                break;
            case "pay":
                pay(k);
                // System.out.println("[DEBUG] pay then " + totalMoney);
                break;
        }
        // payReservation(); // 현재 reservation에서 pay가 가능하면 바로 pay하므로 여기선 필요 없다.
    }

    static boolean hasEnoughMoney(int k) {
        return (totalMoney - k) >= 0;
    }

    static void deposit(int k) {
        totalMoney += k;
    }

    static boolean pay(int k) {
        if (hasEnoughMoney(k)) {
            totalMoney -= k;
            return true;
        }
        return false;
    }
    /* Queue 인터페이스 특징 */
    // add()/remove() 예외를 던짐
    // offer()/poll() : null 반환
    static void reservation(int k) {
        if (hasEnoughMoney(k) && payReserves.isEmpty()) { // 바로 결제 가능한 조건
            pay(k);
        } else {
            payReserves.offer(k);
        }
    }

    static void payReservation() {
        while (!payReserves.isEmpty()) {
            int peekNextPay = payReserves.peek();
            if (pay(peekNextPay)) payReserves.poll();
            else break;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        payReserves = new LinkedList<>();
        totalMoney = Integer.parseInt(inputs[0]);
        int T = Integer.parseInt(inputs[1]);

        for (int i = 0; i < T; i++) {
            inputs = br.readLine().split(" ");
            String cmd = inputs[0];
            int k = Integer.parseInt(inputs[1]);
            transaction(cmd, k);
        }
        System.out.println(totalMoney);
    }
}
