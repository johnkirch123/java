package time;

public class time {

	public static void main(String[] args) {
		mtime mtimeObject = new mtime();
		System.out.println(mtimeObject.toMilitary());
		System.out.println(mtimeObject.toStandard());
		mtimeObject.setTime(13, 27, 6);
		System.out.println(mtimeObject.toMilitary());
		System.out.println(mtimeObject.toStandard());

	}

}
