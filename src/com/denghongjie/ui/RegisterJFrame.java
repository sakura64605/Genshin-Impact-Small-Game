package com.denghongjie.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterJFrame extends JFrame {
    // 添加表单组件
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;

    public RegisterJFrame() {
        this.setSize(490, 500);
        this.setTitle("用户注册");  // 修正标题文字
        this.setLayout(new GridLayout(5, 2, 10, 10)); // 使用网格布局
        
        // 初始化表单组件
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmField = new JPasswordField();
        
        // 添加表单元素
        add(new JLabel("用户名:"));
        add(usernameField);
        add(new JLabel("密码:"));
        add(passwordField);
        add(new JLabel("确认密码:"));
        add(confirmField);
        
        // 添加注册按钮
        JButton registerBtn = new JButton("立即注册");
        registerBtn.addActionListener(this::handleRegister);
        add(registerBtn);

        // 保留原有界面设置
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 修改关闭行为
        this.setVisible(true);
    }

    private void handleRegister(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmField.getPassword());

        // 输入验证
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空");
            return;
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "两次密码输入不一致");
            return;
        }

        // 调用用户管理类进行注册
        if (com.denghongjie.util.UserManager.register(username, password)) {
            JOptionPane.showMessageDialog(this, "注册成功");
            new LoginJFrame();  // 跳转回登录界面
            this.dispose();     // 关闭当前窗口
        } else {
            JOptionPane.showMessageDialog(this, "用户名已存在");
        }
    }
}