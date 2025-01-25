package com.denghongjie.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class buttonJFrame2 extends JFrame implements MouseListener{

    JButton jbt = new JButton("first button");
    JButton jbt1 = new JButton("second button");

    Random r = new Random();

    public buttonJFrame2() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(800, 600);
        jFrame.setTitle("演示");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);

        jbt.setBounds(100, 100, 100, 100);
        jbt.addMouseListener(this);
        jFrame.getContentPane().add(jbt);

        jbt1.setBounds(0, 0, 100, 100);
        jbt1.addMouseListener(this);
        jFrame.getContentPane().add(jbt1);

        jFrame.setVisible(true);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object source = e.getSource();
//        if(source == jbt){
//            jbt.setSize(800, 600);
//        }else if(source == jbt1){
//            jbt1.setLocation(r.nextInt(500), r.nextInt(500));
//        }
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClick");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }
}
