package com.denghongjie;

import javax.swing.*;

public class GameJFrame extends JFrame {
    public GameJFrame() {
        //初始化菜单
        initJFrame();
        //创建界面
        initJMenuBar();
        //初始化图片
        initImage();
        this.setVisible(true);
    }

    private void initImage() {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                ImageIcon icon = new ImageIcon("images/少女/image" + (i * 5 + j + 1) + ".png");
                JLabel label = new JLabel(icon);
                //指定位置
                label.setBounds(110 * j, 110 * i, icon.getIconWidth(), icon.getIconHeight());
                this.getContentPane().add(label);
            }
        }

    }

    private void initJMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("function");
        JMenu aboutJMenu = new JMenu("About");

        JMenuItem replayJMenuItem = new JMenuItem("Replay");
        JMenuItem reLoginItem = new JMenuItem("ReLogin");
        JMenuItem exitJMenuItem = new JMenuItem("Exit");

        JMenuItem aboutJMenuItem = new JMenuItem("About");

        //添加条目
        functionJMenu.add(replayJMenuItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(exitJMenuItem);

        aboutJMenu.add(aboutJMenuItem);

        menuBar.add(functionJMenu);
        menuBar.add(aboutJMenu);

        this.setJMenuBar(menuBar);
    }

    private void initJFrame() {
        this.setSize(610,680);
        //界面标题
        this.setTitle("拼图游戏");
        //置顶界面
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //设置关闭界面
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
    }
}
