package com.ninza.hrm.api.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
	static Connection con=null;
	static ResultSet result=null;
	static FileUtility fLib =new FileUtility();
	

public void getDbconnection() throws SQLException {
	Driver driver;
	try {
	driver=new Driver();
		DriverManager.registerDriver(driver);
		con= DriverManager.getConnection(fLib.getDataFromPropertiesFile("DBUrl")
				,fLib.getDataFromPropertiesFile("DB_Username"),fLib.getDataFromPropertiesFile("DB_Password"));
	}catch(Exception e) {
	}
}
public void closeDbconnection() {
	try {
		con.close();
	}
	catch(Exception e) {}
}

public ResultSet executeSelectQuery(String Query) {
	
	try {
		result =con.createStatement().executeQuery(Query);
	return result;
}catch(SQLException e) {
	e.printStackTrace();
}finally {
	
}
	return result;
}
public int executenonSelectQuery(String Query) {
	int result =0;
	try {
		Statement stat=con.createStatement();
		 stat.executeUpdate(Query);
	}catch(Exception e) {
		
	}
	return result;
	}
public boolean  executeQueryVerifyAndGetData(String query,int columnIndex,String expectedData) throws SQLException {
boolean flag=false;
result =con.createStatement().executeQuery(query);
while(result.next()) {
    if(result.getString(columnIndex).equals(expectedData)) {
    	flag=true;
    	break;
    }
    }
   if (flag) {
	   System.out.println(expectedData+"===>data verified in data base table");
	   return true;
   }else {
	   System.out.println(expectedData+"===>data verified in data base table");
	   return false;
   }
}

}

	


