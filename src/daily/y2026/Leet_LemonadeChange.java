package daily.y2026;

public class Leet_LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int price = 5;
        int sum = 0;

        // 지폐 종류를 고려해서 잔돈 줘야함
        int[] kinds = {5, 10};
        int[] billCnts = {0, 0};

        for (int pay : bills) {
            int exchange = pay - price;
            // 반환해줘야하는 돈 만큼 지페가 있는지.
            for (int i = 1; i >= 0; i--) {
                if (exchange == 0) break;
                if (billCnts[i] == 0 || kinds[i] > exchange) {
                    continue;
                }

                int use = Math.min(billCnts[i], exchange / kinds[i]);
                exchange -= use * kinds[i];
                billCnts[i] -= use;
            }

            if (exchange != 0) return false; // 잔돈 못 맞춘 경우

            if (pay == 5) {
                billCnts[0]++;
            } else if (pay == 10) {
                billCnts[1]++;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Leet_LemonadeChange leet = new Leet_LemonadeChange();
        System.out.println(leet.lemonadeChange(new int[]{5, 5, 5, 20, 10, 10}));
    }
}
