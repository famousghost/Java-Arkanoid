/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
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
    JLabel statsLabel = new JLabel();
    JPanel menu = new JPanel();
    JButton start = new JButton("Start");
    JButton stats = new JButton("Stats");
    JButton back = new JButton("Back");
    JButton exit = new JButton("Exit");
    
    
        //Resolution of screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double scWidth = screenSize.getWidth();
    private double scHeight = screenSize.getHeight();
    
    private ReadFromFile fileRead = new ReadFromFile();
    
    private int halfOfScreenWidth;
    private int halfOfScreenHeight;
    
    
    static Window window = new Window(800,600,"Arkanoid");
    
    private void Init(int width,int height,String title)
    {
        fileRead.Read();
        halfOfScreenWidth = (int)((scWidth-width)/2);
        halfOfScreenHeight = (int)((scHeight-height)/2);
        /*Set Window value*/
        this.setBounds(halfOfScreenWidth,halfOfScreenHeight,width,height);
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
        
        back.setBounds((width/2)-50, height/2+50,100,50);
        
        back.setVisible(false);
        
        back.addActionListener(this);
        
        back.setFont(new Font("Serif",Font.BOLD,14));
        
        statsLabel.setText("nie ma jeszcze żadnych statystyk");
        statsLabel.setFont(new Font("Serif",Font.BOLD,20));
        statsLabel.setBounds((width/2)-50,0,300,600);
        statsLabel.setForeground(Color.white);
        statsLabel.setVisible(false);
        
        
        exit.setFont(new Font("Serif",Font.BOLD,14));
        
        start.setBackground(Color.yellow);
        stats.setBackground(Color.yellow);
        exit.setBackground(Color.yellow);
        back.setBackground(Color.yellow);
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
        
        menu.add(statsLabel);
        
        menu.add(back);
        
        menu.add(exit);
        
        menu.add(copyRight);
        
        menu.setBackground(Color.darkGray);
        
        /*Attatch menu to Window and disable a Window Resizable*/
        this.add(menu);
        this.setResizable(false);
        
        
        //window.getContentPane().setLayout(null);
        
    }
    
    private Window(int width,int height,String title)
    {
        Init(width,height,title);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == start)
        {
            GameWindow.GetInstanceGameWindow();
            GameWindow.GetInstanceGameWindow().SetPause(false);
            GameWindow.GetInstanceGameWindow().setVisible(true);
            GameWindow.GetInstanceGameWindow().SetSizeOfGameWindow(halfOfScreenWidth, halfOfScreenHeight);
            
            this.setVisible(false);
        }
        if(source == stats)
        {
            fileRead.Read();
            start.setVisible(false);
            stats.setVisible(false);
            exit.setVisible(false);
            statsLabel.setVisible(true);
            back.setVisible(true);
            if(fileRead.file.exists())
            {
                Arrays.sort(fileRead.scoresIntArray);
                String[] scores = new String[3];
                for(int i=0;i<3;i++)
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(fileRead.scoresIntArray[i]);
                    scores[i] = sb.toString(); 
                }
                for(int i=0;i<3;i++)
                {
                    System.out.println(scores[i]);
                }
                String text = "<html>First Score: " + scores[2] + "<br>Second Score: " + scores[1] +"<br>Third Score: " + scores[0]+"</html>";
                statsLabel.setText(text);
            }
            
        }
        if(source == back)
        {
            start.setVisible(true);
            stats.setVisible(true);
            exit.setVisible(true);
            back.setVisible(false);
            statsLabel.setVisible(false);
        }
        if(source == exit)
        {
            System.exit(0);
        }
    }
    
    public void CheckPauseGame()
    {
        if(GameWindow.GetInstanceGameWindow().GetPause())
        {
            start.setText("Resume");
            this.setVisible(true);
            GameWindow.GetInstanceGameWindow().setVisible(false);
        }
    }
    
    public static Window GetWindowInstance()
    {
        if(window == null)
        {
            window = new Window(800,600,"Arkanoid");
        }
        return window;
    }
    
    public int GetHalfOfScreenWidth()
    {
        return halfOfScreenWidth;
    }
    public int GetHalfOfScreenHeight()
    {
        return halfOfScreenHeight;
    }
    
}
