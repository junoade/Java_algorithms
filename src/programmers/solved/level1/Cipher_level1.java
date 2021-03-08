package programmers.solved.level1;

import java.util.Scanner;

public class Cipher_level1 {
	public static void main(String[] args) {
		Cipher_level1 c = new Cipher_level1();
		while(true) {
			Scanner sc=new Scanner(System.in);
			String s="";
			int n=0;
			System.out.println("입력");
			s=sc.nextLine();
			n=sc.nextInt();
			System.out.println("입력된 문자열            "+s);
			System.out.println("시저 암호화된 문자열   "+c.solution(s,n));
			
		}
		
	}
	public String solution(String s, int n) {
		int length = s.length();
		String after_ = "";
		int num = 0;
		for (int i = 0; i < length; i++) {
			if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
				if (90 < s.charAt(i) + n)
					after_ += (char) (s.charAt(i) + n - 26);
				else
					after_ += (char) (s.charAt(i) + n);

			} else if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
				if (122 < s.charAt(i) + n)
					after_ += (char) (s.charAt(i) + n - 26);
				else
					after_ += (char) (s.charAt(i) + n);
			} else {
				after_ += " ";
			}
		}
		
		return after_;
	}

}
