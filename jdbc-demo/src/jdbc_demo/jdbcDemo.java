package jdbc_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "123456";
        Connection conn=DriverManager.getConnection(url,username, password);
        String sql="update user set id=2 where id=1";
        Statement stmt=conn.createStatement();
        int cnt=stmt.executeUpdate(sql);
        System.out.println(cnt);
        stmt.close();
        conn.close();
    }
}