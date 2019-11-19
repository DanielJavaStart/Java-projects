package dproj_bt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class DB_Queries {
	static String url = "jdbc:mariadb://localhost:3306/BANK";
	static String username = "root";
	static String password= "qaz123";
static Connection cn;

public static boolean DeleteUser(String log) throws SQLException {
	try {
	cn = DriverManager.getConnection(url,username,password);
	Statement st = cn.createStatement();
	
	ResultSet res1 = st.executeQuery("select * from users;");
	while(res1.next()) {
	if(res1.getString(2).equals(log) && !log.equals(Main_Terminal.currLogin)) {
		st.executeUpdate("Delete from users where login="+'"'+log+'"');
		return true;
	}
	}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		if(cn!=null)cn.close();
	}
	
	return false;
}

    public static String getListUsers() {
    	String ret="";
    	try {
    		cn = DriverManager.getConnection(url,username,password);
    		Statement st = cn.createStatement();
    		ResultSet res1 = st.executeQuery("select * from users;");
    		
    		while(res1.next()) {
    			ret+="id:";
    			ret+=res1.getInt(1)+"\n";
    			ret+="login:"+res1.getString(2)+"\n";
    			ret+="password:"+res1.getString(3)+"\n";
    			ret+="isAdmin:"+res1.getInt(4)+"\n";
    			ret+="money:"+res1.getInt(5)+'\n';
    			ret+="-------------------\n";
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return ret;
    }
    
	public static boolean checkLogin(String log,String pass) throws SQLException {
		try {
		cn = DriverManager.getConnection(url,username,password);
		Statement st = cn.createStatement();
		ResultSet res1 = st.executeQuery("select * from users;");
		
		while(res1.next()) {
			if(res1.getString(2).equals(log) && res1.getString(3).equals(pass)) {
				Main_Terminal.currLogin = log;
				Main_Terminal.currPass = pass;
				Main_Terminal.currMoney = res1.getInt(5);
		System.out.println(res1.getInt(4)+"");
				if(res1.getInt(4)==1) {
					Main_Terminal.IsAdmin = true;
				
				}else {
					Main_Terminal.IsAdmin = false;	
				}
				return true;
			}
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(cn!=null)cn.close();
		}
		return false;
	}
	
	public static boolean Registrate(String log,String pass,boolean isAdmin,int money) throws SQLException {
		try {
		cn = DriverManager.getConnection(url,username,password);
		Statement st = cn.createStatement();
		ResultSet res1 = st.executeQuery("select * from users;");
		
		while(res1.next()) {
			if(res1.getString(2).equals(log)) {
				return false;
			}
		}
		if(money==-1) {
			money=new Random().nextInt(10000);
		}
		int adm=0;
		if(isAdmin)adm=1;
		
		st.execute("Insert into users(login,pass,isAdmin,Money)values("+'"'+log+'"'+","+'"'+pass+'"'+","+adm+","+money+")");
		checkLogin(log,pass);
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(cn!=null)cn.close();
		}
		
		return true;
	}	
	
	public static boolean UpdatePass(String pass) throws SQLException {
		try {
		cn = DriverManager.getConnection(url,username,password);
		Statement st = cn.createStatement();
		
		st.executeUpdate("Update users Set pass="+'"'+pass+'"'+" Where login="+'"'+Main_Terminal.currLogin+'"');
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(cn!=null)cn.close();
		}
		
		return true;
	}
	public static boolean transferMoney(String log, int count) throws SQLException {
		try {
		cn = DriverManager.getConnection(url,username,password);
		Statement st = cn.createStatement();
		
		ResultSet res1 = st.executeQuery("select * from users;");
		while(res1.next()) {
		if(res1.getString(2).equals(log) && !log.equals(Main_Terminal.currLogin)) {
			if(Main_Terminal.currMoney-count>=0) {
			int monetu = res1.getInt(5)+count;
			int monetu1 = Main_Terminal.currMoney-count;
			Main_Terminal.currMoney=monetu1;
			
			st.executeUpdate("Update users Set money="+'"'+monetu+'"'+" Where login="+'"'+log+'"');
			st.executeUpdate("Update users Set money="+'"'+monetu1+'"'+" Where login="+'"'+Main_Terminal.currLogin+'"');
			return true;
			}
		}
		}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(cn!=null)cn.close();
		}
		
		return false;
	}
}
