package daily.y2026;

public class Leet_FlowerBeds {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        final int N = flowerbed.length;

        for(int i = 0; i < N; i++) {
            boolean leftEmpty = (i == 0 || flowerbed[i - 1] == 0);
            boolean rightEmpty = (i == N - 1 || flowerbed[i + 1] == 0);

            if(flowerbed[i] == 0 && leftEmpty && rightEmpty) {
                flowerbed[i] = 1;
                n--;
            }
        }

        return n <= 0;
    }
}
