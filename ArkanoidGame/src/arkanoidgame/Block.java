/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Marcin
 */
public class Block extends JPanel {

    
    private void Init(int width,int height)
    {
 
        this.setBounds(0,0, width, height);
        this.setVisible(true);
        
    }
    
    public Block(int width,int height)
    {
        Init(width,height);
    }
    
    @Override
    public void paint(Graphics g) {
    super.paintComponents(g);
    Graphics2D rectangle = (Graphics2D)g;
    rectangle.fillRect(0,0,60 , 60);
   }
}
