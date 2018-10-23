package myTrain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class formatTime {
private void mian() {
	// TODO Auto-generated method stub
	 SimpleDateFormat  format1 = new SimpleDateFormat("yyyy-MM-dd"); 
	 DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd"); 
	 String value="2018-09-12";
	 System.out.println(format1.format(value));
	 System.out.println(format2.format(value));
}
}
