package hackerRank.pathfinder;

import java.util.Date;

public class test {
    public static void main(String[] args) {

        String bk ="2021-11-02 00:00:00";
        String bk2 = "2021-10-30 00:00:00";

        String temp1 = bk.substring(0,4)+bk.substring(5,7)+bk.substring(8,10)+bk.substring(11,13)
                +bk.substring(14,16)+bk.substring(17,19);
        long l_date = Long.parseLong(temp1);

        String temp2 = bk2.substring(0,4)+bk2.substring(5,7)+bk2.substring(8,10)+bk2.substring(11,13)
                +bk2.substring(14,16)+bk2.substring(17,19);
        long r_date = Long.parseLong(temp2);
        /*대출일자 lDate가 rDate보다 이후?*/
        boolean isLdateAfterRdate =  (l_date - r_date) >= 0;

        System.out.println(isLdateAfterRdate);

    }
}
