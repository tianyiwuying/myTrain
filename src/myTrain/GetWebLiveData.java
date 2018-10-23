package myTrain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

 




import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/*
 * cwj
 * 2018-10-19
 * 获取http://www.weatherdt.com/market/datastore/database 网上的数据
 * 秘钥：bacbe11aebf062116fffab113e41413a
 * 接口URL规范
 * http://api.weatherdt.com/common/?area=站号&type=数据大类[数据小类]&key=密钥
 * */
public class GetWebLiveData {
	// String
	// webIp="http://api.weatherdt.com/common/?area=站号&type=数据大类[数据小类]&key=密钥";
	// 北京城区
	static String webIp = "http://api.weatherdt.com/common/?area=101010100&type=forecast&key=bacbe11aebf062116fffab113e41413a";

	public static void main(String[] args) {
		Map<String, String> windConvert = new HashMap<String, String>() {
			{
		/*		put("0", "无持续风向");
				put("1", "东北风");
				put("2", "东风");
				put("3", "东南风");
				put("4", "南风");
				put("5", "西南风");
				put("6", "西风");
				put("7", "西北风");
				put("8", "北风");
				put("9", "旋转风");*/
				put("0", "wcxfx");
				put("1", "dbf");
				put("2", "df");
				put("3", "dnf");
				put("4", "nf");
				put("5", "xnf");
				put("6", "xf");
				put("7", "xbf");
				put("8", "bf");
				put("9", "xzf");
			}
		};
		Map<String, String> codeConvert = new HashMap<String, String>() {
			{
				put("000", "Datetime");
				put("002", "TEM");
				put("003", "WIN_S_Avg_2mi");
				put("004", "WIN_D_Avg_2mi");
				put("005", "RHU");
				put("006", "PRE_1h");
				put("007", "PRS");
			}
		};
		List<String> objStation = new ArrayList<String>() {
			{
				add("114010101001");
				add("114010102001");
				add("114010103001");
			}
		};
		String typeData="1001002"; //实况
		
		
		//runable(codeConvert,windConvert,objStation,typeData);
		
	   Runnable runnable = new Runnable() {  
            public void run() {    
            	System.out.println("888");
            	try{
            	runable(codeConvert,windConvert,objStation,typeData);
            	}catch(Exception ex){ex.printStackTrace();}
            	System.out.println("99");
            	System.gc();
            }  
        };  
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, 1, 50, TimeUnit.MINUTES); 
	}

	/*
	 * cwj
	 * 循环运行读取数据
	 * */
	public static void runable(Map<String, String> codeConvert,Map<String, String> windConvert,List<String> objStation,String typeData)
	{
		SimpleDateFormat objSimple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date objTime=new Date();
        System.out.println(objSimple.format(objTime)+"****执行*********");
		webIp = "http://api.weatherdt.com/common/?area="+String.join("|", objStation)+"&type=observe&key=4cf9a37d7cfc14eb101495e308d5950b";
		// 获取网页获取的值
		String webValue = loadJson(webIp);
		JSONObject myJson = JSONObject.parseObject(webValue);
		Map sum = new HashMap();		
	
		getJson(myJson, sum, objStation, typeData);
		List<String> objList=new ArrayList();
		StringBuilder keyV=new StringBuilder();
		StringBuilder valueV=new StringBuilder();
		
		String insert="insert into observe(%s) values(%s)";
		for (Object key : sum.keySet())
		{
			keyV.setLength(0);
			valueV.setLength(0);
			String values = sum.get(key).toString();
			System.out.println("Key = " + key.toString() + ", Value = "	+ values);
			keyV.append(String.format("%s,%s", "Station_ID_C","hour"));
			valueV.append(String.format("%s,%s", "'"+key.toString()+"'","'"+objSimple.format(objTime)+"'"));
			Map objMap = JSONObject.parseObject(values);
			for (Object child : objMap.keySet()) 
			{
				String keyVaule=child.toString().trim();
				keyV.append(","+String.format("%s", codeConvert.get(keyVaule).toString()));
				String childValue =keyVaule.equals("004")?windConvert.get(objMap.get(child).toString().trim()):objMap.get(child).toString();
				valueV.append(","+String.format("%s", "'"+(childValue.equals("?")?"":childValue)+"'"));
			}
			objList.add(String.format(insert, keyV,valueV));
		}
		System.out.println(String.join(";",objList));
		insertTable	(objList);
	}
	/*
	 * cwj
	 * 插入数据库操作
	 * */
	public static void insertTable(List<String> objList) {
		// 连接MySql数据库，用户名和密码都是root rewriteBatchedStatements=true，mysql默认关闭了batch处理，通过此参数进行打开，这个参数可以重写向数据库提交的SQL语句
		String url = "jdbc:mysql://localhost:3306/lcj?characterEncoding=utf-8";
		String username = "root";
		String password = "sasa";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String result ="";
		try {
			// 获得MySQL驱动的实例
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// 获得连接对象(提供：地址，用户名，密码)
			con = DriverManager.getConnection(url, username, password);

			if (!con.isClosed())
				System.out.println("Successfully connected ");
			else
				System.out.println("failed connected");

			// 建立一个Statement，数据库对象
			st = con.createStatement();
			// 运行SQL查询语句
			System.out.println(String.join(";",objList));
			for (int i = 0; i < objList.size(); i++) {
				result+=st.executeUpdate(objList.get(i))+"**";
			}			
			//result = st.executeLargeUpdate(String.join(";",objList));// .executeQuery("select * from Weather.question_type_1;");
			System.err.println("執行成功!"+result);
			
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (con != null) {
				// 关闭连接对象
				try {
					// 关闭链接
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * cwj 2018-10-22 解析指定格式的json值
	 */
	public static void getJson(Map source, Map sumMap, List<String> objStation,
			String type) {
		for (Object key : source.keySet()) {
			String values = source.get(key).toString();
			if (objStation.contains(key.toString().trim())) {
				Map map = JSONObject.parseObject(values);
				sumMap.put(key.toString(), map.get(type));
			} else {
				try {
					Map map = JSONObject.parseObject(values);
					if (map.size() > 0 && map != null) {
						getJson(map, sumMap, objStation, type);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/*
	 * cwj 2018-10-22 获取根据url获取的接口值
	 */
	private static String loadJson(String url) {
		StringBuilder json = new StringBuilder();
		try {
			// 通过一个表示URL地址的字符串可以构造一个URL对象。
			// url构造函数需要的参数。
			URL link = new URL(url);
			URLConnection yc = link.openConnection();
			// BufferedReader缓冲方式文本读取
			// InputStreamReader是字节流与字符流之间的桥梁，能将字节流输出为字符流，
			// 并且能为字节流指定字符集，可输出一个个的字符
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream(), "utf-8"));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return json.toString();
	}
}
