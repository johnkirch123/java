package arrays;

public class arrays {

	public static void main(String[] args) {
		int john[]={3,4,5,6,7};
		int johnny[]={8,9,10,11,12,13};
		int total = 0;
		change(john);
		
		for(int y:john){
			System.out.println(y);
		}
		for (int x:johnny){
			total+=x;
		}
		System.out.println(total);
	}
	public static void change(int x[]){
		for(int counter=0; counter<x.length;counter++){
			x[counter]+=5;
		}
	}
}
