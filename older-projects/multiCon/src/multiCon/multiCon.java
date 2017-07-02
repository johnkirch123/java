package multiCon;

public class multiCon {

	public static void main(String[] args) {
		secClass secClassObject = new secClass();
		secClass secClassObject1 = new secClass(5);
		secClass secClassObject2 = new secClass(5,13);
		secClass secClassObject3 = new secClass(5,13,43);
		
		otClass otClassObject = new otClass(4,5,6);
		
		System.out.printf("%s\n", secClassObject.toMilitary());
		System.out.printf("%s\n", secClassObject1.toMilitary());
		System.out.printf("%s\n", secClassObject2.toMilitary());
		System.out.printf("%s\n", secClassObject3 .toMilitary());

	}

}
