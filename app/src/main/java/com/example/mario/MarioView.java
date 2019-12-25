package com.example.mario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class MarioView extends View {
    private int sPowerUp=0;
    private int mPowerUp=0;
    private int lives=3;
    private int pUp=0;
    private int piranha=0;
    private int timer=2;
    private  int gameOver=0;
    private String errMessage="";
    private int score=0;
    private int xLoc=10;
    private Rect jumpBtn;
    private int yLoc=2;
    private Paint paint;
    private final int squareSize = 80;
    private Rect[][] rectArr = new Rect[100][11];
    private int[][] boardArr= new int[100][11];
    private int[][] level1= new int[100][11];
    private int[][] level2= new int[100][11];
    private int[][] level3= new int[100][11];

    private Bitmap marioStar = BitmapFactory.decodeResource(getResources(), R.drawable.mariosuper);
    private Bitmap marioRun = BitmapFactory.decodeResource(getResources(), R.drawable.marioicn);

    private Bitmap marioJmp = BitmapFactory.decodeResource(getResources(), R.drawable.mariojmp);

    private Bitmap floor = BitmapFactory.decodeResource(getResources(), R.drawable.floor);
    //private Bitmap sky = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
    private Bitmap brick = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
    private Bitmap question = BitmapFactory.decodeResource(getResources(), R.drawable.question);
    private Bitmap usedQuestion = BitmapFactory.decodeResource(getResources(), R.drawable.usedquestion);
    private Bitmap pipeBL = BitmapFactory.decodeResource(getResources(), R.drawable.pbl);
    private Bitmap pipeBR = BitmapFactory.decodeResource(getResources(), R.drawable.pbr);
    private Bitmap pipeTL = BitmapFactory.decodeResource(getResources(), R.drawable.ptl);
    private Bitmap pipeTR = BitmapFactory.decodeResource(getResources(), R.drawable.ptr);
    private Bitmap coin = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
    private Bitmap goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);

    private Bitmap piranhaLeft = BitmapFactory.decodeResource(getResources(), R.drawable.piranhaleft);
    private Bitmap piranhaRight = BitmapFactory.decodeResource(getResources(), R.drawable.piranharight);

    private Bitmap mushroom = BitmapFactory.decodeResource(getResources(), R.drawable.mushroom);
    private Bitmap star = BitmapFactory.decodeResource(getResources(), R.drawable.star);


    private Bitmap marioBM=marioRun;

    private int currentLevel=1;
    private float x;
    private float y;
    private int marioY=0;
    private int leftPos=0;
    private Rect mario;
    private Paint marioPaint;
    private int moving=0;

    public MarioView(Context context) {
        super(context);
        init(null);
    }

    public MarioView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public MarioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    public MarioView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);

    }

    private void init(@Nullable AttributeSet set) {

        //Initializing things to draw/defining colors
        mario=new Rect();
        jumpBtn=new Rect();
        marioPaint=new Paint();
        marioPaint.setColor(Color.BLUE);


        //-------------------------------LEVEL 1 CODE-------------------------------//
        //setting all blocks to be sky
        for (int i = 0; i < rectArr.length; i++) {
            for (int j = 0; j < rectArr[0].length; j++) {
                rectArr[i][j] = new Rect();
                level1[i][j]=0;
            }
        }


        //setting block to jump on
        level1[15][5]=2;
        level1[16][5]=2;
        level1[17][5]=2;
        level1[18][5]=2;
        //coins on block
        level1[15][4]=-2;
        level1[16][4]=-2;
        level1[17][4]=-2;
        level1[18][4]=-2;

        level1[21][4]=2;
        level1[22][4]=2;
        level1[23][4]=3;
        level1[24][4]=2;

        level1[21][3]=-2;
        level1[22][3]=-2;
        //level1[23][3]=-2;
        level1[24][3]=-2;


        //setting floor
        for (int i = 0; i < rectArr.length; i++) {
            for (int j = 7; j < rectArr[0].length; j++) {
                level1[i][j]=1;
            }
        }

        //setting barrier
        for (int j =0; j < 7; j++) {
            level1[5][j]=10;
        }

        //Gap in the ground
        for (int i = 28; i < 36; i++) {
            for (int j = 4; j < rectArr[0].length; j++) {
                level1[i][j]=0;
            }
        }

        for (int i = 28; i < 36; i++) {
            level1[i][10]=-1;
        }

        //to cross gap
        level1[30][6]=2;
        level1[31][6]=2;
        level1[32][6]=2;

        //pyramid
        level1[39][6]=2;
        level1[40][6]=2;
        level1[41][6]=2;
        level1[42][6]=2;
        level1[43][6]=2;

        level1[40][5]=2;
        level1[41][5]=2;
        level1[42][5]=2;
        level1[43][5]=2;

        level1[41][4]=2;
        level1[42][4]=2;
        level1[43][4]=2;

        level1[42][3]=2;
        level1[43][3]=2;

        //level1[43][2]=2;

        //pipe
        level1[47][5]=5;
        level1[46][5]=6;
        level1[47][6]=7;
        level1[46][6]=8;

        level1[57][6]=7;
        level1[56][6]=8;
        level1[57][5]=7;
        level1[56][5]=8;
        level1[57][4]=5;
        level1[56][4]=6;

        level1[67][5]=5;
        level1[66][5]=6;
        level1[67][6]=7;
        level1[66][6]=8;


        //goombas
        level1[85][6]=-3;
        level1[75][6]=-3;

        level1[79][6]=-2;
        level1[80][6]=-2;
        level1[81][6]=-2;
        //-------------------------------LEVEL 1 CODE-------------------------------//
        //-------------------------------LEVEL 2 CODE-------------------------------//
        //setting barrier
        for (int j =0; j < 7; j++) {
            level2[5][j]=10;
        }

        //setting floor
        for (int i = 0; i < rectArr.length; i++) {
            for (int j = 7; j < rectArr[0].length; j++) {
                level2[i][j]=1;
            }
        }

        //Gap in the ground
        for (int i = 20; i < 50; i++) {
            for (int j = 4; j < rectArr[0].length; j++) {
                level2[i][j]=0;
            }
        }

        level2[23][3]=2;
        level2[24][3]=3;
        level2[25][3]=2;
        level2[26][3]=2;


        level2[22][6]=2;
        level2[23][6]=2;
        level2[24][6]=2;
        level2[25][6]=2;
        level2[26][6]=2;
        level2[27][6]=2;

        level2[22][5]=-2;
        level2[23][5]=-2;
        level2[24][5]=-2;
        level2[25][5]=-2;
        level2[26][5]=-2;
        level2[27][5]=-2;

        //level2[29][5]=2;
        level2[30][5]=2;
        level2[31][5]=2;
        level2[32][5]=2;
        //level2[33][5]=2;


        level2[35][6]=2;
        level2[36][6]=2;
        level2[37][6]=2;
        level2[38][6]=2;
        //level2[39][6]=2;

        //PIPE
        level2[36][5]=8;
        level2[37][5]=7;
        level2[36][4]=6;
        level2[37][4]=5;

        level2[39][3]=2;
        level2[40][3]=2;
        level2[41][3]=2;
        level2[42][3]=2;
        level2[43][3]=2;
        level2[44][3]=2;
        level2[45][3]=2;
        level2[46][3]=2;

        //pipe
        level2[57][6]=7;
        level2[56][6]=8;
        level2[57][5]=7;
        level2[56][5]=8;
        level2[57][4]=5;
        level2[56][4]=6;


        //coins
        level2[60][5]=-2;
        level2[61][5]=-2;
        level2[62][5]=-2;
        level2[64][6]=-3;

        level2[67][5]=5;
        level2[66][5]=6;
        level2[67][6]=7;
        level2[66][6]=8;

        level2[70][5]=-2;
        level2[71][5]=-2;
        level2[72][5]=-2;
        level2[73][5]=-2;
        level2[74][5]=-2;

        level2[79][4]=2;
        level2[80][4]=2;
        level2[81][4]=2;
        level2[82][4]=3;
        level2[83][4]=2;
        level2[84][4]=2;

        //-------------------------------LEVEL 2 CODE-------------------------------//
        //-------------------------------LEVEL 3 CODE-------------------------------//
        //setting floor
        for (int i = 0; i < rectArr.length; i++) {
            for (int j = 7; j < rectArr[0].length; j++) {
                level3[i][j]=1;
            }
        }

        level3[23][3]=2;
        level3[24][3]=3;
        level3[25][3]=2;
        level3[26][3]=2;

        level3[24][6]=-3;

        level3[28][4]=2;
        level3[29][4]=2;
        level3[30][4]=2;
        level3[31][4]=2;

        //pipe
        level3[37][6]=7;
        level3[36][6]=8;
        //level3[37][5]=7;
        //level3[36][5]=8;
        level3[37][5]=5;
        level3[36][5]=6;



        //Gap in the ground
        for (int i = 45; i < 50; i++) {
            for (int j = 4; j < rectArr[0].length; j++) {
                level3[i][j]=0;
            }
        }
        level3[47][10]=7;
        level3[46][10]=8;
        level3[47][9]=7;
        level3[46][9]=8;
        level3[47][8]=7;
        level3[46][8]=8;
        level3[47][7]=7;
        level3[46][7]=8;
        level3[47][6]=7;
        level3[46][6]=8;
        level3[47][5]=5;
        level3[46][5]=6;

        level3[50][6]=-2;
        level3[51][6]=-2;
        level3[52][6]=-2;
        level3[53][6]=-2;
        level3[54][6]=-2;

        //pipe
        level3[61][6]=7;
        level3[60][6]=8;
        level3[61][5]=7;
        level3[60][5]=8;
        level3[61][4]=5;
        level3[60][4]=6;


        level3[64][6]=-3;
        level3[69][6]=-3;
        level3[74][6]=-3;

        level3[80][6]=-2;
        level3[81][6]=-2;
        level3[82][6]=-2;
        level3[83][6]=-2;
        level3[84][6]=-2;

        //-------------------------------LEVEL 3 CODE-------------------------------//

        levelUp(level1);
        currentLevel--;
        paint=new Paint();
        //checkDeath();
        gravity();              //Gravity is constantly running so that mario falls if there is nothing beneath him

    }

    private void invincible() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    marioJmp=marioStar;
                    try {
                        Thread.sleep(10000);

                    } catch (InterruptedException e) {

                    }
                    sPowerUp=0;
                    marioJmp = BitmapFactory.decodeResource(getResources(), R.drawable.mariojmp);
                }
            }
        });
        thread.start();
    }

    private void checkDeath() {
        //Code for death jump
        /*for(int i=0;i<160;i++) {
            try {
                marioY--;
                Thread.sleep(1);
                postInvalidate();

            } catch (InterruptedException e) {
            }
        }
        while(marioY<800) {
            try {
                marioY+=2;
                Thread.sleep(1);
                postInvalidate();

            } catch (InterruptedException e) {
            }
        }*/
        if(mPowerUp==0 && sPowerUp==0) {
            if (lives == 1) {
                gameOver();
            } else {
                lives--;
                if (currentLevel == 1) {
                    levelUp(level1);
                } else if (currentLevel == 2) {
                    levelUp(level2);
                } else if (currentLevel == 3) {
                    levelUp(level3);
                }
                currentLevel--;

            }
        }
        else if(mPowerUp==1 && sPowerUp==0){
            mPowerUp=0;
        }
    }

    private void levelUp(int[][] arr) {
        currentLevel++;
        xLoc=10;
        yLoc=2;
        marioY=0;
        leftPos=0;
        moving=0;
        for (int i = 0; i < rectArr.length; i++) {
            for (int j = 0; j < rectArr[0].length; j++) {
                boardArr[i][j]=arr[i][j];

            }
        }
    }

    private void piranhaPlant(final int X,final int Y) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                pUp=1;
                synchronized (this) {
                    while (xLoc-X<5 && xLoc-X>-5) {
                        boardArr[X][Y-1]=-1;
                        boardArr[X-1][Y-1]=-1;

                        for (int i = 0; i < 80; i++) {
                            piranha++;
                            try {
                                Thread.sleep(15);
                                postInvalidate();

                            } catch (InterruptedException e) {

                            }
                        }
                        for (int i = 0; i < 80; i++) {
                            piranha--;
                            try {
                                Thread.sleep(5);
                                postInvalidate();

                            } catch (InterruptedException e) {

                            }
                        }
                        piranha = 0;
                        boardArr[X][Y-1]=0;
                        boardArr[X-1][Y-1]=-0;

                        try {
                           // boardArr[X][Y]=0;
                            postInvalidate();

                            Thread.sleep(1500);

                        } catch (InterruptedException e) {

                        }

                    }
                    pUp = 0;

                }

            }
        });
        thread.start();
    }

    private void gameOver() {
        score=0;
        lives=3;
        xLoc=10;
        yLoc=2;
        marioY=0;
        leftPos=0;
        moving=0;
        levelUp(level1);
        currentLevel=1;

        errMessage="Game Over";
    }


    private void jump() {           //Makes mario jump
        Thread thread = new Thread(new Runnable() {             //start a new thread

            int init=yLoc;
            int diff=0;
            @Override
            public void run() {
                synchronized (this) {
                    marioBM=marioJmp;
                    while (diff < 4) {             //keep moving up unless there is a block above mario or he reaches maximum height of 4
                        if(boardArr[xLoc+1][(marioY-1)/squareSize]==2) {
                            boardArr[xLoc+1][(marioY-1)/squareSize]=0;
                            score+=10;
                            break;

                        }
                        else if(boardArr[xLoc+1][(marioY-1)/squareSize]==3) {
                            boardArr[xLoc+1][(marioY-1)/squareSize]=4;
                            Random rand = new Random();
                            int n = rand.nextInt(2);
                            if(n==0) {
                                boardArr[xLoc + 1][(marioY - 1) / squareSize - 1] = -5;
                            }
                            else {
                                boardArr[xLoc + 1][(marioY - 1) / squareSize - 1] = -4;
                            }
                            //Add power up on block above
                        }

                        if(boardArr[xLoc][(marioY-1)/squareSize]>0 || boardArr[xLoc+1][(marioY-1)/squareSize] > 0) {
                            break;
                        }

                        marioY -= 4;                                            //move 4 pixels up every 1 milliseconds (technically 2 since gravity pulls down 2)
                        if(marioY<50) {
                            marioY+=4;
                            break;
                        }
                        yLoc = marioY / squareSize;                             //track y coordinate of mario in the array
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {

                        }
                        diff = init - yLoc;
                        if (diff == 4) {
                            break;
                        }
                        postInvalidate();           //Draw on board

                    }
                    marioBM=marioRun;
                }
            }
        });
        thread.start();
    }

    private void gravity() {
        Thread thread = new Thread(new Runnable() {
            int temp;
            @Override
            public void run() {
                synchronized (this) {
                    while (true) {              //run constantly once called
                        while (boardArr[xLoc][yLoc + 1] <= 0) {         //while block below mario is sky
                            if(boardArr[xLoc][(marioY+squareSize)/squareSize]>0 || boardArr[xLoc+1][(marioY+squareSize)/squareSize] > 0) {
                                break;
                            }
                            marioY += 2;                        //move 2 pixels down every 1 millisecond

                            temp = marioY / squareSize;         //track mario's y location
                            if(boardArr[xLoc][yLoc+1]==-1) {
                                checkDeath();
                                continue;
                            }
                            else if(boardArr[xLoc][yLoc]==-1) {
                                checkDeath();
                                continue;
                            }
                            if(temp>8) {
                                sPowerUp=0;
                                checkDeath();
                                continue;
                                //break;
                            }
                            yLoc=temp;
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {

                            }
                            postInvalidate();       //draw on board
                        }

                    }
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(boardArr[xLoc][yLoc+1] ==-1 || boardArr[xLoc][yLoc]==-1) {       //piranha
            checkDeath();
        }
        if(boardArr[xLoc][yLoc] ==-2) {     //coins
            score+=200;
            boardArr[xLoc][yLoc] = 0;
        }
        if(boardArr[xLoc][yLoc] == -3 || boardArr[xLoc+1][yLoc]== -3) {         //goomba from sides
            checkDeath();
        }
        if(boardArr[xLoc][yLoc+1] ==-3) {           //goomba from top
            boardArr[xLoc][yLoc+1] =0;
            score+=100;
        }
        if(boardArr[xLoc+1][yLoc+1] ==-3) {           //goomba from top
            boardArr[xLoc+1][yLoc+1] =0;
            score+=100;
        }

        if(boardArr[xLoc][yLoc] ==-4) {             //mushroom
            boardArr[xLoc][yLoc] =0;
            score+=1000;
            mPowerUp=1;
            boardArr[xLoc][yLoc] =0;
        }
        if(boardArr[xLoc][yLoc] ==-5) {             //starman
            boardArr[xLoc][yLoc] =0;
            score+=1000;
            sPowerUp=1;
            invincible();
        }

        if(xLoc>=90) {
            if(currentLevel==1) {
                levelUp(level2);
                errMessage="Level 2!";

            }
            else if(currentLevel==2) {
                levelUp(level3);
                errMessage="Level 3!";

            }
            else {
                levelUp(level1);
                errMessage="You Win!";
                score=0;
                lives=3;
                currentLevel=1;
            }

        }
        canvas.drawColor(Color.parseColor("#99C0FF"));
        for (int i = 0; i < rectArr.length; i++) {
            for (int j = 0; j < rectArr[0].length; j++) {

                rectArr[i][j].top = squareSize * j;                         //Draw each square in the array and set the colors according to the value in the array
                rectArr[i][j].left = leftPos + squareSize * i;
                rectArr[i][j].right = rectArr[i][j].left + squareSize;
                rectArr[i][j].bottom = rectArr[i][j].top + squareSize;

                if(boardArr[i][j]==1) {
                    canvas.drawBitmap(floor, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==0) {
                    //canvas.drawBitmap(sky, rectArr[i][j].left, rectArr[i][j].top, null);

                }
                else if(boardArr[i][j]==2) {
                    canvas.drawBitmap(brick, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==3) {
                    canvas.drawBitmap(question, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==4) {
                    canvas.drawBitmap(usedQuestion, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==5) {
                    canvas.drawBitmap(piranhaRight,rectArr[i][j].left,rectArr[i][j].top-piranha, null);
                    canvas.drawBitmap(piranhaLeft,rectArr[i][j].left-squareSize,rectArr[i][j].top-piranha, null);
                    canvas.drawBitmap(pipeTR, rectArr[i][j].left, rectArr[i][j].top, null);
                    canvas.drawBitmap(pipeTL, rectArr[i][j].left-squareSize, rectArr[i][j].top, null);

                    if(pUp==0) {
                        if(xLoc-i<5 && xLoc-i>-5) {
                            piranhaPlant(i, j);
                        }
                    }
                }
                else if(boardArr[i][j]==6) {
                    canvas.drawBitmap(pipeTL, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==7) {
                    canvas.drawBitmap(pipeBR, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==8) {
                    canvas.drawBitmap(pipeBL, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==-2) {
                    canvas.drawBitmap(coin, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==-3) {
                    canvas.drawBitmap(goomba, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==-4) {
                    canvas.drawBitmap(mushroom, rectArr[i][j].left, rectArr[i][j].top, null);
                }
                else if(boardArr[i][j]==-5) {
                    canvas.drawBitmap(star, rectArr[i][j].left, rectArr[i][j].top, null);
                }
            }
        }

        mario.top=marioY;                                                   //Draw Mario
        mario.left=800;
        mario.right=mario.left+squareSize;
        mario.bottom=mario.top+squareSize;
        marioPaint.setTextSize(80);

        canvas.drawText(errMessage,870,190,marioPaint);
        canvas.drawText("Score: ".concat(Integer.toString(score)),1320,90,marioPaint);         //Draw X and Y Coordinates of Mario
        canvas.drawText("Lives: ".concat(Integer.toString(lives)),1320,190,marioPaint);

        jumpBtn.top=700;                //Draw Jump Button
        jumpBtn.left=750;
        jumpBtn.bottom=850;
        jumpBtn.right=1200;
        float temp[]={100,100};
        //canvas.drawColor(Color.BLACK);
        //canvas.drawRect(mario,marioPaint);
        canvas.drawRect(jumpBtn,marioPaint);
        canvas.drawBitmap(marioBM,mario.left,mario.top,null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(xLoc>12) {
            errMessage="";
        }

        boolean value = super.onTouchEvent(event);
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();

            if(x>750 && x<1200 && y>700 && y<850) {             //if jump button is pressed, then jump
                if(boardArr[xLoc][yLoc+1]>0 || boardArr[(xLoc+1)][yLoc+1]>0) {                 //must be on the ground to jump
                    jump();
                }
            }
            else {                                                      //If right half of the screen is pressed, move right
                moving = 1;
                if (x > 900) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (this) {
                                while (moving != 0) {
                                    leftPos -= 2;
                                    if (boardArr[(leftPos * -1) / squareSize + 11][yLoc] > 0 && boardArr[(leftPos * -1) / squareSize + 11][(marioY+squareSize)/squareSize] > 0) {
                                        leftPos += 2;
                                        //xLoc -= 1;
                                    } else {
                                        xLoc = (leftPos * -1) / squareSize + 10;
                                    }

                                    try {
                                        Thread.sleep(1);
                                        timer++;
                                    } catch (InterruptedException e) {

                                    }
                                    if(timer%40==1) {
                                        timer=2;
                                        if( marioBM==marioJmp){
                                            marioBM=marioRun;
                                        }
                                        else {
                                            marioBM=marioJmp;
                                        }
                                    }
                                    postInvalidate();
                                }
                                marioBM=marioRun;
                            }

                        }
                    });
                    thread.start();
                } else {                                        //if left half of the screen is pressed, move left
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (this) {
                                while (moving != 0) {
                                    leftPos += 2;
                                    if (boardArr[(leftPos * -1) / squareSize + 9][yLoc] > 0 && boardArr[(leftPos * -1) / squareSize + 9][(marioY+squareSize)/squareSize] > 0) {
                                        leftPos -= 2;
                                        //xLoc -= 1;
                                    } else {
                                        xLoc = (leftPos * -1) / squareSize + 10;
                                    }

                                    try {
                                        Thread.sleep(1);
                                        timer++;
                                    } catch (InterruptedException e) {

                                    }
                                    if(timer%40==1) {
                                        timer=2;
                                        if( marioBM==marioJmp){
                                            marioBM=marioRun;
                                        }
                                        else {
                                            marioBM=marioJmp;
                                        }
                                    }
                                    postInvalidate();
                                }
                                marioBM=marioRun;
                            }
                        }
                    });
                    thread.start();
                }
                postInvalidate();
            }
            return true;
        }
        else if(event.getAction() == KeyEvent.ACTION_UP) {
            moving=0;
            //value = super.onTouchEvent(event);
            x++;
            postInvalidate();
            return true;
        }
        return value;
    }
}
