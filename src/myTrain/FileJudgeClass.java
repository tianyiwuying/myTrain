package myTrain;

import java.io.File;
import java.util.Date;

public class FileJudgeClass {
public static void main(String[] args) 
{
	String file="C:/Users/user/Desktop/茶坞";
	File[] fs=(new File(file)).listFiles();
	Date d=new Date();
	//删除6小时前的数据
	for(File f:fs)
	{
		System.out.println(f.getName()+"---"+f.lastModified());
		System.out.println(d.getTime());
		/*if(f.lastModified()<(d.getTime()-21600000))
		{
			f.delete();
		}*/
	}
}
	
}
