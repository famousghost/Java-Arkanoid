/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Marcin
 */
public class GameWindow extends JFrame implements KeyListener {
    
    JPanel blocks = new JPanel(new GridLayout(4,4));
    private int blockWidth;
    private int blockHeight;
    
    Paddle paddle = new Paddle(150,30);
    
    private void Init(int width,int height,String title)
    {
        blockWidth = 60;
        blockHeight = 60;
        this.setLayout(null);
        this.setBounds(0,0,width,height);
        this.setTitle(title);
        blocks.setSize(500, 300);
        blocks.setLocation(150, 5);
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                blocks.add(new Block(blockWidth,blockHeight));
                System.out.println("Tworze obiekt block");
                System.out.println(blockWidth);
            }
        }
        
        //blocks.add(new Block(60,60));
        blocks.add(new Block(120,60));
        this.add(blocks);
        this.add(paddle);
        this.setVisible(true);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public GameWindow(int width,int height,String title)
    {
        Init(width,height,title);
    }
    
        
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        switch(keyCode)
        {
            case KeyEvent.VK_LEFT:
                if(paddle.GetX()>=30)
                {
                    paddle.UpdateX(-15);
                    this.repaint();
                    System.out.println("W lewo");
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(paddle.GetX()<=600)
                {
                    paddle.UpdateX(15);
                    this.repaint();
                    System.out.println("W prawo");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
