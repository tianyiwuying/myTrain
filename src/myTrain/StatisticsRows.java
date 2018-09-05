package myTrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StatisticsRows
{
	    private static int normalLines = 0;  //��Ч��������
	    private static int whiteLines = 0;   //�հ�����
	    private static int commentLines = 0; //ע������

	    public static void countCodeLine(File file) {
	        System.out.println("��������ͳ�ƣ�" + file.getAbsolutePath());
	        if (file.exists()) {
	            try {
	                scanFile(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("�ļ������ڣ�");
	            System.exit(0);
	        }
	        System.out.println(file.getAbsolutePath() + " ,java�ļ�ͳ�ƣ�" +
	                "����Ч��������: " + normalLines +
	                " ,�ܿհ�������" + whiteLines +
	                " ,��ע��������" + commentLines +
	                " ,��������" + (normalLines + whiteLines + commentLines));
	    }

	    private static void scanFile(File file) throws IOException {
	        if (file.isDirectory()) {
	            File[] files = file.listFiles();
	            for (int i = 0; i < files.length; i++) {
	                scanFile(files[i]);
	            }
	        }
	        if (file.isFile())
	        {
	        	 if(file.getName().endsWith(".java")) count(file);	           
	             if(file.getName().endsWith(".jsp"))   count(file);
	             if(file.getName().endsWith(".xml"))   count(file);
	             if(file.getName().endsWith(".js"))   count(file);
	             if(file.getName().endsWith(".css"))   count(file);
	             if(file.getName().endsWith(".properties"))   count(file);
	             if(file.getName().endsWith(".txt"))   count(file);	       	
	        	//
	        	 if(file.getName().endsWith(".cs"))   count(file);
	        	 if(file.getName().endsWith(".xaml"))   count(file);
	        	 if(file.getName().endsWith(".config"))   count(file);
	        	 if(file.getName().endsWith(".aspx"))   count(file);
	        }
	    }

	    private static void count(File file) {
	        BufferedReader br = null;
	        // �жϴ����Ƿ�Ϊע����
	        boolean comment = false;
	        int temp_whiteLines = 0;
	        int temp_commentLines = 0;
	        int temp_normalLines = 0;

	        try {
	            br = new BufferedReader(new FileReader(file));
	            String line = "";
	            while ((line = br.readLine()) != null) {
	                line = line.trim();
	                if (line.matches("^[//s&&[^//n]]*$")) {
	                    // ����
	                    whiteLines++;
	                    temp_whiteLines++;
	                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
	                    // �жϴ���Ϊ"/*"��ͷ��ע����
	                    commentLines++;
	                    comment = true;
	                } else if (comment == true && !line.endsWith("*/")) {
	                    // Ϊ����ע���е�һ�У����ǿ�ͷ�ͽ�β��
	                    commentLines++;
	                    temp_commentLines++;
	                } else if (comment == true && line.endsWith("*/")) {
	                    // Ϊ����ע�͵Ľ�����
	                    commentLines++;
	                    temp_commentLines++;
	                    comment = false;
	                } else if (line.startsWith("//")) {
	                    // ����ע����
	                    commentLines++;
	                    temp_commentLines++;
	                } else {
	                    // ����������
	                    normalLines++;
	                    temp_normalLines++;
	                }
	            }

	            System.out.println(file.getName() +
	                    " ,��Ч����" + temp_normalLines +
	                    " ,�հ�����" + temp_whiteLines +
	                    " ,ע������" + temp_commentLines +
	                    " ,������" + (temp_normalLines + temp_whiteLines + temp_commentLines));
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                    br = null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    //����
	    public static void main(String[] args) {
	        File file = new File("H:\\gongxiang\\SWNC");
	        countCodeLine(file);
	    }
	}
