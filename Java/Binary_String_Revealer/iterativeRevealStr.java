package Binary_String_Revealer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.Integer;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class iterativeRevealStr {

  public static PrintWriter pw;
  public static PrintWriter runWrite;

  /**
   * Iterative method to generate the possible combinations
   *
   * @param inputString A string composed of 1s, 0s and *s (randomly generated in main)
   */
  public static void revealStrIterative(String inputString) {
    Stack<String> stack = new Stack<String>();
    stack.push(inputString);
    int index;
    while (!stack.empty()) {
      String out = stack.pop();
      if ((index = out.indexOf('*')) != -1) {
        for (char i = '0'; i <= '1'; i++) {
          out = out.substring(0, index) + i + out.substring(index + 1);
          stack.push(out);
        }
      } else {
        System.out.println(out);
        pw.println(out); // Print output to file. Since we're using a static printwriter we dont need to provide any more info.
      }
    }
  }

  /**
   * Randomly Generates string
   *
   * @param counter that represent the # of the iteration of the program
   * @return randomly generated strings of 1 0 *
   */
  public static String genString(int counter) {
    Random rand = new Random();
    String digit = "";
    for (int i = 0; i < counter * 2; i++) {
      Integer test = rand.nextInt(3);
      if (test == 2) {
        digit += "*";
      } else {
        digit += test.toString();
      }
    }
    System.out.println("Here is the random generated binary string : " + digit);
    return digit;
  }

  public static void main(String[] args) {
    pw = null;
    runWrite = null;
    try {
      runWrite = new PrintWriter(new FileOutputStream("RunTime.txt", true));
    } catch (FileNotFoundException e) {
      System.out.println("error");
    }
    System.out.println("Welcome to the binary combination finder ");
    Scanner keyboard = new Scanner(System.in);
    long startTime; // var to keep track of time per run. Not instantiating yet for accuracy
    long startTimeT = System.nanoTime(); // var to keep track of total run time.
    int stars = 0;
    int response1, counter1 = 0; // vars to control loops and count iterations
    do {
      System.out.println("Would you like to run a combination?");
      System.out.println("1 for yes and 0 for no");
      response1 = keyboard.nextInt();
      if (response1 == 1) {
        counter1++;
        System.out.println("Iteration #" + counter1);
        String file0 = "out" + counter1 + ".txt";
        try {
          pw = new PrintWriter(new FileOutputStream(file0, true));
        } catch (FileNotFoundException e) {
          System.out.println("error");
        }
        String test1 = new String(genString(counter1));
        pw.println("Random generated number [" + counter1 + "] : " + test1);
        for (int i = 0; i < test1.length(); i++) {
          if (test1.charAt(i) == '*') {
            stars++;
          }
        }
        startTime = System.nanoTime();
        revealStrIterative(test1);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        pw.println(
          "Iteration #" +
          counter1 +
          " run time : " +
          timeElapsed +
          " nanoseconds. For replacing " +
          stars +
          " *s"
        );
        runWrite.println(
          "Iteration #" +
          counter1 +
          " run time : " +
          timeElapsed +
          " nanoseconds. For replacing " +
          stars +
          " *s in a string of size " +
          test1.length()
        );
      }
      stars = 0;
      pw.close();
    } while (response1 == 1 && counter1 < 50);
    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTimeT;
    runWrite.println(
      "The whole program run time : " + timeElapsed + " in nanoseconds"
    );
    keyboard.close();
    runWrite.close();
  }
}
