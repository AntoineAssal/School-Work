package Magic_Board_Solver;

import java.awt.desktop.AppReopenedEvent;
import java.io.CharConversionException;
import java.util.*;
import javax.xml.stream.events.StartDocument;

public class MagicBoard {

  private int sizeD;
  private int[][] theBoard;
  private int goalX;
  private int goalY;
  static int startX;
  static int startY;
  static int valueZ;
  static boolean isitsolvable = true;
  static ArrayList<String> pastXY = new ArrayList<String>();

  public class pathFrame {

    private Character[] Direction = new Character[4];
    private int posX;
    private int posY;
    private int sqrVal;
    private int pathToTake;

    public pathFrame(int x, int y, int val) {
      posX = x;
      posY = y;
      sqrVal = val;
    }

    public pathFrame(
      int x,
      int y,
      int val,
      char east,
      char south,
      char west,
      char north
    ) {
      posX = x;
      posY = y;
      sqrVal = val;
      Direction[0] = east;
      Direction[1] = south;
      Direction[2] = west;
      Direction[3] = north;
    }

    public void setDirection(Character[] test) {
      pathToTake = 0;
      for (int i = 0; i < 4; i++) {
        if (test[i] != null) {
          //  System.out.println("this is the direction we are taking : "+test[i]);
          Direction[i] = new Character(test[i].charValue());
          if (Direction[i] != null) {
            pathToTake++;
          }
        }
      }
      System.out.println(
        "setDirecitoin() available paths to take: " + pathToTake
      );
    }

    public Character getLastDirect() {
      if (pathToTake != 0) for (int i = 3; i >= 0; i--) {
        if (Direction[i] != null) {
          System.out.println(
            "getLastDirect() : this is the next direction we're taking : " +
            Direction[i]
          );
          return Direction[i];
        }
      } else System.out.println("getLastDirect() : no possible path to take");
      return new Character('F');
    }

    public void eraseLast() {
      for (int i = 3; i >= 0; i--) {
        if (Direction[i] != null) {
          Direction[i] = null;
          break;
        }
      }
      return;
    }

    public String toString() {
      if (pathToTake == 0) {
        return (
          "This path starts from the square with coordinates x: " +
          posX +
          " & y: " +
          posY +
          " and the value of : " +
          sqrVal
        );
      } else {
        System.out.println(
          "This path starts from the square with coordinates x: " +
          posX +
          " & y: " +
          posY +
          " and the value of : " +
          sqrVal
        );
        System.out.println(
          "from here we can chose this many paths: " + pathToTake
        );
        System.out.print("Directions available :");
        for (int i = 0; i < 4; i++) {
          if (Direction[i] != null) System.out.print(Direction[i] + " ; ");
        }
      }
      return "";
    }
  }

  static Stack<pathFrame> pathDeck = new Stack<pathFrame>();

  public MagicBoard(int size) {
    if (size >= 5) {
      sizeD = size;
    } else {
      System.out.println("Please enter a valid number");
      return;
    }
    int[][] temp = new int[size][size];
    Random ranGen = new Random();
    goalX = ranGen.nextInt(size); //lower limit is 0 inclusive
    goalY = ranGen.nextInt(size); //upper limit is size exclusive
    do {
      startX = ranGen.nextInt(2); //lower limit is 0 inclusive
      startY = ranGen.nextInt(2);
      if (startX == 1) {
        startX = size - 1;
      }
      if (startY == 1) {
        startY = size - 1;
      }
    } while (goalX == startX && goalY == startY);
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        temp[col][row] = ranGen.nextInt(size - 1) + 1;
      }
    }
    temp[goalX][goalY] = 0;
    valueZ = temp[startX][startY];
    pastXY.add(startX + "," + startY);
    pathFrame basePath = new pathFrame(startX, startY, valueZ);
    System.out.println(
      "------------- This is the generated board : -------------"
    );
    // System.out.println(basePath.toString());
    pathDeck.push(basePath);
    // System.out.println(pathDeck.toString());
    theBoard = temp;
  }

  public void printMB() {
    String sep = "----";
    String lineSep = sep.repeat(sizeD);

    for (int row = 0; row < sizeD; row++) {
      for (int col = 0; col < sizeD; col++) {
        if (col == startX && row == startY) {
          System.out.print("|(" + theBoard[col][row] + ")");
        } else {
          System.out.print("| " + theBoard[col][row] + " ");
        }
      }
      System.out.println("\n" + lineSep);
    }
  }

  public void printUpdate(int x, int y) {
    String sep = "----";
    String lineSep = sep.repeat(sizeD);
    for (int row = 0; row < sizeD; row++) {
      for (int col = 0; col < sizeD; col++) {
        if (col == x && row == y) {
          System.out.print("|(" + theBoard[col][row] + ")");
        } else {
          System.out.print("| " + theBoard[col][row] + " ");
        }
      }
      System.out.println("\n" + lineSep);
    }
  }

  private Character[] possibleMove(int n) {
    Character[] temp = new Character[4];
    if (startX + n < sizeD) {
      //  System.out.println(!pastXY.contains((startX+n)+","+startY)+"this is the bool 1");
      if (!pastXY.contains((startX + n) + "," + startY)) {
        temp[0] = new Character('E');
      }
    }
    if ((startX - n) >= 0) {
      //  System.out.println(!pastXY.contains((startX-n)+","+startY)+"this is the bool 2");
      if (!pastXY.contains((startX - n) + "," + startY)) {
        temp[2] = new Character('W');
      }
    }
    if (startY + n < sizeD) {
      //  System.out.println(!pastXY.contains(startX+","+(startY+n))+"this is the bool");
      if (!pastXY.contains(startX + "," + (startY + n))) {
        temp[1] = new Character('S');
      }
    }
    if (startY - n >= 0) {
      //  System.out.println(!pastXY.contains(startX+","+(startY-n))+"this is the bool 3");
      if (!pastXY.contains(startX + "," + (startY - n))) {
        temp[3] = new Character('N');
      }
    }
    pathFrame tFrame = pathDeck.pop();
    pathFrame prev = new pathFrame(0, 0, 0);
    if (
      temp[0] == null && temp[1] == null && temp[2] == null && temp[3] == null
    ) {
      System.out.println("no more possible path for this frame");
      if (pathDeck.size() == 0) {
        isitsolvable = false;
        System.out.println("\n\n !! This magic board was not solvable !!");
      }
      if (!pathDeck.empty()) {
        prev = pathDeck.pop();
        startX = prev.posX;
        startY = prev.posY;
        valueZ = prev.sqrVal;
        System.out.println(
          "\n~~~~~~~~~~~~~~~this is a previous frame ~~~~~~~~~~~~~~~"
        );
        System.out.println(
          "Number of frames left in the deck: " + pathDeck.size()
        );
        printUpdate(startX, startY);
        pathDeck.push(prev);
        moveIt();
      }
      return temp;
    }
    tFrame.setDirection(temp);
    pathDeck.push(tFrame);
    return temp;
  }

  public void moveIt() {
    do {
      // System.out.println("BEGINING OF DO LOOP : Cycle");
      // System.out.println("startX : " + startX + " | startY : " + startY + " | valueZ : " + valueZ + "\n");
      possibleMove(valueZ);
      pathFrame tFrame = new pathFrame(0, 0, 0);
      char tChar = 'F';
      if (!pathDeck.empty()) {
        tFrame = pathDeck.pop();
        tChar = tFrame.getLastDirect().charValue();
      }
      //     System.out.println("moveit()  tChar "+tChar);
      if (tChar != 'F') {
        switch (tChar) {
          case 'E':
            tFrame.eraseLast();
            tFrame.pathToTake--;
            pathDeck.push(tFrame);
            startX += valueZ;
            valueZ = theBoard[startX][startY];
            printUpdate(startX, startY);
            pastXY.add(startX + "," + startY);
            pathFrame nextE = new pathFrame(startX, startY, valueZ);
            pathDeck.push(nextE);
            // System.out.println("moveit() pathframe from case E : \n" + nextE);
            if (valueZ == 0) {
              System.out.println("WE WON");
              pathDeck.clear();
              break;
            }
            break;
          case 'S':
            tFrame.eraseLast();
            tFrame.pathToTake--;
            pathDeck.push(tFrame);
            startY += valueZ;
            valueZ = theBoard[startX][startY];
            printUpdate(startX, startY);
            pastXY.add(startX + "," + startY);
            pathFrame nextS = new pathFrame(startX, startY, valueZ);
            pathDeck.push(nextS);
            // System.out.println("moveit() pathframe from S : \n" + nextS);
            if (valueZ == 0) {
              System.out.println("/////////////////WE WON////////////");
              pathDeck.clear();
            }
            break;
          case 'W':
            tFrame.eraseLast();
            tFrame.pathToTake--;
            pathDeck.push(tFrame);
            startX -= valueZ;
            valueZ = theBoard[startX][startY];
            printUpdate(startX, startY);
            pastXY.add(startX + "," + startY);
            pathFrame nextW = new pathFrame(startX, startY, valueZ);
            pathDeck.push(nextW);
            //   System.out.println("moveit() pathframe from W : \n" + nextW);
            if (valueZ == 0) {
              System.out.println("/////////////////WE WON//////////////");
              pathDeck.clear();
            }
            break;
          case 'N':
            tFrame.eraseLast();
            tFrame.pathToTake--;
            pathDeck.push(tFrame);
            startY -= valueZ;
            valueZ = theBoard[startX][startY];
            pastXY.add(startX + "," + startY);
            printUpdate(startX, startY);
            pathFrame nextN = new pathFrame(startX, startY, valueZ);
            pathDeck.push(nextN);
            //                        System.out.println("this is the next pathframe from N : \n" + nextN);
            if (valueZ == 0) {
              System.out.println(
                "//////////////////WE WON////////////////////"
              );
              pathDeck.clear();
            }
            break;
          default:
            tFrame.eraseLast();
            valueZ = 0;
            //     System.out.println("no more possible path. \n moveit() pathtotake : " + tFrame.pathToTake);
            //   System.out.println("no more possible path. \n moveit() pathdeck : " + pathDeck);
            if (!pathDeck.empty()) {
              pathFrame rootFrame = new pathFrame(0, 0, 0);
              rootFrame = pathDeck.pop();
              rootFrame.eraseLast();
              rootFrame.pathToTake--;
              //          System.out.println("\n\nmoveit() defaultcase rootframe "+rootFrame);
              pathDeck.push(rootFrame);
            }
            break;
        }
      } else {
        //System.out.println("moveit() else{} stack size : "+pathDeck.size());
        pathDeck.clear();
      }
    } while (!pathDeck.empty());
  }
}
