package lecture.week2.sorts;

import java.io.*;

/**
 * 과제를 수행함에 있어서
 */
public class MyArray {

    final static int weight = 1000000;
    /*static int[] arr = new int[N];*/

    /* 1~1000000 범위의 랜덤숫자를 배열값으로 할당하는 static 메소드*/
    static int[] getArray(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * weight) + 1;
        }
        /*for(int i: arr)
            i=(int)(Math.random()*weight)+1; 값은 직접 바꿀수 없어*/
        return arr;
    }

    /*배열을 입력받아 정해진 format으로 출력하는 static 메소드 */
    static void printArray(int[] a) {
        for (int i : a)
            System.out.print(i + " ");
        System.out.println();
    }

    /*non-Recursive 방법으로 배열 하고 동일한 배열에 대해 Recursive 방법을 사용하도록 깊은 복사를 하는 static 메소드 구현*/
    static int[] deepCopyArray(int[] origin) {
        if (origin == null)
            return null;
        int[] result = new int[origin.length]; //새 배열공간을 메모리에 할당하고,
        result = origin.clone();
        return result;
    }

    /* 결과값을 확인하기 위해, 파일 출력을 하는 static 메소드 구현 */
    static void printOutput(String filePath, int[] a) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        String line = "";
        /*0부터 N의 크기까지 인덱스 번호를 파일에 출력하도록*/
        for(int i=0; i<a.length; i++)
            writer.write(Integer.toString(i)+" ");
        writer.newLine();
        writer.newLine();//좀더 확실하게 구분하기 위해서
        /*인자로 받은 배열의 원소들을 파일로 출력하도록*/
        for (int i : a)
            line += Integer.toString(i) + " ";
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    /* 사용하는 클래스에서 PrintWriter 스트림을 여는 용도의 staic 메소드*/
    static PrintWriter openPrintWriter (String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        PrintWriter pw = new PrintWriter(writer);
        return pw;
    }

    /* 그래프를 그리기 위해 걸린 시간을 파일에 출력하는 static 메소드 구현 */
    /*static void writeTimeResult(String filePath, double time,int count) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        PrintWriter pw = new PrintWriter(writer); // 이어쓰기 위해서.
        *//*pw.write(count+" "+time+"\n");*//*
        pw.printf("%d %f\n",count, time);
        pw.flush();
        if(count==4)
            pw.close();
    }*/
}
