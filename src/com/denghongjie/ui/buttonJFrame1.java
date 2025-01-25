package com.denghongjie.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class buttonJFrame1 extends JFrame implements ActionListener {

    JButton jbt = new JButton("first button");
    JButton jbt1 = new JButton("second button");

    Random r = new Random();

    public buttonJFrame1() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(800, 600);
        jFrame.setTitle("演示");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);

        jbt.setBounds(100, 100, 100, 100);
        jbt.addActionListener(this);
        jFrame.getContentPane().add(jbt);

        jbt1.setBounds(0, 0, 100, 100);
        jbt1.addActionListener(this);
        jFrame.getContentPane().add(jbt1);

        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == jbt){
            jbt.setSize(800, 600);
        }else if(source == jbt1){
            jbt1.setLocation(r.nextInt(500), r.nextInt(500));
        }
    }
}
