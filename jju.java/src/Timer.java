import java.time.LocalDateTime;//표준시간에서 가져옴
import javax.swing.JLabel;

public class Timer extends JLabel implements Runnable{
	LocalDateTime currentTime=LocalDateTime.now();
	int h1=currentTime.getHour();
	int m1=currentTime.getMinute();
	int s1=currentTime.getSecond();
	int ss1=h1*3600+m1*60+s1;
	
	int time() {//시작시간에서 현재시간을 뺀 값을 초 단위로 리턴
		LocalDateTime t=LocalDateTime.now();
		int h2=t.getHour();
		int m2=t.getMinute();
		int s2=t.getSecond();
		int ss2=h2*3600+m2*60+s2;
		ss2-=ss1;
		return ss2;
	}
	public Timer() {
		setOpaque(true);
		setBounds(10, 10, 300, 50);
		setText("");
	}
	@Override
	public void run() {
		while(true) {
			int s=time();//흘러간 시간을 초로 받아와 시분초로 바꾸는 과정
			int h=s/3600;
			s%=3600;
			int m=s/60;
			s%=60;

			setText(h+":"+m+":"+s);//폼에 출력
			System.out.printf("%02d:%02d:%02d\n",h,m,s);//잘되는지 확인용
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}