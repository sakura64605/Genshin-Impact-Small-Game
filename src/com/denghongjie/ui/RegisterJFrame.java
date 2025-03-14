package com.denghongjie.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import com.denghongjie.ui.UserManager;

public class RegisterJFrame extends JFrame {
    // 添加表单组件
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;

    public RegisterJFrame() {
        // 设置统一字体
        Font labelFont = new Font("微软雅黑", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // 初始化表单组件并设置样式
        usernameField = new JTextField();
        usernameField.setFont(fieldFont);
        passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);
        confirmField = new JPasswordField();
        confirmField.setFont(fieldFont);

        // 创建带标题边框的表单面板
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 15, 20));
        formPanel.setBorder(BorderFactory.createTitledBorder("注册信息"));
        formPanel.add(createStyledLabel("用户名:", labelFont));
        formPanel.add(usernameField);
        formPanel.add(createStyledLabel("密码:", labelFont));
        formPanel.add(passwordField);
        formPanel.add(createStyledLabel("确认密码:", labelFont));
        formPanel.add(confirmField);

        // 添加渐变背景
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(245, 245, 245), getWidth(), getHeight(), new Color(220, 237, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        // 调整窗口尺寸为 450x450
        this.setSize(450, 450);
        
        // 在contentPane布局中添加内边距
        contentPane.setLayout(new BorderLayout(10, 20) {
            {
                setVgap(30); // 增加垂直间隔
            }
        });
        
        // 调整表单面板尺寸
        // 移除重复的 formPanel 声明，修改现有面板参数
        formPanel = new JPanel(new GridLayout(3, 2, 15, 25)); // 调整行间距
        formPanel.setPreferredSize(new Dimension(350, 250)); // 保留尺寸设置
        formPanel.setBorder(BorderFactory.createTitledBorder("注册信息"));
        
        // 保持原有组件添加逻辑不变
        formPanel.add(createStyledLabel("用户名:", labelFont));
        formPanel.add(usernameField);
        formPanel.add(createStyledLabel("密码:", labelFont));
        formPanel.add(passwordField);
        formPanel.add(createStyledLabel("确认密码:", labelFont));
        formPanel.add(confirmField);

        contentPane.add(formPanel, BorderLayout.CENTER);

        // 美化注册按钮
        JButton registerBtn = new JButton("立即注册");
        registerBtn.setFont(new Font("微软雅黑", Font.BOLD, 14));
        registerBtn.setBackground(new Color(70, 130, 180));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        
        // 添加事件监听绑定（关键修复）
        registerBtn.addActionListener(this::handleRegister);  // ← 在此处添加监听绑定

        // 组合界面元素
        contentPane.add(createButtonPanel(registerBtn), BorderLayout.SOUTH);
        this.setContentPane(contentPane);

        this.setTitle("用户注册");  // 修正标题文字
        this.setAlwaysOnTop(true);
        // 最後確保顯示在屏幕中央
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 修改关闭行为
        this.setVisible(true);
    }

    private JLabel createStyledLabel(String text, Font font) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        label.setFont(font);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        return label;
    }

    private JPanel createButtonPanel(JButton btn) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // 上下边距增加到20
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // 底部添加20像素边距
        panel.setOpaque(false);
        panel.add(btn);
        return panel;
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

        // 修正方法调用（原路径错误）
        if (UserManager.register(username, password)) { 
            JOptionPane.showMessageDialog(this, "注册成功");
            new LoginJFrame();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "用户名已存在");
        }
    }
}