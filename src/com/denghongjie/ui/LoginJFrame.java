package com.denghongjie.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginJFrame extends JFrame {
    // 添加表单组件
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginJFrame() {
        this.setSize(490, 430);
        this.setTitle("用户登录"); // 修正标题
        this.setLayout(new GridLayout(3, 2, 10, 10)); // 网格布局
        
        // 初始化组件
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        
        // 添加表单元素
        add(new JLabel("用户名:"));
        add(usernameField);
        add(new JLabel("密码:"));
        add(passwordField);
        
        // 登录按钮
        JButton loginBtn = new JButton("登录");
        loginBtn.addActionListener(this::handleLogin);
        add(loginBtn);

        // 注册按钮
        JButton registerBtn = new JButton("去注册");
        registerBtn.addActionListener(e -> {
            new RegisterJFrame();
            dispose();
        });
        add(registerBtn);

        // 保留原有设置
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 修改关闭行为
        this.setVisible(true);
    }

    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入用户名和密码");
            return;
        }
        
        // 调用用户验证
        if (com.denghongjie.util.UserManager.login(username, password)) {
            new GameJFrame(); // 登录成功启动游戏
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "验证失败，请检查凭据");
        }
    }
}