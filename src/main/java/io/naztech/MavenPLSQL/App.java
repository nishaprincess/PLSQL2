package io.naztech.MavenPLSQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		String url = "jdbc:sqlserver://vNTDACWSSQLD002:1433;databaseName=" + "DEV_TEST";
		Connection conn = DriverManager.getConnection(url, "dev_test_dbo", "dev_test_dbo123");
		System.out.println("DB Connection started");
		Statement sta = conn.createStatement();
		CallableStatement stmt5 = null;
		stmt5 = conn.prepareCall("{call join_Student2}");
		
		ResultSet rs2 =stmt5.executeQuery();
		while (rs2.next()) {
			System.out.println(rs2.getString("id") +" "+ rs2.getString("st_name")+" "+rs2.getString("adress")+" "+rs2.getString("dept")+" "+rs2.getString("Section"));
		}
		CallableStatement stmt = null;

		//stmt = conn.prepareCall("{call ins_Student3}");
		//stmt.execute();

		Scanner input = new Scanner(System.in);
		System.out.println("Enter Student name:");
		String name = input.next();
		CallableStatement stmt2 = null;

		stmt2 = conn.prepareCall("{call update_student5(?)}");
		stmt2.setString(1, name);

		stmt2.execute();

		CallableStatement stmt3 = null;

		System.out.println("Enter Student id:");
		int id = input.nextInt();
		System.out.println("Enter Student name:");
		String sname = input.next();
		System.out.println("Enter Student address:");
		String addr = input.next();
		stmt3 = conn.prepareCall("{call insert_student_proc5(?,?,?)}");

		stmt3.setInt(1, id);
		stmt3.setString(2, sname);
		stmt3.setString(3, addr);

		stmt3.execute();
		
		System.out.println("Enter Student id:");
		int sid = input.nextInt();
		CallableStatement stmt4 = null;
		stmt4 = conn.prepareCall("{call delete_all_fromstudent(?)}");
		stmt4.setInt(1, sid);
		stmt4.execute();
		String Sql = "select * from dbo.Student2";
		ResultSet rs = sta.executeQuery(Sql);
		while (rs.next()) {
			System.out.println(rs.getString("id") +" "+ rs.getString("st_name")+" "+rs.getString("adress"));
		}
		
		

	}
}
