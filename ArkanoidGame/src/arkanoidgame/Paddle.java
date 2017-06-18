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
   
    private int positionX;
    private int positionY;
    private int paddleHeight;
    private int paddleWidth;
    
    private void Init(int positionX,int positionY,int width,int height)
    {
        paddleWidth = width;
        paddleHeight = height;
        
        this.positionX = positionX;
        this.positionY = positionY;
        
        this.setLayout(null);
        this.setSize(800,600);
        this.setLocation(0, 0);
        this.setVisible(true);
        
    }
    
    public Paddle(int positionX,int positionY,int width,int height)
    {
        Init(positionX,positionY,width,height);
    }
        
    @Override
    public void paint(Graphics g) {
       super.paintComponents(g);
       g.setColor(Color.white);
       Graphics2D rectangle = (Graphics2D)g;
       rectangle.fillRect(positionX,positionY,paddleWidth , paddleHeight);
   }
    
    public void MovePaddle(int x)
    {
        positionX+=x;
    }
    
    public int GetPositionX()
    {
        return positionX;
    }
    
    public int GetPositionY()
    {
        return positionY;
    }
    
    public void SetPositionX(int positionX)
    {
        this.positionX = positionX;
    }

}
