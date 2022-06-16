import processing.core.PApplet;

public class Sketch extends PApplet {
  //all integers that use sizes
  int CELL_WIDTH = 20;
  int CELL_HEIGHT = 20;
  int MARGIN = 5;
  int ROW_COUNT = 6;
  int COLUMN_COUNT = 7;
  int SCREEN_WIDTH = (CELL_WIDTH + MARGIN) * COLUMN_COUNT + MARGIN;
  int SCREEN_HEIGHT = (CELL_HEIGHT + MARGIN) * ROW_COUNT + MARGIN;
  //all integers that use math in the game
  int intPlayerTurn = 1;
  int intLastColumn;
  int intLastRow;
  int intElem;
  int intSelectedBlock;
  int intCount1;
  int intCount2;
  int intPieces;
  //all booleans used
  boolean isPlayer1;
  boolean isPlayer2;
  boolean isPlayer1Win;
  boolean isPlayer2Win;
  boolean isGameDraw;
  boolean isGameOn;
  boolean isEndScreen;
  //the 2D array we use to create the connect 4 board
  int [][] board = new int[6][7];
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(300, SCREEN_HEIGHT);

    
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0);
    textSize(20);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */

  public void draw() {
    // Daniel
    //Checks that the game is off and that we're not at the end screen, therefore we draw the main menu
    //-------------------MAIN MENU-----------------------
    if(isGameOn == false && isEndScreen == false){
      fill(255);
      text("CONNECT 4", 95, 50);
      rect(95,70, 115, 40);
      fill(0);
      text("PLAY", 127, 97);
    }
    //if we're not at the main menu anymore (by clicking the start game button which changes the variables isGameOn to true) 
    //and we're not at the End screen, we draw the actual connect 4 game
    //----------------GAME IS NOW ON---------------------
    else if(isGameOn == true && isEndScreen == false){
      
    //Checks if a player has won yet, or if the game has drawn, and turns the game off, and turns the end screen on
    if(isPlayer1Win || isPlayer2Win || isGameDraw){
      isGameOn = false;
      isEndScreen = true;
    }
      // i is rows, j is columns THERE ARE ONLY 6 ROWS AND 7 COLUMNS. 
      // THE INDEXES START AT 0
      // cycles through the 2d array for the connect 4 grid, and draws out the shapes depending on what part of the array is a 0, 1, 2 (0 is nothing, 1 is red piece, 2 is yellow piece)
      for(int i = 0; i < board.length; i++){ 
        for(int j = 0; j < board[0].length; j++){
          //this code builds the blue squares
          fill(52, 146, 235);
          rect (MARGIN * (j + 1) + (j * CELL_WIDTH), MARGIN * (i + 1) + (i * CELL_HEIGHT), CELL_HEIGHT, CELL_WIDTH);
          intElem = board[i][j];
          
          //we check at each position in the array, if that specific piece that should be in that row and column is a 0, 1, or 2. Then we draw the appropriate circle in that position
          if(intElem == 0){
            fill(0);
            ellipse (MARGIN * (j + 1) + (j * CELL_WIDTH) + (float) 11, MARGIN * (i + 1) + (i * CELL_HEIGHT) + (float) 11, 15, 15);
          }
          else if(intElem == 1){
            fill(255, 0, 0);
            ellipse (MARGIN * (j + 1) + (j * CELL_WIDTH) + (float) 11, MARGIN * (i + 1) + (i * CELL_HEIGHT) + (float) 11, 15, 15);
          }
          else if(intElem == 2){
            fill(255, 191, 0);
            ellipse (MARGIN * (j + 1) + (j * CELL_WIDTH) + (float) 11, MARGIN * (i + 1) + (i * CELL_HEIGHT) + (float) 11, 15, 15);
          }
      }
      }
      
      //Check what player turn is up
      if(intPlayerTurn > 0){
        isPlayer1 = true;
        isPlayer2 = false;
      }
      else if(intPlayerTurn < 0){
        isPlayer2 = true;
        isPlayer1 = false;
      }

      //creating text to show whose turn it is
      //-------------------TURN SCREENS----------------------
       
      if(isPlayer1){
        fill(0);
        rect(180,0, 200, 100);
        fill(255, 0, 0);
        text("Reds Turn", 190, 85);
      }
      else if(isPlayer2){
        fill(0);
        rect(180,0, 200, 100);
        fill(255, 191, 0);
        text("Yellows Turn", 180, 85);
      }
    }
    //Draws an end screen that has a button that can go back to the main menu. This screen shows which player one or if the game was a draw
    //----------------------END SCREENS-----------------------
    else if(isEndScreen == true){
      fill(0);
      rect(0,0,300,300);
      fill(255);
      if(isPlayer1Win){
        text("RED WINS!", 100, 50);
        rect(95,70, 115, 40);
        fill(0);
        text("MENU", 123, 97);
      }
      else if(isPlayer2Win){
        text("YELLOW WINS!", 80, 50);
        rect(95,70, 115, 40);
        fill(0);
        text("MENU", 123, 97);
      }
      else if(isGameDraw){
        text("GAME DRAW!", 80, 50);
        rect(95,70, 115, 40);
        fill(0);
        text("MENU", 123, 97);
      }

    }
  
}

  public void mouseClicked() {

    //Joshua
    // Checks that the game is on and that we're not on the end screen
    if(isGameOn == false && isEndScreen == false){
      //if the mouse is inside where the "start game" button is on the main menu, than it will run this code
      if(mouseX > 95 && mouseX < 210 && mouseY > 70 && mouseY < 110){
        //This code resets the variables needed for a fresh new connect 4 game, and puts a black rectangle over the entire screen so that the new drawings will be on a fresh canvas
        fill(0);
        rect(0,0,300,300);
        isGameOn = true;
        isPlayer1Win = false;
        isPlayer2Win = false;
        intPieces = 0;
        intCount1 = 0;
        intCount2 = 0;
        //we reset the 2d array back to all 0's
        board = new int[6][7];
        
      }
    }
    //checks if the game is on, and that no one has won. It also checks that we are not at the end screen yet
    else if(isGameOn == true && isEndScreen == false && isPlayer1Win == false && isPlayer2Win == false){
     if(mouseX > 5 && mouseX < 25){
      //Each of these if statements checks if the x value of the mouse is in any of the 7 columns. Depending on which column it is, it cycles through the integers in that 
      //column to check their numbers. If there is a 0 will it's going through the array from bottom to top, it changes it to a 1 or a 0 depending on the players turn, 
      //then breaks the code so it can only change one piece every turn.
      
      //this for loop runs through the column from bottom to top
      for(int i = 5; i >= 0; i--){
        //The code checks if the element at that position is a 0
        if(board[i][0] == 0){
          //if its a 0 and its player1's turn it changes it to a 1 (red piece)
          if(isPlayer1){
            board[i][0] = 1;
          }
          else if(isPlayer2){
          //if its a 0 and its player2's turn it changes it to a 2 (yellow piece)
            board[i][0] = 2;
          }
          //add a plus one to intPieces so we know how many pieces have been dropped
          intPieces++;
          //Save the positions of the last piece that was played
          intLastRow = i;
          intLastColumn = 0;
          //intPlayerTurn will be multiplied by -1 everytime a piece is put down. This will make it so every players turn, will cycle this value from being either below or over 0, 
          //which is used in an if statement above to determine whose turn it is (lines 102-110)
          intPlayerTurn *= -1;
          break;
        }
      }
     }
     //we repeat the above code for all 7 columns
     else if(mouseX > 30 && mouseX < 50){
      for(int i = 5; i >= 0; i--){
        if(board[i][1] == 0){
          if(isPlayer1){
            board[i][1] = 1;
          }
          else if(isPlayer2){
            board[i][1] = 2;
          }
          intPieces++;
          intLastRow = i;
          intLastColumn = 1;
          intPlayerTurn *= -1;
          break;
        }
      }
     }
     else if(mouseX > 55 && mouseX < 75){
      for(int i = 5; i >= 0; i--){
        if(board[i][2] == 0){
          if(isPlayer1){
            board[i][2] = 1;
          }
          else if(isPlayer2){
            board[i][2] = 2;
          }
          intPieces++;
          intLastRow = i;
          intLastColumn = 2;
          intPlayerTurn *= -1;
          break;
        }
      }
     }
     else if(mouseX > 80 && mouseX < 100){
      for(int i = 5; i >= 0; i--){
        if(board[i][3] == 0){
          if(isPlayer1){
            board[i][3] = 1;
          }
          else if(isPlayer2){
            board[i][3] = 2;
          }
          intPieces++;
          intLastRow = i;
          intLastColumn = 3;
          intPlayerTurn *= -1;
          break;
        }
      }
     }
     else if(mouseX > 105 && mouseX < 125){
      for(int i = 5; i >= 0; i--){
        if(board[i][4] == 0){
          if(isPlayer1){
            board[i][4] = 1;
          }
          else if(isPlayer2){
            board[i][4] = 2;
          }
          intLastRow = i;
          intLastColumn = 4;
          intPlayerTurn *= -1;
          break;
        }
      }
     }
     else if(mouseX > 130 && mouseX < 150){
      for(int i = 5; i >= 0; i--){
        if(board[i][5] == 0){
          if(isPlayer1){
            board[i][5] = 1;
          }
          else if(isPlayer2){
            board[i][5] = 2;
          }
          intPieces++;
          intLastRow = i;
          intLastColumn = 5;
          intPlayerTurn *= -1;
          break;
        }
      }
     }
     else if(mouseX > 155 && mouseX < 175){
      for(int i = 5; i >= 0; i--){
        if(board[i][6] == 0){
          if(isPlayer1){
            board[i][6] = 1;
          }
          else if(isPlayer2){
            board[i][6] = 2;
          }
          intPieces++;
          intLastRow = i;
          intLastColumn = 6;
          intPlayerTurn *= -1;
          break;
        }
      }
     }

     //In this part of the code, we check if anyone has one whenever the mouse is clicked while the game is still running
     
     //Checking for vertical connections
     //runs a for loop through whichever column was clicked last.
     for(int i= 0; i < board.length; i++){
      int intElem = board[i][intLastColumn];
      //we use two counters to track how many pieces in a row we find. 

      //if there is a 0 while we're going through this code, it resets both counters to 0
       if(intElem == 0){
        intCount1 = 0;
        intCount2 = 0;
       }
       //if there is a 1 while we go through the column, it adds 1 to the intCount for player 1, and resets the count for player 2
       else if(intElem == 1){
        intCount1++;
        intCount2 = 0;
       }
       //if there is a 2 while we go through the column, it adds 1 to the intCount for player 2, and resets the count for player 1
       else if(intElem == 2){
        intCount1 = 0;
        intCount2++;
       }
       //if either of the players counts reach 4 or higher, without being reset back to 0, we change that players win variable to true
       if(intCount1 >= 4){
        isPlayer1Win = true;
       }
       else if(intCount2 >= 4){
        isPlayer2Win = true;
       }
     }

     //Checking for horizontal connections. This is the same code as above, except it goes through the integers of the last row that a piece was added to when clicked
     for(int i = 0; i < board[0].length; i++){
      int intElem = board[intLastRow][i];
      if(intElem == 0){
        intCount1 = 0;
        intCount2 = 0;
       }
       else if(intElem == 1){
        intCount1++;
        intCount2 = 0;
       }
       else if(intElem == 2){
        intCount1 = 0;
        intCount2++;
       }
       if(intCount1 >= 4){
        isPlayer1Win = true;
       }
       else if(intCount2 >= 4){
        isPlayer2Win = true;
       }
     }
     
     //Unfortunately we couldn't figure out how to do diagonal checks for the win condition
      
     

     
      //This if statement checks if 42 or more pieces have been dropped, so that once all pieces are dropped, the game is a draw
      if(intPieces >= 42){
        isGameDraw = true;
      }
    }
    //We now check if the mouse is being clicked while the end screen is true. If you click within the rectangle that goes back to the main menu, 
    //it will turn the isGameOn and isEndScreen variable to false
    else if(isEndScreen == true){
      if(mouseX > 95 && mouseX < 210 && mouseY > 70 && mouseY < 110){
        fill(0);
        rect(0,0,300,300);
        isGameOn = false;
        isEndScreen = false;
      }
    }

     

     

  }
}