package test;

public class vla {

	public static void main(String[] args) {
		System.out.println(average(43,53,56,76,8,91,23));

	}

	public static int average(int...numbers){
		int total=0;
		for(int x:numbers)
			total+=x;
		
		return total/numbers.length;
	}
}
