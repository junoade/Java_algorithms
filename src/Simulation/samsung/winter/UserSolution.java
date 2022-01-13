package Simulation.samsung.winter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class UserSolution {
    /* 미체결 매수, 매도 주식을 관리할 DS */
    ArrayList<Stocks> stocks;
    static final int STOCK_SIZE = 5;
    public void init() {
        /* 메모리 할당 */

        stocks = new ArrayList<>(STOCK_SIZE);
        for(int i = 0; i<STOCK_SIZE; i++){
            Stocks temp  = new Stocks();
            /* 미체결 매수 queue에서 매수 가격이 높으면 우선순위 높게 (내림차순) */
            /* 가격이 같으면, 매수 번호 낮은 것이 우선순위 높게 (오름차순) */
            temp.buy_queue = new PriorityQueue<>(
                    (o1, o2) -> o1.mPrice < o2.mPrice ? 1 :
                            (o1.mPrice == o2.mPrice && o1.mNumber < o2.mNumber) ? 1 : -1
            );
            /* 미체결 매도 queue에서 매도 가격이 낮으면 우선순위가 높게 (오름차순)*/
            temp.sell_queue = new PriorityQueue<>(
                    (o1, o2) -> o1.mPrice > o2.mPrice ? 1 :
                            (o1.mPrice == o2.mPrice && o1.mNumber < o2.mNumber) ? 1 : -1
            );
            stocks.add(temp);
        }
    }

    /* 주문 번호가 mNumber인 매수 주문 처리
     * mStock : 주식 종목
     * mQuantity : 주문 수량
     * mPrice : 매수 희망 가격
     * */
    public int buy(int mNumber, int mStock, int mQuantity, int mPrice) {
        Stock temp = new Stock(mNumber, mQuantity, mPrice);
        AtomicInteger result = new AtomicInteger();
        /* 호가창에 미체결 매도가 없으면 그냥 미체결 매수에 계속 추가 */
        if (stocks.get(mStock).sell_queue.isEmpty()) {
            stocks.get(mStock).buy_queue.add(temp);
        }else {
            /* 1st try) 일단 호가창에 넣고 체결해주자 */
            if(stocks.get(mStock).buy_queue.isEmpty()){
                stocks.get(mStock).buy_queue.add(temp);
            }
            /* 이외엔 체결 검증 필요  */
            int cursor_price = stocks.get(mStock).sell_queue.peek().mPrice;

            if (cursor_price <= mPrice) {
                Stock cursor = stocks.get(mStock).sell_queue.peek();
                assert cursor != null;

                while (true) {
                    if (cursor.mQuantity >= mQuantity) {
                        cursor.mQuantity -= mQuantity;
                        /* 체결 이후에도 주문량이 남아있다면*/
                        temp.mQuantity = cursor.mQuantity;
                        if(temp.mQuantity != 0)
                            stocks.get(mStock).buy_queue.add(temp);
                        break;
                    }else{ // cursor.mQuantity < mQuantity
                        /* 반복 필요 */
                        stocks.get(mStock).sell_queue.remove();
                    }
                }

            }else{
                stocks.get(mStock).buy_queue.add(temp);
            }
        }
        
        stocks.get(mStock).buy_queue.stream().filter(t->t.mNumber == mNumber).findAny().ifPresent(s->{
            result.set(s.mQuantity);
        });


        return result.get();
    }

    public int sell(int mNumber, int mStock, int mQuantity, int mPrice) {
        Stock temp = new Stock(mNumber, mQuantity, mPrice);
        
        /* 호가창에 미체결 매수가 없으면 그냥 미체결 매도에 계속 추가 */
        if(stocks.get(mStock).buy_queue.isEmpty()){
            stocks.get(mStock).sell_queue.add(temp);
        }
        else{

            /* 1st try) 일단 호가창에 넣고 체결해주자 */
            if(stocks.get(mStock).sell_queue.isEmpty()){
                stocks.get(mStock).sell_queue.add(temp);
            }
            /* 이외엔 체결 검증 필요 */
            int cursor_price = stocks.get(mStock).buy_queue.peek().mPrice; // 현재 최대 매수가

            if(cursor_price >= mPrice){  // 현재 최대 매수가 >= 내 매도가 시 체결
                Stock cursor = stocks.get(mStock).sell_queue.peek();
                assert cursor != null;

                while (true) {
                    if (cursor.mQuantity >= mQuantity) {
                        cursor.mQuantity -= mQuantity;
                        /* 체결 이후에도 주문량이 남아있다면*/
                        temp.mQuantity = cursor.mQuantity;
                        if(temp.mQuantity != 0)
                            stocks.get(mStock).sell_queue.add(temp);
                        break;
                    }else{ // cursor.mQuantity < mQuantity
                        /* 반복 필요 */
                        stocks.get(mStock).buy_queue.remove();
                    }
                }

            }else{
                stocks.get(mStock).buy_queue.add(temp);
            }
        }

        return 0;
    }

    public void cancel(int mNumber) {
        for(Stocks stock : stocks){
            if(stock.buy_queue.removeIf(temp -> temp.mNumber == mNumber)){
                break;
            }
            if(stock.sell_queue.removeIf(temp -> temp.mNumber == mNumber)){
                break;
            }
        }
    }

    /***
     * (mStock 주식의 임의의 시점(B)에서의 체결 가격 – mStock 주식의 임의의 시점(A)에서의 체결 가격) 의 최댓값을 반환한다.
     * 단, 시점(B)는 bestProfit 함수 호출 시점 이전이어야 하며, 시점(A)는 시점(B) 이전이어야 한다.
     * ‘시점(B) 이전’은 ‘시점(B)’를 포함한다.
     * buy 혹은 sell 함수가 한 번만 호출되었을 때에도, 여러 번의 거래 체결이 생길 수 있음에 유의하라.
     * 이 경우, 어떤 거래 체결이 더 이른 시점에 발생하는 지는 각 함수의 설명을 참조하라.
     * mStock 주식의 거래 체결이 한 번 이상 있었음이 보장된다.
     * @param mStock : 주식 종목 (1 ≤ mStock ≤ 5)
     * @return (mStock 주식의 임의의 시점(B)에서의 체결 가격 – mStock 주식의 임의의 시점(A)에서의 체결 가격) 의 최댓값
     */
    public int bestProfit(int mStock) {
        return 0;
    }
}

class Stocks {
    int mStock;
    PriorityQueue<Stock> buy_queue;
    PriorityQueue<Stock> sell_queue;
}

class Stock {
    int mNumber;
    int mQuantity;
    int mPrice;

    public Stock(int mNumber, int mQuantity, int mPrice) {
        this.mNumber = mNumber;
        this.mQuantity = mQuantity;
        this.mPrice = mPrice;
    }
}
