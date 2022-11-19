package com.umc.week6.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class Week6Application {

	public static void main(String[] args) {
		SpringApplication.run(Week6Application.class, args);
		try{
			String url = "jdbc:mysql://localhost/?characterEncoding=UTF-8&serverTimezone=UTC";
			String user = "yj";
			String passwd = "4571";
			Connection con = DriverManager.getConnection(url,
					user, passwd);
					con.close();
					System.out.println("DB연결 성공");
				} catch (SQLException e) {
					System.out.println("DB연결 실패");
					System.out.print("사유 : " + e.getMessage());
				}
	}

}
