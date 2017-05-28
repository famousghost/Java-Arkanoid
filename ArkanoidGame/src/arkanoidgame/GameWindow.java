/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Marcin
 */
public class GameWindow extends JFrame implements KeyListener {
    
    private int X;
    private int paddleHeight;
    private int paddleWidth;
    
    JPanel paddle = new JPanel();
    
    private void Init(int width,int height,String title)
    {
        paddleHeight = height;
        paddleWidth = width;
        X = (width/2)-70;
        this.setLayout(null);
        this.setBounds(0,0,width,height);
        this.setTitle(title);
        paddle.setBounds((width/2)-70,paddleHeight-150,800, 40);
        //this.add(paddle);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(this);
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
                if(X>=30)
                {
                    X-=10;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(X<=(paddleWidth-180))
                {
                    X+=10;
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
    
    @Override
     public void update(Graphics g) {
         
    }
    
    @Override
    public void paint(Graphics g) {
       super.paintComponents(g);
       Graphics2D rectangle = (Graphics2D)g;
       rectangle.fillRect(X,paddleHeight-100,150 , 30);
       rectangle.drawRect(15, 0, paddleWidth-30,paddleHeight);
       repaint();
   }
    
    
}
