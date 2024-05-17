package ch0510;

class PlusK {
	public int plus(int a, int b) {
		return a + b;
	}
	
	public void ex() {
		int output = 0;
		for(int i = 1; i<= 10; i++) {
			if(i%2 == 1) {
				continue;
			}
			output += i;
			System.out.println(output);
		}
	}
	
	public void ex2() {
		int num = 0;
		for(int i = 1; i <= 10; i++) {
			System.out.println(i);
		}
	}
}


public class Exam {

	public static void main(String[] args) {
		PlusK m  = new PlusK();
		int k1 = m.plus(7, 3);
//		System.out.println(k1);
		
		m.ex();
	}

}
