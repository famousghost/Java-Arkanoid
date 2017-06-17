/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Marcin
 */
public class GameWindow extends JFrame implements KeyListener,ActionListener {
    
    //Blocks Property
    JPanel blocks = new JPanel(new GridLayout(4,4));
    private int blockWidth;
    private int blockHeight;
    
    //Screen Property
    private int ScreenWidth = 800;
    private int ScreenHeight = 600;
    
    //ball Property
    private final int ballR = 25;
    private int ballMoveX = 1;
    private int ballMoveY = -1;
    private boolean runBall = false;
    
    //Paddle Property
    private final int paddleWidth = 130;
    private final int paddleHeight = 25;
    private int paddlePositionX = ScreenWidth/2-20;
    private final int paddlePositionY = ScreenHeight-100;
    private int paddleMove = 5;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    
    //Loop timer to my game
    Timer loopTimer;
    
    //instance of paddle JPanel
    Paddle paddle = new Paddle(paddlePositionX,paddlePositionY,paddleWidth,paddleHeight);
    
    //instace of ball JPanel
    Ball ball = new Ball(ballR,paddlePositionX+50,paddlePositionY-30);
    
    private void Init(int width,int height,String title)
    {
        blockWidth = 60;
        blockHeight = 60;
        ScreenWidth = width;
        ScreenHeight = height;
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
        //this.add(blocks);
        this.add(paddle);
        this.add(ball);
        this.setVisible(true);
        this.setResizable(false);
        this.addKeyListener(this);
        
        //Instance of timer
        loopTimer = new Timer();
        
        //Main loop of my game

            loopTimer.scheduleAtFixedRate(new TimerTask()
            {
                @Override
                public void run() 
                {
                    MovePaddle();
                    if(runBall)
                    {
                        MoveBall();
                    }
                    else
                    {
                        AttachBall();
                    }
                    repaint();
                }
            },0,6);
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
                //Move to left side of screen
                    moveLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                //Move to right side of screen
                    moveRight = true;
                break;
            case KeyEvent.VK_SPACE:
                runBall = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        switch(keyCode)
        {
            case KeyEvent.VK_LEFT:
                //Move to left side of screen
                moveLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                //Move to right side of screen
                 moveRight = false;
                break;
        }
    }
    @Override
    public void paint(Graphics g) {
       super.paintComponents(g);
       Graphics2D rectangle = (Graphics2D)g;
       rectangle.drawRect(15,0 ,ScreenWidth-30 ,ScreenHeight);
   }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
    
    private void MoveBall()
    {
        if((ball.GetPositionX()<=15) || ((ball.GetPositionX()+ballR)>=ScreenWidth-25))
        {
            ballMoveX=-ballMoveX;
        }
        
        if((ball.GetPositionY()<=5))
        {
            ballMoveY=-ballMoveY;
        }
        if((ball.GetPositionY()+ballR)>=ScreenHeight-30)
        {
            runBall = false;
            ballMoveY=-ballMoveY;
        }
        boolean ballTouchPaddleLeftSide =  ball.GetPositionY() == paddle.GetPositionY() - 20;
        if(ballTouchPaddleLeftSide)
        { 
            //Left side of paddle ball go to left
            boolean bouncyLeftSide =(ball.GetPositionX() >= paddle.GetPositionX()) && (ball.GetPositionX() <=(paddle.GetPositionX() + paddleWidth/2));
            //Right side of paddle ball go to right
            boolean bouncyRightSide =(ball.GetPositionX() >= (paddle.GetPositionX()+paddleWidth/2)) && (ball.GetPositionX() <=(paddle.GetPositionX() + paddleWidth));
            if(bouncyLeftSide)
            {
                ballMoveY=-ballMoveY;
                ballMoveX = -1;
            }
            if(bouncyRightSide)
            {
                ballMoveY=-ballMoveY;
                ballMoveX = 1;
            }
        }
    
        
        ball.BallMove(ballMoveX, ballMoveY);
    }
    
    private void AttachBall()
    {
        ball.SetBallPosition((paddle.GetPositionX()+50), (paddle.GetPositionY()-30));
    }
      
    private void MovePaddle()
    {
        if(moveLeft)
        {
            if(paddle.GetPositionX()>=30)
                paddle.MovePaddle(-paddleMove);
            
        }
        if(moveRight)
        {
            if((paddle.GetPositionX()+paddleWidth)<=ScreenWidth-30)
                paddle.MovePaddle(paddleMove);
        }
    }
}
