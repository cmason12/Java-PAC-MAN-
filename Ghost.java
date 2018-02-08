/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author cmaso
 */
public class Ghost {
    private ImageView pink = new ImageView("/Resources/pink.gif");
    private Point2D pinkCoord = new Point2D(12, 13);
    private ImageView orange = new ImageView("/Resources/orange.gif");
    private Point2D orangeCoord = new Point2D(15, 13);
    private ImageView blue = new ImageView("/Resources/blue.gif");
    private Point2D blueCoord = new Point2D(10, 13);
    private ImageView red = new ImageView("/Resources/red.gif");
    private Point2D redCoord = new Point2D(13, 11);
    
    private final Point2D pinkStart = new Point2D(12, 13);
    private final Point2D orangeStart = new Point2D(15, 13);
    private final Point2D blueStart = new Point2D(10, 13);
    private final Point2D redStart= new Point2D(13, 11);
    
    private boolean eatPink = false;
    private boolean eatOrange = false;
    private boolean eatBlue = false;
    private boolean eatRed = false;
    
    private int pinkTick = 0;
    private int orangeTick = 0;
    private int blueTick = 0;
    private int ppTimer = 0;
    Timeline playerDeath = new Timeline(new KeyFrame(Duration.seconds(2.25), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //4*15 = 60
           Game2.player.setImage(new Image("/Resources/pac.gif"));
            Game2.player.setRotate(180);
            Game2.player.setTranslateX(-160);
            Game2.player.setTranslateY(-61+48*2);
        StageLevel.xCoord =1;
        StageLevel.yCoord = 14;
        Game2.isDead = false;
        //Game2.moveEnt.play();
        playerDeath.stop();
        resetAll();
        }
        })); 
    Timeline powerPellet = new Timeline(new KeyFrame(Duration.seconds(.25), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //4*15 = 60
            if (ppTimer == 0){
            eatBlue = true;
            eatRed = true;
            eatPink = true;
            eatOrange = true;
            }
            if (ppTimer == 0 || ppTimer == 50-10 || ppTimer == 54-10 || ppTimer == 58-10 ){
                if(eatBlue)blue.setImage(new Image("/Resources/power.gif"));
                if(eatRed)red.setImage(new Image("/Resources/power.gif"));
                if(eatPink)pink.setImage(new Image("/Resources/power.gif"));
                if(eatOrange)orange.setImage(new Image("/Resources/power.gif"));
               // ppTimer++;
            } else if (ppTimer == 48-10 || ppTimer == 52-10 || ppTimer == 56-10 || ppTimer == 60-10){
                if(eatBlue)blue.setImage(new Image("/Resources/blue.gif"));
                if(eatRed)red.setImage(new Image("/Resources/red.gif"));
               if(eatPink) pink.setImage(new Image("/Resources/pink.gif"));
                if(eatOrange)orange.setImage(new Image("/Resources/orange.gif"));
            } else;
            if(ppTimer == 60) {
                ppTimer = 0;
                powerPellet.stop();
            }
            ppTimer++;
        }
        })); 
    Ghost(){
        pink.setFitWidth(17);
        pink.setFitHeight(20);
        pink.setTranslateY(-60+48*2);
        pink.setTranslateX(-7);
        
        red.setFitWidth(17);
        red.setFitHeight(20);
        red.setTranslateY(-60 - 17 *3 + 48*2);
        red.setTranslateX(-13);
        
        blue.setFitWidth(17);
        blue.setFitHeight(20);
        blue.setTranslateY(-60 + 48*2);
        blue.setTranslateX(-13*2 + -7);
        
        orange.setFitWidth(17);
        orange.setFitHeight(20);
        orange.setTranslateY(-60 + 48*2);
        orange.setTranslateX(+13*2);
}
    void testHit(ImageView player, int x, int y) {
        boolean dead = false;
        //Point2D test = new Point2D(x,y);
        //System.out.println("Here");
        dead = (!eatPink &&(((int)pinkCoord.getX()== x )&& ((int)pinkCoord.getY() == y))
                || (!eatRed &&((int)redCoord.getX() == x) &&( (int)redCoord.getY() == y))
                || (!eatBlue &&((int)blueCoord.getX() == x) && ((int)blueCoord.getY() == y))
                || (!eatOrange &&((int)orangeCoord.getX()) == x && ((int)orangeCoord.getY() == y)));
        if (eatPink &&((int)pinkCoord.getX()== x )&& ((int)pinkCoord.getY() == y)) {
        pink.setTranslateY(-60 + 48*2);
        pink.setTranslateX(-7);;
        pinkCoord = pinkStart;
        pinkTick = 0;  
        eatPink = false;
        Game2.SCORE += 500;
        Game2.lblSCORE.setText(Game2.SCORE + "");
        pink.setImage(new Image("/Resources/pink.gif"));
        }
        if (eatOrange &&((int)orangeCoord.getX()== x )&& ((int)orangeCoord.getY() == y)) {
        orange.setTranslateY(-60 + 48*2);
        orange.setTranslateX(26);
        orangeCoord = orangeStart;
        orangeTick = 0;
        orange.setImage(new Image("/Resources/orange.gif"));
        eatOrange = false;
        Game2.SCORE += 500;
        Game2.lblSCORE.setText(Game2.SCORE + "");
        }
        
        if (eatRed &&((int)redCoord.getX()== x )&& ((int)redCoord.getY() == y)) {
        red.setFitWidth(17);
        red.setFitHeight(20);
        red.setTranslateY(-60 - 17 *3 + 48*2);
        red.setTranslateX(-13);
        redCoord = redStart;
        eatRed = false;
        red.setImage(new Image("/Resources/red.gif"));
        Game2.SCORE += 500;
        Game2.lblSCORE.setText(Game2.SCORE + "");
        }
        
        if (eatBlue &&((int) blueCoord.getX()== x )&& ((int)blueCoord.getY() == y)) {
        blue.setFitWidth(17);
        blue.setFitHeight(20);
        blue.setTranslateY(-60+48*2);
        blue.setTranslateX(-33);
        blueCoord = blueStart;
        blueTick = 0;
        blue.setImage(new Image("/Resources/blue.gif"));
        eatBlue = false;
        Game2.SCORE += 500;
        Game2.lblSCORE.setText(Game2.SCORE + "");
        }
        if (dead) {
        Game2.isDead = true;
        Game2.moveEnt.stop();
        player.setImage(new Image("/Resources/DED.gif"));
        player.setRotate(180);
       // Thread.sleep(3000);
       Game2.lives--;
       Game2.lblLives.setText("Lives: " + Game2.lives + "");
       playerDeath.play();
        
        };
    }
    ImageView retGhost(int index){
        ImageView ret;
        if(index == 1)ret = blue;
        else if (index == 2) ret = pink;
        else if (index == 3) ret = orange;
        else ret = red;
        return ret;
    }
    public void move(int index){
        int X = 0;
        int Y = 0;
        int XorY = 0;
        boolean canMove = false;
        boolean xChange = false;
        boolean yChange = false;
        Point2D pos;
        String temp;
        do {
        //for (int index = 1; index < 5; index++){
        if(index == 1)pos = blueCoord;
        else if (index == 2) pos = pinkCoord;
        else if (index == 3) pos = orangeCoord;
        else pos = redCoord;
        
        Random r = new Random();
        X= 0;
        Y= 0;
        //int Result = r.nextInt(High-Low) + Low;
        r = new Random();
			XorY = r.nextInt(2 - 1 + 1) + 1;
                        //System.out.println(XorY);
			if (XorY == 1)
			{
				X = r.nextInt(2 - 1 + 1) + 1;

				if (X == 1)
					X = -1;
                                else X = 1;
			}
			if (XorY == 2)
			{
				Y = r.nextInt(2 - 1 + 1) + 1;
				if (Y == 1)
					Y = -1;
                                else Y = 1;
			}

			temp = StageLevel.STAGE[(int)pos.getY() + Y];
                        char test;
			test = temp.charAt((int)pos.getX() + X);
                       // System.out.println(Y + ", " + X + ", " + test);

			if (test == '~' || test == 'T' || test == 'C')
				canMove = true;
		
		if(canMove){  Point2D newPos = new Point2D(pos.getX()+ X, pos.getY() +Y);
                  pos = newPos;
		  if(index == 1) {
                      blueCoord = pos;
                      blue.setTranslateX(blue.getTranslateX() + 13*X);
                      blue.setTranslateY(blue.getTranslateY() + 17*Y);
                  }
                  else if (index == 2) {
                      pinkCoord = pos;
                      pink.setTranslateX(pink.getTranslateX() + 13*X);
                      pink.setTranslateY(pink.getTranslateY() + 17*Y);
                  }
                  else if (index == 3) {
                      orangeCoord = pos;
                      orange.setTranslateX(orange.getTranslateX() + 13*X);
                      orange.setTranslateY(orange.getTranslateY() + 17*Y);
                  }
                  else {
                      redCoord = pos;
                      red.setTranslateX(red.getTranslateX() + 13*X);
                      red.setTranslateY(red.getTranslateY() + 17*Y);
                  }
                  
               //   System.out.println( "(" + pos.getX() + ", " + pos.getY() + ")");
        }
               /* if(index == 1){
            testHit(Game2.player, (int)blueCoord.getX(), (int)blueCoord.getY());
        }
        else if (index == 2) {
            testHit(Game2.player, (int)pinkCoord.getX(), (int)pinkCoord.getY());
        }
        else if (index == 3) {
            testHit(Game2.player, (int)orangeCoord.getX(), (int)orangeCoord.getY());
        }
        else {
            testHit(Game2.player, (int)redCoord.getX(), (int)redCoord.getY());
        }*/
        
        }while(!canMove);
    }
    
    public void entry(){
        Point2D temp;
        //System.out.println("blue " + blueCoord.getX() + ", " + blueCoord.getY());
     if (blueTick == 0 || blueTick == 1){
         temp = new Point2D(blueCoord.getX() + 1, blueCoord.getY());
         blueCoord = temp;
         blueTick++;
         blue.setTranslateX(blue.getTranslateX()+13);
     } else if (blueTick == 2 || blueTick == 3 || blueTick == 4){
         temp = new Point2D(blueCoord.getX()-1, blueCoord.getY()-1);
         blueCoord = temp;
         blue.setTranslateY(blue.getTranslateY()-17);
         blueTick++;
     }  else if(blueTick == 5) {
         blueCoord = new Point2D(13, 11);
         blueTick++;
     }else move(1);
     
     
     if (orangeTick == 0 || orangeTick == 1){
         temp = new Point2D(orangeCoord.getX()-1, orangeCoord.getY());
         orangeCoord = temp;
         orangeTick++;
         orange.setTranslateX(orange.getTranslateX()-13);
     } else if (orangeTick == 2 || orangeTick == 3 || orangeTick == 4){
         temp = new Point2D(orangeCoord.getX(), orangeCoord.getY()-1);
         orangeCoord = temp;
         orange.setTranslateY(orange.getTranslateY()-17);
         orangeTick++;
     }  else if (orangeTick == 5){
         orangeCoord = new Point2D(14, 11);
         orangeTick++;
     } else move(3);
     
     
     if (pinkTick == 0 || pinkTick == 1 || pinkTick == 2){
         temp = new Point2D(pinkCoord.getX(), pinkCoord.getY()-1);
         pinkCoord = temp;
         pinkTick++;
         pink.setTranslateY(pink.getTranslateY()-17);
     } else if (pinkTick == 3){
         pinkCoord = new Point2D(13, 11);
         pinkTick++;
     }else move(2);
    
     move(4);
     
    }
    
    public void startPower(){
        ppTimer = 0;
        powerPellet.setCycleCount(Timeline.INDEFINITE);
        powerPellet.play();
    }
    
    public void resetAll(){
        pink.setFitWidth(17);
        pink.setFitHeight(20);
        pink.setTranslateY(-60 + 48*2);
        pink.setTranslateX(-7);
        pinkCoord = pinkStart;
        pinkTick = 0; 
        
        red.setFitWidth(17);
        red.setFitHeight(20);
        red.setTranslateY(-60 - 17 *3 +48*2);
        red.setTranslateX(-13);
        redCoord = redStart;
        
        blue.setFitWidth(17);
        blue.setFitHeight(20);
        blue.setTranslateY(-60+48*2);
        blue.setTranslateX(-33);
        blueCoord = blueStart;
        blueTick = 0;
        
        orange.setFitWidth(17);
        orange.setFitHeight(20);
        orange.setTranslateY(-60 + 48*2);
        orange.setTranslateX(26);
        orangeCoord = orangeStart;
        orangeTick = 0;
    }
}
    

