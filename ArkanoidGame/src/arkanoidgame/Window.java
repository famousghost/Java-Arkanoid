/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 *
 * @author Marcin
 */
public class Window extends JFrame implements ActionListener {
    JLabel gameTitle = new JLabel("Arkanoid");
    JLabel copyRight = new JLabel("By Marcin Czekaj");
    JPanel menu = new JPanel();
    JButton start = new JButton("Start");
    JButton stats = new JButton("Stats");
    JButton exit = new JButton("Exit");
    
    private void Init(int width,int height,String title)
    {
        /*Set Window value*/
        this.setBounds(0,0,width,height);
        this.setTitle(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        /*Set Buttons value*/
        start.setBounds(width/2-50,height/2-100,100,50);
        
        start.addActionListener(this);
        
        stats.setBounds((width/2)-50, height/2-50, 100, 50);
        
        stats.addActionListener(this);
        
        exit.setBounds((width/2)-50, height/2, 100, 50);
        
        exit.addActionListener(this);
        
        start.setFont(new Font("Serif",Font.BOLD,14));
        
        
        stats.setFont(new Font("Serif",Font.BOLD,14));
        
        
        exit.setFont(new Font("Serif",Font.BOLD,14));
        
        start.setBackground(Color.yellow);
        stats.setBackground(Color.yellow);
        exit.setBackground(Color.yellow);
        /*Set Labels value*/
        gameTitle.setBounds((width/2)-70, 0, 300, 100);
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setFont(new Font("Serif", Font.PLAIN, 36));
        copyRight.setBounds(width-200, height-150, 300, 100);
        copyRight.setForeground(Color.WHITE);
        copyRight.setFont(new Font("Serif", Font.PLAIN, 20));
        
        /*Attatch Buttons and Labels to menu*/
        menu.setLayout(null);
        
        menu.add(gameTitle);
        
        menu.add(start);
        
        menu.add(stats);
        
        menu.add(exit);
        
        menu.add(copyRight);
        
        menu.setBackground(Color.darkGray);
        
        /*Attatch menu to Window and disable a Window Resizable*/
        this.add(menu);
        this.setResizable(false);
        
        
        //window.getContentPane().setLayout(null);
        
    }
    
    public Window(int width,int height,String title)
    {
        Init(width,height,title);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == start)
        {
            GameWindow gameWindow = new GameWindow(800,600,"Arkanoid");
            this.setVisible(false);
        }
        if(source == stats)
        {
            
        }
        if(source == exit)
        {
            dispose();
        }
    }
    
}
