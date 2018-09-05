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
	getMusicChangeDones("D:\\湘西\\","ceshi.m4a");
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
	String dateTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+".mp3";// 获取当前到毫秒的时间
	String command="ffmpeg.exe -y -i \""+path+filename+"\" -ar 8000 -ab 12.2k -ac 1 \""+path+dateTime+"\"";
	System.out.println(command);
   		Runtime rt = Runtime.getRuntime();
	try {
		//"cmd echo " +D:\湘西项目资料\apache-tomcat-7.0.72\webapps      //C:/Users/Administrator
		Process process=rt.exec("cmd /c " +command,null,new File("D:/湘西/ "));
		InputStream in=process.getInputStream();
		 //用一个读输出流类去读    
        InputStreamReader isr=new InputStreamReader(in);    
       //用缓冲器读行    
        BufferedReader br=new BufferedReader(isr);    
        String line=null;    
       //直到读完为止    
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
		System.out.println("执行完毕");
		return dateTime;
	} catch (IOException e) {
		System.out.println("命令执行错误，请检查C:用户\\Administrator\\ffmpeg.exe是否存在，错误命令："+command);
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "error";
}
}