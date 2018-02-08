/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 *
 * @author cmaso
 */
public class Block {
    private Label lbl;
    private char blockType;
    public  static int numPellets = 0; 
    public static boolean powerUp = false;
    //numPellets = 288
    
     
    Block(int width, int fontSize, char text, Color textColor, String backColor, char type){
      //  ImagePattern lights;
        //lights.getImage(new Image("/Resources/source.gif"));
        lbl = new Label(text + "");
       // else lbl = new Label(text + "" + text);
        lbl.setMinWidth(13);
        lbl.setMaxWidth(13);
        lbl.setMaxHeight(17);
        lbl.setMinHeight(17);
        if (type == 'W')lbl.setTextFill(Color.BLUE);
        else if (type == 'G') lbl.setTextFill(Color.GREY);
        else if (type == 'C') lbl.setTextFill(Color.RED);
        else lbl.setTextFill(Color.WHITE);//lbl.setTextFill(new ImagePattern(new Image("/Resources/source.gif")));
        lbl.setStyle("-fx-background-color: " + backColor + ";");
        lbl.setFont(Font.font ("Courier New", fontSize+2));
        lbl.setTextAlignment(TextAlignment.LEFT);
        blockType = type;
        lbl.setMouseTransparent(false);
        DropShadow borderGlow = new DropShadow();
borderGlow.setColor(Color.BLUE);
borderGlow.setOffsetX(2f);
borderGlow.setOffsetY(2f);

lbl.setEffect(borderGlow);

    /*Block1.setFont(Font.font ("Courier New", 40));
        Block1.setMinWidth(20);
        Block1.setTextFill(Color.RED);
        Block1.setStyle("-fx-background-color: black;");*/
}
    public Label ret() {
        return lbl;
    }
    
    public void clearLabel(){
        String getText = lbl.getText();
        if (getText.charAt(0) == '.' || getText.charAt(0) == '¤'){
            numPellets++;
        if (getText.charAt(0) == '¤'){
            powerUp = true;
            Game2.SCORE += 100;
            Game2.lblSCORE.setText(Game2.SCORE + "");}
        else {
            powerUp = false;
            Game2.SCORE += 10;
            Game2.lblSCORE.setText(Game2.SCORE + "");
        }
        }
        lbl.setText(" ");
    }
    }