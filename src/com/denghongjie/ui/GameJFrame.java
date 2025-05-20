package com.denghongjie.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.random.RandomGenerator;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] arr = new int[4][4];
    int[][] win = new int[][]  {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0},
    };
    int x = 0;
    int y = 0;
    int count = 0;
    String path = "images/animal/animal1/";
    JMenuItem replayJMenuItem = new JMenuItem("Replay");
    JMenuItem reLoginItem = new JMenuItem("ReLogin");
    JMenuItem exitJMenuItem = new JMenuItem("Exit");
    JMenuItem Animal = new JMenuItem("Animal");
    JMenuItem Girl = new JMenuItem("Girl");
    JMenuItem Sport = new JMenuItem("Sport");

    JMenuItem aboutJMenuItem = new JMenuItem("Author");
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
        int[] testArr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};

        Random random = new Random();
        for (int i = 0; i < testArr.length; i++) {
            int index = random.nextInt(testArr.length);
            int temp = testArr[i];
            testArr[i] = testArr[index];
            testArr[index] = temp;
        }
        for (int i = 0; i < testArr.length; i++) {
            if (testArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            arr[i / 4][i % 4] = testArr[i];
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();

        if(victory()){
            JLabel label = new JLabel(new ImageIcon("images/win.png"));
            label.setBounds(160,20,250,80);
            this.getContentPane().add(label);
        }

        JLabel stepLabel = new JLabel("steps: " + count);
        stepLabel.setBounds(10,10,100,20);
        this.getContentPane().add(stepLabel);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ImageIcon icon = new ImageIcon(path + arr[i][j] + ".jpg");
                JLabel label = new JLabel(icon);
                //指定位置
                label.setBounds(110 * j + 80, 110 * i + 110, icon.getIconWidth(), icon.getIconHeight());
                label.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(label);
            }
        }

        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("function");
        JMenu aboutJMenu = new JMenu("About");
        JMenu changeImageJMenu = new JMenu("changeImage");

        changeImageJMenu.add(Animal);
        changeImageJMenu.add(Girl);
        changeImageJMenu.add(Sport);

        //添加条目
        functionJMenu.add(changeImageJMenu);
        functionJMenu.add(replayJMenuItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(exitJMenuItem);

        aboutJMenu.add(aboutJMenuItem);

        replayJMenuItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        exitJMenuItem.addActionListener(this);
        aboutJMenuItem.addActionListener(this);
        Animal.addActionListener(this);
        Girl.addActionListener(this);
        Sport.addActionListener(this);

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
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 32) {
            this.getContentPane().removeAll();
            JLabel label = new JLabel(new ImageIcon(path+"all.jpg"));
            label.setBounds(0, 0, 610, 680);
            this.getContentPane().add(label);
            this.getContentPane().repaint();
//            this.setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()){
            return;
        }
        int cnt = 0;
        int key = e.getKeyCode();
        if(key == 37 || key == 65){
            count++;
            System.out.println("move left");
            if(y == 3){
                return;
            }
            arr[x][y] = arr[x][y + 1];
            arr[x][y + 1] = 0;
            y++;
            initImage();
        }
        else if(key == 38 || key == 87){
            count++;
            System.out.println("move up");
            if(x == 3){
                return;
            }
            arr[x][y] = arr[x + 1][y];
            arr[x + 1][y] = 0;
            x++;
            initImage();
        }
        else if(key == 39 || key == 68){
            count++;
            System.out.println("move right");
            if(y == 0){
                return;
            }
            arr[x][y] = arr[x][y - 1];
            arr[x][y - 1] = 0;
            y--;
            initImage();
        }
        else if(key == 40 || key == 83){
            count++;
            System.out.println("move down");
            if(x == 0){
                return;
            }
            arr[x][y] = arr[x - 1][y];
            arr[x - 1][y] = 0;
            x--;
            initImage();
        }
        else if(key == 32){
            initImage();
        }
        else if(key == 82){
            for (int i = 0; i < 16; i++) {
                arr[i / 4][i % 4] = i + 1;
            }
            arr[3][3] = 0;
            initImage();
        }
        //System.out.println(key);
    }

    public boolean victory(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(arr[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object click = e.getSource();
        if(click == replayJMenuItem){
            System.out.println("replay");
            count = 0;
            initData();
            initImage();
        }else if(click == reLoginItem){
            System.out.println("reLogin");
            this.setVisible(false);
            new LoginJFrame();
        }else if(click == exitJMenuItem){
            System.exit(0);
        }else if(click == aboutJMenuItem){
            System.out.println("about");

            JDialog aboutDialog = new JDialog();
            JLabel label = new JLabel(new ImageIcon("images/about.png"));
            label.setBounds(0,0,300,300);
            aboutDialog.getContentPane().add(label);
            aboutDialog.setBounds(0,0,330,330);
            aboutDialog.setAlwaysOnTop(true);
            aboutDialog.setLocationRelativeTo(null);
            aboutDialog.setModal(true);
            aboutDialog.setVisible(true);
        }else if(click == Animal){
            RandomGenerator random = RandomGenerator.getDefault();
            int order = random.nextInt(1,9);
            this.path = "images/animal/animal" + order + "/";
            count = 0;
            initData();
            initImage();
        }else if(click == Girl){
            Random rand = new Random();
            int order = rand.nextInt(1,14);
            this.path = "images/girl/girl" + order + "/";
            count = 0;
            initData();
            initImage();
        }else if(click == Sport){
            Random rand = new Random();
            int order = rand.nextInt(1,11);
            this.path = "images/sport/sport" + order + "/";
            count = 0;
            initData();
            initImage();
        }
    }
}
