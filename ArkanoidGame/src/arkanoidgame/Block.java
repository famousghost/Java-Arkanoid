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
public class Block extends JPanel {

    private int positionX;
    private int positionY;
    private boolean isExist;
    
    private void Init(int positionX,int positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        isExist = true;
        this.setBounds(0,0, 800, 600);
        this.setVisible(true);
        
    }
    
    public Block(int positionX,int positionY)
    {
        Init(positionX,positionY);
    }
    
    @Override
    public void paint(Graphics g) {
    if(isExist)
    {
        super.paintComponents(g);
        g.setColor(Color.yellow);
        Graphics2D rectangle = (Graphics2D)g;
        rectangle.fillRect(positionX,positionY,60 , 60);
    }
   }
    
    public void SetPosition(int positionX,int positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;
    }
    
    public void Remove()
    {
        isExist = false;
    }
    
    public boolean GetIsExist()
    {
        return isExist;
    }
    
    public void SetExist(boolean isExist)
    {
        this.isExist = isExist;
    }
    
    public int GetPositionX()
    {
        return positionX;
    }
    public int GetPositionY()
    {
        return positionY;
    }
}
