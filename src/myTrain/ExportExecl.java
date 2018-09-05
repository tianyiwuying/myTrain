package myTrain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExportExecl {
public static void main(String[] args) throws IOException
 {
	Date objTime=new Date();
	//时间格式化
	SimpleDateFormat objSimple=new SimpleDateFormat("yyyyMMddHHmmss");
	//获取相对项目的路径
	String execlPath=System.getProperty("user.dir")+File.separator+objSimple.format(objTime)+".xls";
	System.out.println(execlPath);
	//创建HSSFWorkbook对象  
	HSSFWorkbook wb=new HSSFWorkbook();
	//创建HSSFSheet对象  
	HSSFSheet sheet=wb.createSheet("第一页");	
	//设置缺省列高sheet.setDefaultColumnWidth(20);//设置缺省列宽  
	sheet.setDefaultRowHeightInPoints(20);	
	//创建HSSFRow对象  
	HSSFRow row=sheet.createRow(0);
	//创建HSSFCell对象  
	HSSFCell cell=row.createCell(0); 
	//设置指定列的列宽，256 * 50这种写法是因为width参数单位是单个字符的256分之一
	sheet.setColumnWidth(cell.getColumnIndex(), 256 * 50);
	
	 HSSFCellStyle style=wb.createCellStyle();	
     //style.setFillBackgroundColor(HSSFColor.SKY_BLUE.index);	
     style.setFillBackgroundColor((short)25);
     style.setFillPattern(FillPatternType.ALT_BARS);
     
	 HSSFFont  fontStyle=wb.createFont(); 
	//设置字体样式  
	  fontStyle.setFontName("宋体");  
	  //设置字体高度  
	  fontStyle.setFontHeightInPoints((short)20); 	
	  //设置字体颜色  
	  fontStyle.setColor((short)30); 
	  //设置粗体  
	  fontStyle.setBold(true);
	  //设置斜体  
	 fontStyle.setItalic(true); 	  
	 //设置下划线  	  
	 fontStyle.setUnderline(HSSFFont.U_SINGLE);	 
	 style.setFont(fontStyle); 
	
	 //设置单元格的值  
	 cell.setCellStyle(style);	
	 cell.setCellValue("单元格中的中文"); 
	//输出Excel文件  
	FileOutputStream output=new FileOutputStream(execlPath); 
	wb.write(output); 
	
	/*OutputStream output=response.getOutputStream();  
    response.reset();  
    response.setHeader("Content-disposition", "attachment; filename=details.xls");  
    response.setContentType("application/msexcel");   */    
    
	
	output.flush();  
 }
}
