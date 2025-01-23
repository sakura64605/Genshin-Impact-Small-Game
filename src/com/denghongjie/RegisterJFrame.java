package com.denghongjie;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
        this.setSize(490,500);
        //界面标题
        this.setTitle("登入");
        //置顶界面
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
