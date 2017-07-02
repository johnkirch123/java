package time;
import java.util.Random;

public class mtime {
	private int hour;
	private int minute;
	private int second;
	Random rand = new Random();
	Random rand1 = new Random();
	Random rand2 = new Random();
	
	
	public void setTime(int h, int m, int s){
		hour = 1 + rand.nextInt(24);
		minute = 1 + rand1.nextInt(60);
		second = 1 + rand2.nextInt(60);
	}
	
	public String toMilitary(){
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
	
	public String toStandard(){
		return String.format("%d:%02d:%02d %s", ((hour==0||hour==12)?12:hour%12), minute, second, (hour < 12? "AM": "PM"));
	}
}
