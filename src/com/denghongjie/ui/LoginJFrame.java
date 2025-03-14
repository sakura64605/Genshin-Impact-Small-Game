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
        this.setTitle("用户登录");
        // 修改为更灵活的布局
        this.setLayout(new BorderLayout(10, 20));
        
        // 创建表单面板
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 15));
        formPanel.add(new JLabel("用户名:", SwingConstants.RIGHT));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("密码:", SwingConstants.RIGHT));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        
        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        JButton loginBtn = new JButton("登录");
        loginBtn.setPreferredSize(new Dimension(120, 35)); // 设置按钮尺寸
        loginBtn.addActionListener(this::handleLogin);
        
        JButton registerBtn = new JButton("去注册");
        registerBtn.setPreferredSize(new Dimension(120, 35));
        registerBtn.addActionListener(e -> {
            new RegisterJFrame();
            dispose();
        });

        // 添加组件到面板
        buttonPanel.add(loginBtn);
        buttonPanel.add(registerBtn);
        
        // 添加边距并组合布局
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

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
        if (com.denghongjie.ui.UserManager.login(username, password)) {
            new GameJFrame(); // 登录成功启动游戏
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "验证失败，请检查凭据");
        }
    }
}