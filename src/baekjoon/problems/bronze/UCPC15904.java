package baekjoon.problems.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UCPC15904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if(checkString(br.readLine())){
            System.out.println("I love UCPC");
        }else{
            System.out.println("I hate UCPC");
        }

    }
    static boolean checkString(String str){
        String[] temp = str.split(" ");
        char[] answer ={'U', 'C','P','C'};
        int ans_idx=0;
        char check = answer[ans_idx];
        boolean result=false;
        int U=0, C=0,P=0;
        for(int i=0; i<temp.length; i++){
            for(int j=0;j<temp[i].length();j++){
                /*switch(temp[i].charAt(j)){
                    case 'U' :
                        U=1;
                        break;
                    case 'C':
                        C=1;
                        break;
                    case 'P':
                        P=1;
                        break;
                }*/
                if(check==temp[i].charAt(j)){
                    if(ans_idx<3) {
                        ans_idx++;
                        check = answer[ans_idx];
                    }else if(ans_idx == 3){
                        ans_idx++;
                    }
                }
            }
        }
        if(ans_idx==4){
            result=true;
        }


        /*int compare =U+C+P;
        if(compare==4){
            result=true;
        }else{
            result=false;
        }*/
        return result;
    }

}
