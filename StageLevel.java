/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author cmaso
 */
public class StageLevel {
    public static final String STAGE[] =
{ "1++++++++++++21++++++++++++2",
"-~~~~~~~~~~~~--~~~~~~~~~~~~-",
"-~1++2~1+++2~--~1+++2~1++2~-",
"-C-XX-~-XXX-~--~-XXX-~-XX-C-",
"-~4++3~4+++3~43~4+++3~4++3~-",
"-~~~~~~~~~~~~~~~~~~~~~~~~~~-",
"-~1++2~12~1++++++2~12~1++2~-",
"-~4++3~--~4++21++3~--~4++3~-",
"-~~~~~~--~~~~--~~~~--~~~~~~-",
"4++++2~-4++2~--~1++3-~1++++3",
"XXXXX-~-1++3~43~4++2-~-XXXXX",
"XXXXX-~--~~~~~~~~~~--~-XXXXX",
"XXXXX-~--~1++GG++2~--~-XXXXX",
"+++++3~43~-XXXXXX-~43~4+++++",
"XXXXXX~~~~-XXXXXX-~~~~XXXXXX",
"+++++2~12~-XXXXXX-~12~1+++++",
"XXXXX-~--~4++++++3~--~-XXXXX",
"XXXXX-~--~~~~~~~~~~--~-XXXXX",
"XXXXX-~--~1++++++2~--~-XXXXX",
"1++++3~43~4++21++3~43~4++++2",
"-~~~~~~~~~~~~--~~~~~~~~~~~~-",
"-~1++2~1+++2~--~1+++2~1++2~-",
"-~4+2-~4+++3~43~4+++3~-1+3~-",
"-C~~--~~~~~~~~~~~~~~~~--~~C-",
"4+2~--~12~1++++++2~12~--~1+3",
"1+3~43~--~4++21++3~--~43~4+2",
"-~~~~~~--~~~~--~~~~--~~~~~~-",
"-~1++++34++2~--~1++34++++2~-",
"-~4++++++++3~43~4++++++++3~-",
"-~~~~~~~~~~~~~~~~~~~~~~~~~~-",
"4++++++++++++++++++++++++++3" };
    
     private static String curStage[] =
{ "1++++++++++++21++++++++++++2",
"-~~~~~~~~~~~~--~~~~~~~~~~~~-",
"-~1++2~1+++2~--~1+++2~1++2~-",
"-C-XX-~-XXX-~--~-XXX-~-XX-C-",
"-~4++3~4+++3~43~4+++3~4++3~-",
"-~~~~~~~~~~~~~~~~~~~~~~~~~~-",
"-~1++2~12~1++++++2~12~1++2~-",
"-~4++3~--~4++21++3~--~4++3~-",
"-~~~~~~--~~~~--~~~~--~~~~~~-",
"4++++2~-4++2~--~1++3-~1++++3",
"XXXXX-~-1++3~43~4++2-~-XXXXX",
"XXXXX-~--~~~~~~~~~~--~-XXXXX",
"XXXXX-~--~1++GG++2~--~-XXXXX",
"+++++3~43~-XXXXXX-~43~4+++++",
"XXXXXX~~~~-XXXXXX-~~~~XXXXXX",
"+++++2~12~-XXXXXX-~12~1+++++",
"XXXXX-~--~4++++++3~--~-XXXXX",
"XXXXX-~--~~~~~~~~~~--~-XXXXX",
"XXXXX-~--~1++++++2~--~-XXXXX",
"1++++3~43~4++21++3~43~4++++2",
"-~~~~~~~~~~~~--~~~~~~~~~~~~-",
"-~1++2~1+++2~--~1+++2~1++2~-",
"-~4+2-~4+++3~43~4+++3~-1+3~-",
"-C~~--~~~~~~~~~~~~~~~~--~~C-",
"4+2~--~12~1++++++2~12~--~1+3",
"1+3~43~--~4++21++3~--~43~4+2",
"-~~~~~~--~~~~--~~~~--~~~~~~-",
"-~1++++34++2~--~1++34++++2~-",
"-~4++++++++3~43~4++++++++3~-",
"-~~~~~~~~~~~~~~~~~~~~~~~~~~-",
"4++++++++++++++++++++++++++3" };
     
    public static int xCoord =1;
    public static int yCoord = 14;
    private VBox grid = new VBox();
    public  HBox row = new HBox();
    public static Block[][] board = new Block[31][28];
    StageLevel(){
        String temp;
        grid.setLayoutY(0);
	for (int i = 0; i < 31; i++)
	{
		temp = STAGE[i];
                row = new HBox();
		for (int i2 = 0; i2 < 28; i2++)
		{
                        Block box1 = new Block(10, 20, '|', Color.RED, "black", 'W'); ;
			if (temp.charAt(i2) == '+')
			{
				//what = char(205);
                                box1 = new Block(10, 15, '═', Color.RED, "black", 'W'); 
			}
			else if (temp.charAt(i2)  == '-')
			{
				box1 = new Block(10, 15, '║', Color.RED, "black", 'W'); 
			}
			else if (temp.charAt(i2)  == '~')
			{
				box1 = new Block(10, 15, '.', Color.RED, "black", 'D'); 
			}
			else if (temp.charAt(i2)  == '1')
			{
				box1 = new Block(10, 15, '╔', Color.RED, "black", 'W');
			}
			else if (temp.charAt(i2)  == '2')
			{
				box1 = new Block(10, 15, '╗', Color.RED, "black", 'W'); 
			}
			else if (temp.charAt(i2) == '3')
			{
				box1 = new Block(10, 15, '╝', Color.RED, "black", 'W'); 
			}
			else if (temp.charAt(i2) == '4')
			{
				box1 = new Block(10, 15, '╚', Color.RED, "black", 'W'); 
			}
			else if (temp.charAt(i2) == 'X')
			{
				box1 = new Block(10, 15, ' ', Color.RED, "black", 'D'); 
			}
			else if (temp.charAt(i2) == 'C')
			{
				box1 = new Block(10, 15, '¤', Color.RED, "black", 'C'); 
			}
			else if (temp.charAt(i2) == 'G')
			{
				box1 = new Block(10, 15, '=', Color.RED, "black", 'G'); 
			}
			else;
                        row.getChildren().add(box1.ret());
                        board[i][i2] = box1;
		}
                grid.getChildren().add(row);
    }
}
    public VBox ret(){
        return grid;
    }
    
    public boolean testHitX(int dir){
        boolean good = false;
     char holder;
     holder = curStage[yCoord].charAt(xCoord +dir);
    good = (holder == ' ' || holder == 'X' || holder == '~' || holder == 'C');
    if (good){
        String temp = "";
        for (int i = 0; i < curStage[yCoord].length(); i++){
            if(i == xCoord) temp += 'X';
            else temp += curStage[yCoord].charAt(i);
        }
        curStage[yCoord] = temp;
        xCoord += dir;
    }
    return good;
    }
    
    public boolean testHitY(int dir){
        boolean good = false;
     char holder;
     holder = curStage[yCoord+dir].charAt(xCoord);
    good = (holder == ' ' || holder == 'X' || holder == '~' || holder == 'C');
     if (good){
        String temp = "";
        for (int i = 0; i < curStage[yCoord].length(); i++){
            if(i == xCoord) temp += 'X';
            else temp += curStage[yCoord].charAt(i);
        }
        curStage[yCoord] = temp;
        yCoord += dir;
    }
    return good;
    }

}