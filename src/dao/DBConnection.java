package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn;  
		
	public static Connection getConnection() {
		//싱글톤 패턴 : 단 하나의 객체만 만들어서 사용하도록 설계된 패턴
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");//Java쪽으로 설계도 클래스를 사용하고자 불러오는 것 
				System.out.println("드라이버 로딩 성공!");
				
				//다리를 짓고자 하는 목적지
				String url = "jdbc:mysql://localhost:3306/musi";
				String user = "root";
				String password = "1234";
				
				conn = DriverManager.getConnection(url, user, password);
			}
			catch(ClassNotFoundException cnfe) {
				System.out.println("드라이버 로딩 실패 : "+cnfe);
			}
			catch(SQLException sqle) {
				System.out.println("DB 연결 실패"+sqle);
			}
		}
		return conn;
	}
}