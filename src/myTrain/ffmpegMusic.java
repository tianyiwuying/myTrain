package myTrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ffmpegMusic {

public static void main(String[] args)
{
	getMusicChangeDones("D:\\����\\","ceshi.m4a");
}

public static String getMusicChangeDones(String pathOld,String filename)
{
	System.out.println(pathOld);
	System.out.println(filename);
	String path=null;
	if(pathOld.indexOf("\\\\")>-1){
		path=pathOld.replaceAll("\\", "/");
	}else{
		path=pathOld;
	}//-acodec libmp3lame   -ar 8000 -ab 12.2k -ac 1
	String dateTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+".mp3";// ��ȡ��ǰ�������ʱ��
	String command="ffmpeg.exe -y -i \""+path+filename+"\" -ar 8000 -ab 12.2k -ac 1 \""+path+dateTime+"\"";
	System.out.println(command);
   		Runtime rt = Runtime.getRuntime();
	try {
		//"cmd echo " +D:\������Ŀ����\apache-tomcat-7.0.72\webapps      //C:/Users/Administrator
		Process process=rt.exec("cmd /c " +command,null,new File("D:/����/ "));
		InputStream in=process.getInputStream();
		 //��һ�����������ȥ��    
        InputStreamReader isr=new InputStreamReader(in);    
       //�û���������    
        BufferedReader br=new BufferedReader(isr);    
        String line=null;    
       //ֱ������Ϊֹ    
       while((line=br.readLine())!=null)    
        {    
    	   System.out.println("000000");
            System.out.println(line);    
        }  
		
		/*while(in.read()!=-1){
			System.out.println(in.read());
		}*/
		System.out.println("000000");
		in.close();
		System.out.println("8888");
		int exitVal =process.waitFor();
		System.out.println(exitVal);
		System.out.println("ִ�����");
		return dateTime;
	} catch (IOException e) {
		System.out.println("����ִ�д�������C:�û�\\Administrator\\ffmpeg.exe�Ƿ���ڣ��������"+command);
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "error";
}
}