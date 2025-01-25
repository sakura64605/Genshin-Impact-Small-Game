package com.denghongjie.ui;

import javax.swing.*;


public class LoginJFrame extends JFrame {
    public LoginJFrame() {
        this.setSize(490,430);
        //界面标题
        this.setTitle("注册");
        //置顶界面
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
