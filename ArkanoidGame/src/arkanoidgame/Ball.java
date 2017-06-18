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
public class Ball extends JPanel {
    private int positionX;
    private int positionY;
    private int ballR;
    
    private void Init(int ballR,int positionX,int positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.ballR = ballR;
        this.setLayout(null);
        this.setSize(800,600);
        this.setLocation(0, 0);
        this.setBackground(Color.black);
        this.setVisible(true);
    }
    
    public Ball(int ballR,int moveX,int moveY)
    {
        Init(ballR,moveX,moveY);
    }
    
    @Override
    public void paint(Graphics g) {
       super.paintComponents(g);
       g.setColor(Color.white);
       Graphics2D rectangle = (Graphics2D)g;
       rectangle.fillOval(positionX,positionY,ballR,ballR);
   }
    
    public void BallMove(int x,int y)
    {
        positionX+=x;
        positionY+=y;
    }
    
    public int GetPositionX()
    {
        return positionX;
    }
    
    public int GetPositionY()
    {
        return positionY;
    }
    
    public void SetBallPosition(int X,int Y)
    {
        positionX = X;
        positionY = Y;
    }

    
    
}
