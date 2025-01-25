package com.denghongjie.ui;

import javax.swing.*;
import java.util.Random;

public class GameJFrame extends JFrame {
    int[][] arr = new int[5][5];
    public GameJFrame() {
        //初始化菜单
        initJFrame();
        //创建界面
        initJMenuBar();
        //初始化数据
        initData();
        //初始化图片
        initImage();
        this.setVisible(true);
    }

    private void initData() {
        int[] testArr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};

        Random random = new Random();
        for (int i = 0; i < testArr.length; i++) {
            int index = random.nextInt(testArr.length);
            int temp = testArr[i];
            testArr[i] = testArr[index];
            testArr[index] = temp;
        }
        int cnt = 0;
        for (int i = 0; i < testArr.length; i++) {
            arr[i / 5][i % 5] = testArr[i];
        }
    }

    private void initImage() {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                ImageIcon icon = new ImageIcon("images/少女/image" + arr[i][j] + ".png");
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
