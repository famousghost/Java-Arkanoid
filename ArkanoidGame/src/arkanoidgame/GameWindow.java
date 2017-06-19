/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author Marcin
 */
public class GameWindow extends JFrame implements KeyListener{
    
    //Instance of GameWindow
    static GameWindow gameWidnow = new GameWindow(800,600,"Arkanoid Gra");
    
    //Instance of SaveToFile
    private SaveToFile save = new SaveToFile();
    private ReadFromFile read = new ReadFromFile();
    
    //Label
    JLabel scoreLabel = new JLabel();
    JLabel failedLabel = new JLabel();
    JLabel infoLabel = new JLabel("Space: Push Ball, Move:(LeftArrow-RightArrow)");
    JLabel gameOver = new JLabel("Game Over");
    JLabel restartInfo = new JLabel("To Restart Click (R)");
    
    //Player
    private int failedTimes;
    private int score;
    private boolean restartBool = false;
    private boolean pause = false;
    private boolean over = false;
    static int[] scoreArray = new int[3];
    
    //Blocks Property
    private Block[] block = new Block[12];
    private int blockWidth;
    private int blockHeight;
    private int blockPositionX;
    private int blockPositionY;
    private int blockCount;
    private int destroyedBlocks;
    
    //Screen Property
    private int ScreenWidth = 800;
    private int ScreenHeight = 600;
    
    //ball Property
    private final int ballR = 25;
    private int ballMoveX = 1;
    private int ballMoveY = -1;
    private int moveX = 1;
    private int moveY = -1;
    private boolean runBall = false; 
    private int speed;
    private int countOfBouncy;
    
    //Paddle Property
    private final int paddleWidth = 130;
    private final int paddleHeight = 25;
    private int startPaddlePostionX = ScreenWidth/2-20;
    private int paddlePositionX = ScreenWidth/2-20;
    private final int paddlePositionY = ScreenHeight-100;
    private int paddleMove = 5;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    
    //Loop timer to my game
    Timer loopTimer;
    
    //instance of paddle JPanel
    Paddle paddle = new Paddle(paddlePositionX,paddlePositionY,paddleWidth-10,paddleHeight);
    
    //instace of ball JPanel
    Ball ball = new Ball(ballR,paddlePositionX+50,paddlePositionY-30);
    
    private void Init(int width,int height,String title)
    {
        blockCount = 6;
        countOfBouncy = 0;
        failedTimes = 3;
        speed = 1;
        score = 0;
        destroyedBlocks = 0;
        blockWidth = 60;
        blockHeight = 60;
        blockPositionX = ScreenWidth/2-220;
        blockPositionY = 20;
        ScreenWidth = width;
        ScreenHeight = height;
        scoreLabel.setBounds(ScreenWidth-100, 0, 100, 50);
        infoLabel.setBounds(ScreenWidth-300, ScreenHeight-80, 400, 50);
        failedLabel.setBounds(40, 0, 100, 50);

        
        //Instance of timer
        loopTimer = new Timer();
        
        //Main loop of my game
        AddElements(width,height,title);
            loopTimer.scheduleAtFixedRate(new TimerTask()
            {
                @Override
                public void run() 
                {
                    if(failedTimes >=0)
                    {
                        Window.GetWindowInstance().CheckPauseGame();
                        if(pause == false)
                        {
                            MovePaddle();
                            RestartBlocks();
                            scoreLabel.setText("Score: " + score);
                            failedLabel.setText("Lives: " + failedTimes);
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
                    }
                    else if(failedTimes <0 && over == false)
                    {
                        read.Read();
                        int licznik = 0;
                        for(int i=0;i<3;i++)
                        {
                            scoreArray[i] = read.scoresIntArray[i];
                        }
                        for(int i=0;i<3;i++)
                        {
                            if(score>scoreArray[i])
                            {
                                licznik++;
                            }
                        }
                        if(licznik>0)
                        {
                            
                            //Bardzo prymitywne ale nie miałem pomysłu na pętle a chciałem tylko 3 największe wyniki
                            int j = licznik-1;
                            int k = 0;
                            while(j>0)
                            {
                                
                                scoreArray[k] = scoreArray[k+1];
                                k++;
                                j--;
                            }
                            scoreArray[licznik-1] = score;

  
                            String[] scores = new String[3];
                            for(int i=0;i<3;i++)
                            {
                                StringBuilder sb = new StringBuilder();
                                sb.append("");
                                sb.append(scoreArray[i]);
                                scores[i] = sb.toString(); 
                            }
                            save.Save(scores);
                        }
                        getContentPane().removeAll();
                        getContentPane().repaint();
                        getContentPane().setBackground(Color.BLACK);
                        gameOver.setBounds(370,100,300,300);
                        restartInfo.setBounds(350,150,300,300);
                        getContentPane().add(gameOver); 
                        getContentPane().add(restartInfo);
                        over = true;

                    }
                    if(restartBool)
                    {
                        //Restart function
                        getContentPane().removeAll();
                        getContentPane().repaint();
                        AddElements(width,height,title);
                        failedTimes = 3;
                        countOfBouncy = 0;
                        destroyedBlocks = 0;
                        score = 0;
                        moveY = -1;
                        moveX = 1;
                        restartBool=false;
                        pause = false;
                        runBall = false;
                        paddle.SetPositionX(startPaddlePostionX);
                        over= false;
                    }
                }
            },0,6);
            
        
    }
    
    private GameWindow(int width,int height,String title)
    {
        Init(width,height,title);
    }
    
    private void AddElements(int width,int height,String title)
    {
        this.setLayout(null);
        this.setBounds(Window.GetHalfOfScreenWidth(),Window.GetHalfOfScreenHeight(),width,height);
        this.setTitle(title);
        this.add(paddle);
        this.add(ball);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setResizable(false);
        this.addKeyListener(this);
        this.add(scoreLabel);
        this.add(failedLabel);
        this.add(infoLabel);
        CreateBlocks();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
            case KeyEvent.VK_R:
                restartBool = true;
                break;
             case KeyEvent.VK_ESCAPE:
                pause = true;
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
            failedTimes--;
            ballMoveY=-ballMoveY;
        }
        boolean ballTouchTopOfPaddle =  ball.GetPositionY() == (paddle.GetPositionY() - 15);
        if(ballTouchTopOfPaddle)
        {   
            int left = paddle.GetPositionX();
            int middleLeft = paddle.GetPositionX() + paddleWidth/4;
            int middle = paddle.GetPositionX() + paddleWidth/2;
            int middleRight = paddle.GetPositionX() + (3*paddleWidth)/4;
            int right = paddle.GetPositionX() + paddleWidth;
            
            //Left side of paddle ball go to left
            boolean boucyLeft = (ball.GetPositionX() >= left) && (ball.GetPositionX() <= middleLeft);
            boolean boucyMiddleLeft = (ball.GetPositionX() >= middleLeft) && (ball.GetPositionX() <= middle);
            boolean bouncyMiddleRight = (ball.GetPositionX() >= middle) && (ball.GetPositionX() <= middleRight);
            boolean bouncyRight = (ball.GetPositionX() >= middleRight) && (ball.GetPositionX() <= right);
            //boolean bouncyLeftSide =(ball.GetPositionX() >= paddle.GetPositionX()) && (ball.GetPositionX() <=(paddle.GetPositionX() + paddleWidth/2));
            //Right side of paddle ball go to right
            //boolean bouncyRightSide =(ball.GetPositionX() > (paddle.GetPositionX()+paddleWidth/2)) && (ball.GetPositionX() <= (paddle.GetPositionX() + paddleWidth));
            if(boucyLeft)
            {
                ballMoveY = moveY;
                if(countOfBouncy < 2)
                {
                    moveY-=speed;
                    moveX+=speed;
                    countOfBouncy++;
                }
                ballMoveX = -moveX;
                
            }
            if(boucyMiddleLeft)
            {
                ballMoveY = (moveY *2);
                if(countOfBouncy < 2)
                {
                    moveY-=speed;
                    moveX+=speed;
                    countOfBouncy++;
                }
                ballMoveX = -moveX;
                
            }
            if(bouncyMiddleRight)
            {
                ballMoveY = (moveY * 2);
                if(countOfBouncy < 2)
                {
                    moveY-=speed;
                    moveX+=speed;
                    countOfBouncy++;
                }
                ballMoveX = moveX;
                
            }
            if(bouncyRight)
            {
                ballMoveY = moveY;
                if(countOfBouncy < 2)
                {
                    moveY-=speed;
                    moveX+=speed;
                    countOfBouncy++;
                }
                ballMoveX = moveX;
            }
        }
        ball.BallMove(ballMoveX, ballMoveY);
        for(int i=0;i<12;i++)
        {
            CheckCollision(block[i]);
        }
    }
    
    private void AttachBall()
    {
        Random ran = new Random();
        int x = ran.nextInt(3) + (-1);
        moveY = -1;
        moveX = 1;
        ballMoveY = -1;
        if(x == 0)
        {
            x=x+1;
        }
        ballMoveX = x;
        countOfBouncy = 0;
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
    
    private void CheckCollision(Block block)
    {
        if(block.GetIsExist())
        {
            boolean checkPositionX = (ball.GetPositionX() >= block.GetPositionX()) && (ball.GetPositionX() <= (block.GetPositionX()+ blockWidth));
            boolean checkPositionY = (ball.GetPositionY() >= (block.GetPositionY()-10)) && (ball.GetPositionY() <= (block.GetPositionY() + (blockHeight+10)));
            if(checkPositionX && checkPositionY)
            {
                ballMoveX = -ballMoveX;
                ballMoveY = -ballMoveY;
                block.Remove();
                score++;
                destroyedBlocks++;
                System.out.println(destroyedBlocks);
            }
                
        }
    }
    
    private void CreateBlocks()
    {
        blockPositionX = ScreenWidth/2-220;
        blockPositionY = 20;
        for(int i=0;i<12;i++)
        {
            block[i] = new Block(blockPositionX,blockPositionY);
            blockPositionX+=70;
            this.add(block[i]);
            block[i].SetExist(true);
            if(i == blockCount-1)
            {
                blockPositionX = ScreenWidth/2-150;
                blockPositionY += 70;
            }
            else if(i== blockCount+3)
            {
                blockPositionX = ScreenWidth/2-80;
                blockPositionY += 70;
            }
        }
    }
    
    private void RestartBlocks()
    {
         if(destroyedBlocks == 12)
         {
             for(int i=0;i<12;i++)
             {
                 block[i].SetExist(true);
             }
             destroyedBlocks=0;
         }
    }
    
    public boolean GetPause()
    {
        return pause;
    }
    
    public void SetPause(boolean pause)
    {
        this.pause = pause;
    }
    
    public static GameWindow GetInstanceGameWindow()
    {
        if(gameWidnow == null)
        {
            gameWidnow = new GameWindow(800,600,"Arkanoid");
        }
        return gameWidnow;
    }
    
    public void SetSizeOfGameWindow(int positionX,int positionY)
    {
        this.setLocation(positionX, positionY);
    }
}
