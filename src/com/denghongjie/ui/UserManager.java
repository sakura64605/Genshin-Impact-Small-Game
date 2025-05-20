package com.denghongjie.ui;

import java.sql.*;
import java.util.HashMap;

public class UserManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/game_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    static {
        try {
            // 显式加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC驱动加载成功");
            initDatabase();
        } catch (ClassNotFoundException e) {
            System.err.println("错误：找不到MySQL JDBC驱动");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("初始化数据库错误");
            e.printStackTrace();
        }
    }

    // 初始化数据库表
    private static void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "username VARCHAR(50) UNIQUE NOT NULL," +
                     "password VARCHAR(100) NOT NULL)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 用户注册
    public static boolean register(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // 先检查数据库连接是否正常
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "users", new String[] {"TABLE"});
            if (!tables.next()) {
                System.err.println("错误：users表不存在");
                return false;
            }
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.err.println("数据库错误详情:");
            System.err.println("错误代码: " + e.getErrorCode());
            System.err.println("SQL状态: " + e.getSQLState());
            System.err.println("错误信息: " + e.getMessage());
            
            if (e.getErrorCode() == 1062) { // 唯一约束冲突
                System.out.println("用户名已存在: " + username);
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    // 用户登录验证
    public static boolean login(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("password").equals(password);
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 添加获取数据库连接信息的方法
    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getDbUser() {
        return DB_USER;
    }
}