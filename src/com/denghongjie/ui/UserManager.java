package com.denghongjie.ui;

import java.io.*;
import java.util.HashMap;

public class UserManager {
    private static final String DATA_PATH = "d:\\text\\game\\data\\users.dat";
    private static HashMap<String, String> users;

    static {
        users = loadUsers();
    }

    // 加载用户数据
    private static HashMap<String, String> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_PATH))) {
            return (HashMap<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("首次使用，创建新用户存储文件");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    // 用户注册
    public static boolean register(String username, String password) {
        System.out.println("尝试注册用户：" + username);  // 调试日志
        if (users.containsKey(username)) {
            System.out.println("用户名已存在：" + username);
            return false;
        }

        users.put(username, password);
        boolean result = saveUsers();
        System.out.println("注册结果：" + result);  // 查看保存结果
        return result;
    }

    // 用户登录验证
    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // 保存用户数据
    private static boolean saveUsers() {
        try {
            File dataFile = new File(DATA_PATH);
            if (!dataFile.exists()) {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
                oos.writeObject(users);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}