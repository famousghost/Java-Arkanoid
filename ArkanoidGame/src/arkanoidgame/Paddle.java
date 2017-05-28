/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Marcin
 */
public class Paddle extends JPanel {
    
    private int X;
    private int paddleHeight;
    private int paddleWidth;
    
    
    private void Init(int width,int height)
    {
        paddleWidth = width;
        paddleHeight = height;
        X=30;
        this.setLayout(null);
        this.setSize(800,600);
        this.setLocation(0, 0);
        this.setBackground(Color.black);
        this.setVisible(true);
        
    }
    
    public Paddle(int width,int height)
    {
        Init(width,height);
    }
        
    @Override
    public void paint(Graphics g) {
       super.paintComponents(g);
       Graphics2D rectangle = (Graphics2D)g;
       rectangle.fillRect(X,500,paddleWidth , paddleHeight);
       rectangle.drawRect(5, 0, 765,600);
   }
    
    public void UpdateX(int x)
    {
        X+=x;
    }
    
    public int GetX()
    {
        return X;
    }

}
