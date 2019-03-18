package com.westos.rbac.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * jdbc工具类
 * @author yihang
 */
public class JdbcUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        }
    }


    /**
     * 获取数据库连接
     * @return 连接对象
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?characterEncoding=utf8&useSSL=false", "root", "root");
            return conn;
        } catch (SQLException e) {
            System.out.println("获取连接失败" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
