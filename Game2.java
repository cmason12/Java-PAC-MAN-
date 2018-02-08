/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author cmaso
 */
public class Game2 extends Application {
    public  static ImageView player = new ImageView("/Resources/pac.gif");
    private static StageLevel stg = new StageLevel();
    private static boolean canMove = false;
    private static Ghost gts = new Ghost();
    private static int ghostTick = 0;
    public static  boolean isDead = false;
    public static int SCORE = 0;
    public static Label lblSCORE = new Label("0");
    public static Label lblLives = new Label("Lives: 3");
    public static int lives = 3;
    static Timeline moveEnt = new Timeline(new KeyFrame(Duration.seconds(.125), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println(Block.numPellets);
            
                canMove = true;
                     gts.testHit(player, StageLevel.xCoord, StageLevel.yCoord);
                     if(ghostTick == 2 && !isDead){
                         gts.entry();
                         gts.testHit(player, StageLevel.xCoord, StageLevel.yCoord);
                         ghostTick = 0;
                     }
            ghostTick++;
            
        }
        })); 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pac-Man");
        primaryStage.getIcons().add(new Image("/Resources/pink.gif"));
        moveEnt.setCycleCount(Timeline.INDEFINITE);
         
        StackPane root = new StackPane();
      //  ImageView player = new ImageView("/Resources/pac.gif");
        Label lblHS = new Label("HighScore");
        lblSCORE.setMaxHeight(48);
        lblHS.setMaxHeight(48);
        lblSCORE.setMinHeight(48);
        lblHS.setMinHeight(48);
        
        lblHS.setTextFill(Color.WHITE);
        lblHS.setStyle("-fx-background-color: " + "black" + ";");
        lblHS.setFont(Font.font ("Courier New", FontWeight.BOLD, 24));
        lblHS.setTextAlignment(TextAlignment.LEFT);
        lblHS.setTranslateX(+20);
        
        lblSCORE.setTextFill(Color.WHITE);
        lblSCORE.setStyle("-fx-background-color: " + "black" + ";");
        lblSCORE.setFont(Font.font ("Courier New", FontWeight.BOLD, 24));
        lblSCORE.setTextAlignment(TextAlignment.LEFT);
        lblSCORE.setTranslateX(+20);
        lblSCORE.setMaxWidth(120);
        lblSCORE.setMinWidth(120);
        
        lblLives.setTextFill(Color.WHITE);
        lblLives.setStyle("-fx-background-color: " + "black" + ";");
        lblLives.setFont(Font.font ("Courier New", FontWeight.BOLD, 24));
        lblLives.setTextAlignment(TextAlignment.LEFT);
        lblLives.setTranslateX(+20);
        lblLives.setTranslateY(10);
        
        HBox scoreLives = new HBox();
        scoreLives.getChildren().addAll(lblSCORE, lblLives);
        
        player.setRotate(180);
        player.setFitWidth(20);
        player.setFitHeight(20);
        player.setTranslateX(-160);
        player.setTranslateY(-61+48*2);
        VBox def = new VBox();
        def.getChildren().addAll(lblHS, scoreLives, stg.ret());
        root.getChildren().addAll(def, gts.retGhost(1), gts.retGhost(2),
                gts.retGhost(3), gts.retGhost(4), player);
        
        Scene scene = new Scene(root, 360, 620);
        root.setStyle("-fx-background-color: " + "black" + ";");
       // VBox rows = stg.ret();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                
                if(!isDead) moveEnt.play();
                if (canMove && Block.numPellets != 288 && lives > 0){
               
                   if (key.getCode().equals(KeyCode.RIGHT) ||key.getCode().equals(KeyCode.D)) {
                    //System.out.println("right");
                    if(!(stg.xCoord >= 26 && stg.yCoord == 14)) 
                    {if(stg.testHitX(1)){
                        player.setTranslateX(player.getTranslateX() +13);
                        player.setRotate(180);
                        //Block ok  = (Block)(StageLevel.row[stg.yCoord].getChildren().get(stg.xCoord -1));
                        StageLevel.board[stg.yCoord][stg.xCoord-1].clearLabel();
                    }
                    } else{
                        player.setTranslateX(player.getTranslateX() - 13*25);
                        stg.xCoord = 1;
                        player.setRotate(180);
                    }
                } else if (key.getCode().equals(KeyCode.LEFT) || key.getCode().equals(KeyCode.A)){
                    if(!(stg.xCoord <= 1 && stg.yCoord == 14)) { 
                        if(stg.testHitX(-1)){
                        player.setTranslateX(player.getTranslateX() -13);
                        player.setRotate(20);
                        StageLevel.board[stg.yCoord][stg.xCoord+1].clearLabel();
                    }
                    }else{
                        player.setTranslateX(player.getTranslateX() + 13*25);
                        stg.xCoord = 26;
                        player.setRotate(20);
                    }
                }else if (key.getCode().equals(KeyCode.UP)|| key.getCode().equals(KeyCode.W)){
                if(stg.testHitY(-1)){
                        player.setTranslateY(player.getTranslateY() -17);
                        player.setRotate(90);
                        StageLevel.board[stg.yCoord+1][stg.xCoord].clearLabel();
                        
                    }    
               
            }else if (key.getCode().equals(KeyCode.DOWN)){
                 if(stg.testHitY(+1)){
                        player.setTranslateY(player.getTranslateY() +17);
                        player.setRotate(270);
                        StageLevel.board[stg.yCoord-1][stg.xCoord].clearLabel();
                        
                    }
            }else;
                } else if (key.getCode().equals(KeyCode.ENTER)){
                    
                } else;
                //System.out.println("(" + StageLevel.xCoord + ", " + StageLevel.yCoord + ")");
                //gts.move(4);
                //gts.testHit(player, StageLevel.xCoord, StageLevel.yCoord);
                canMove = false;
                //System.out.println(Block.numPellets);
                if (Block.numPellets == 288 && lives > 0)
                    moveEnt.stop();
                if(Block.powerUp) gts.startPower();
                }
            
            });
        
        //primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
