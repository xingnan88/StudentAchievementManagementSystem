import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
public class SqlManager {
	private static SqlManager p=null;
	private PropertyResourceBundle bundle;
	private static String jdbcDriver=null;
	private static String split=null;
	private String DBType=null;
	private String DBhost="localhost";
	private String DBname="";
	private String DBport="";
	private String DBuser="";
	private String DBpassword="";
	private Connection Sqlconn=null;
	private Statement Sqlstmt=null;
	private String strCon=null;
	private SqlManager(){
		try{
			bundle=new PropertyResourceBundle(SqlManager.class.
					getResourceAsStream("/sysConfig.properties"));			
			this.DBhost=getString("DBhost");
			this.DBname=getString("DBname");
			this.DBport=getString("DBport");
			this.DBuser=getString("DBuser");
			this.DBpassword=getString("DBpassword");			
			String system_type=getString("system-type");
			if(system_type!=null){
				if(system_type!=null){
					if(system_type.toLowerCase().equals("widows"))
						split=";";
					else if(system_type.toLowerCase().equals("unix"))
						split=":";
				}
				String database_type=getString("database-type");
				this.DBType=database_type;
				if(database_type!=null){
					if(database_type.toLowerCase().equals("mysql")){
						jdbcDriver="com.mysql.jdbc.Driver";
						strCon="jdbc:mysql://"+DBhost+":"+DBport+"/"+DBname;
					}
					else if(database_type.toLowerCase().equals("oracle")){
						jdbcDriver="oracle.jdbc.driver.OracleDriver";
						strCon="jdbc:oracle:thin:@"+DBhost+":"+DBport+":"+DBname;
					}
					else if(database_type.toLowerCase().equals("sqlserver")){
						jdbcDriver="com.microsoft.jdbc.sqlserver.SQLServerDriver";
						strCon="jdbc:microsoft:sqlserver://"+DBhost+":"+DBport+";DatabaseName="+DBname;
					}
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static SqlManager createInstance(){
		if(p==null)
		{
			p=new SqlManager();
			p.initDB();
		}
		return p;
	}
	private String getString(String s)
	{
		return this.bundle.getString(s);
	}
	
	public void initDB(){
		System.out.println(strCon);
		System.out.println(jdbcDriver);
		try{
			Class.forName(jdbcDriver);
		}catch(Exception ex){
			System.err.println("Can't Find Database Driver.");
		}
	}
	public void connectDB(){
		try{
			System.out.println("SqlManager:Connecting to database...");
			Sqlconn=DriverManager.getConnection(strCon,DBuser,DBpassword);
			Sqlstmt=Sqlconn.createStatement();
		}catch(SQLException ex){
			System.err.println("connectDB"+ex.getMessage());
		}
		System.out.println("SqlManager:Connect to database successful.");
	}
	public void closeDB(){
		try{
			System.out.println("SqlManager:Close connection to database...");
			Sqlstmt.close();
			Sqlconn.close();
		}catch(SQLException ex){
			System.err.println("closeDB:"+ex.getMessage());
		}
		System.out.println("Sqlmanager:Close connection successful.");
	}
	public int executeUpdate(String sql){
		int ret=0;
		try{
			ret=Sqlstmt.executeUpdate(sql);
		}catch(SQLException ex)
		{
			System.out.println("executeUpdate:"+ex.getMessage());
		}
		return ret;
	}
	public ResultSet executeQuery(String sql){
		ResultSet rs=null;
		try{
			rs=Sqlstmt.executeQuery(sql);
		}catch(SQLException ex){
			System.err.println("executeQuery:"+ex.getMessage());
		}
		return rs;
	}
}
