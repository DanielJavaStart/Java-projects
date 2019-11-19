package dproj_bt;

import java.sql.SQLException;

public class Main_Terminal {
static boolean InSystem=false;
static boolean IsAdmin=false;
static UI main_ui;
 static  String currLogin;
 static String currPass;
 static  int currMoney;

	public static void main(String[] args) {
		try {
			initializeDB();
			 main_ui = new UI();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	static void initializeDB() throws SQLException, ClassNotFoundException {	
		Class.forName("org.mariadb.jdbc.Driver");
	}
}

	
