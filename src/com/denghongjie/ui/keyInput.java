package com.denghongjie.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyInput extends JFrame implements KeyListener {
    public keyInput() {
        this.setSize(800, 600);
        this.setTitle("game");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.addKeyListener(this);


        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased");
        int number = e.getKeyCode();
        System.out.println("keyCode: " + number);
    }
}
