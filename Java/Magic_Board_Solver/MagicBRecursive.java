package Magic_Board_Solver;

import java.util.*;

public class MagicBRecursive {

  private int sizeD;
  private int[][] theBoard;
  private int goalX;
  private int goalY;
  static int startX;
  static int startY;
  static int valueZ;
  static boolean didwewin = false;
  static ArrayList<String> pastXY = new ArrayList<String>();

  public static int getValueZ() {
    return valueZ;
  }

  public static int getStartX() {
    return startX;
  }

  public static int getStartY() {
    return startY;
  }

  public MagicBRecursive(int size) {
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
    System.out.println(
      "------------- This is the generated board : -------------"
    );
    theBoard = temp;
  }

  public void printMBR() {
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

  public void possibleMove(int x, int y, int n, int nbRecursion) {
    nbRecursion++;
    if (x + n < sizeD && didwewin == false) {
      if (!pastXY.contains((x + n) + "," + y)) {
        System.out.println("Recursive call #" + nbRecursion);
        System.out.println(
          "previous movement: x :" +
          x +
          " | y :" +
          y +
          " | square value : " +
          theBoard[x][y]
        );
        System.out.println(
          "this next movement is taking the direction to the RIGHT"
        );
        System.out.println(
          "New coordinates : " +
          (x + n) +
          " , " +
          y +
          " with value : " +
          theBoard[x + n][y]
        );
        printUpdate((x + n), y);
        pastXY.add((x + n) + "," + y);
        if (theBoard[x + n][y] == 0) {
          System.out.println("WE WON");
          didwewin = true;
          return;
        }
        possibleMove((x + n), y, theBoard[x + n][y], nbRecursion);
      }
    }
    if ((x - n) >= 0 && didwewin == false) {
      if (!pastXY.contains((x - n) + "," + y)) {
        System.out.println("Recursive call #" + nbRecursion);
        System.out.println(
          "previous movement: x :" +
          x +
          " | y :" +
          y +
          " | square value : " +
          theBoard[x][y]
        );
        System.out.println(
          "this next movement is taking the direction to the RIGHT"
        );
        System.out.println(
          "New coordinates : " +
          (x - n) +
          " , " +
          (y) +
          " with value : " +
          theBoard[x - n][y]
        );
        printUpdate((x - n), y);
        pastXY.add((x - n) + "," + y);
        if (theBoard[x - n][y] == 0) {
          System.out.println("WE WON");
          didwewin = true;
          return;
        }
        possibleMove((x - n), y, theBoard[x - n][y], nbRecursion);
      }
    }
    if (y + n < sizeD && didwewin == false) {
      if (!pastXY.contains(x + "," + (y + n))) {
        System.out.println("Recursive call #" + nbRecursion);
        System.out.println(
          "previous movement: x :" +
          x +
          " | y :" +
          y +
          " | square value : " +
          theBoard[x][y]
        );
        System.out.println(
          "this next movement is taking the direction to the RIGHT"
        );
        System.out.println(
          "New coordinates : " +
          (x) +
          " , " +
          (y + n) +
          " with value : " +
          theBoard[x][y + n]
        );
        printUpdate(x, (y + n));
        pastXY.add(x + "," + (y + n));
        if (theBoard[x][y + n] == 0) {
          System.out.println("WE WON");
          didwewin = true;
          return;
        }
        possibleMove(x, (y + n), theBoard[x][y + n], nbRecursion);
      }
    }
    if (y - n >= 0 && didwewin == false) {
      if (!pastXY.contains(x + "," + (y - n))) {
        System.out.println("Recursive call #" + nbRecursion);

        System.out.println(
          "previous movement: x :" +
          x +
          " | y :" +
          y +
          " | square value : " +
          theBoard[x][y]
        );
        System.out.println(
          "this next movement is taking the direction to the RIGHT"
        );
        System.out.println(
          "New coordinates : " +
          (x) +
          " , " +
          (y - n) +
          " with value : " +
          theBoard[x][y - n]
        );
        printUpdate(x, (y - n));
        pastXY.add(x + "," + (y - n));
        if (theBoard[x][y - n] == 0) {
          System.out.println("WE WON");
          didwewin = true;
          return;
        }
        possibleMove(x, (y - n), theBoard[x][y - n], nbRecursion);
      }
    }
    if (didwewin == false && nbRecursion == 1) {
      System.out.println("this game is not solvable");
    }
    return;
  }
}
