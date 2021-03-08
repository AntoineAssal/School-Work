package Bibliography_Maker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BibFactory {

  // Declare the scanner and printwriter arrays
  static Scanner[] inputs;
  static PrintWriter[][] outputs;
  // Constants to be used in all program
  static final String IEEE = "IEEE";
  static final String ACM = "ACM";
  static final String NJ = "NJ";

  /**
   * This method will display the file that user selected to be reviewed.
   *
   * @param bf BufferReader object that will read fro,
   * @throws IOException Exception thrown by the readline method
   */
  public static void displayFile(BufferedReader bf) throws IOException {
    String line = bf.readLine();

    while (line != null) {
      System.out.println(line);
      line = bf.readLine();
    }
    bf.close();
  } // End displayFile method

  /**
   * @param input   Scanner object that represents the input .bib file
   * @param outputs Array of 3 printwriters for every JSON output files
   * @param number  int to represent .bib file number
   * @return boolean to give true if its invalid that is it has an empty field and false otherwise
   */
  public static boolean processFilesForValidation(
    Scanner input,
    PrintWriter[] outputs,
    int number
  ) {
    boolean isNotValid = false;
    input.useDelimiter("},");

    try {
      int counter = 0;
      while (input.hasNext()) {
        String s = input.next().trim();
        // Break out of for loop if it's a } and not a }, means that its at the end of the @Article
        if (s.trim().equals("}")) break;
        String[][] bib = new String[11][2];
        for (int i = 0; i < bib.length; i++) {
          String elementKey;
          // Article always starts with @Article and first key element is between , and =
          if (s.contains("@ARTICLE{")) {
            counter++;
            elementKey =
              s.substring(s.lastIndexOf(",") + 1, s.indexOf("=")).trim();
          } else {
            elementKey = s.substring(0, s.indexOf("=")).trim();
          }
          bib[i][0] = elementKey.trim();

          String elementValue = s
            .substring(s.lastIndexOf("{") + 1, s.length())
            .trim();
          bib[i][1] = elementValue;

          if (elementValue.trim().isEmpty()) {
            isNotValid = true;
            throw new FileInvalidException(
              "Error: Empty field found! Problem found with the input file: Latex" +
              number +
              ".bib\nFile is invalid: field \"" +
              elementKey +
              "\" is empty. Other empty fields may be present. Processing stopped\n"
            );
          }

          if (i < bib.length - 1) s = input.next().trim();
        }

        for (int i = 0; i < outputs.length; i++) {
          outputs[i].append(generateBib(bib, counter)[i]);
        }
      }
    } catch (FileInvalidException e) {
      System.out.println(e);
      input.close();
      for (PrintWriter output : outputs) {
        output.close();
      }
      File invalidFile = new File("Output/" + IEEE + number + ".json");
      File invalidFile2 = new File("Output/" + ACM + number + ".json");
      File invalidFile3 = new File("Output/" + NJ + number + ".json");
      invalidFile.delete();
      invalidFile2.delete();
      invalidFile3.delete();
    }
    return isNotValid;
  } // End processFilesForValidation

  /**
   * Main method where all the scanners and printwriters for file input and output are created.
   * Invokes all the other methods and catches the exceptions.
   *
   * @param args string array
   */
  public static void main(String[] args) {
    System.out.println("Welcome to Antoine's Bibliography Factory!");
    // Assume that all files will be called Latex
    final String FILENAME = "Latex";
    String jsonFileName = "";

    // Variables to keep track of input, output and invalid files
    int indexInputs = 0;
    int indexOutputs = 0;
    int counterInvalidFiles = 0;

    // Scanners to store input streams
    inputs = new Scanner[10];

    // 2D array, that will contain an array of 3 PrintWriters for each input files read
    // outputs[i][0] represents IEEE, outputs[i][1] represents ACM and outputs[i][2] represents NJ.
    // i corresponds to the corresponding Bib File
    outputs = new PrintWriter[10][3];

    // Try opening each Bib file to be read
    try {
      // Create a new Scanner to read from each Bib file
      for (indexInputs = 0; indexInputs < inputs.length; indexInputs++) {
        inputs[indexInputs] =
          new Scanner(
            new FileInputStream("Data/" + FILENAME + (indexInputs + 1) + ".bib")
          );
      }
    } catch (FileNotFoundException e) {
      System.out.println(
        "Could not open input file " +
        FILENAME +
        (indexInputs + 1) +
        ".bib" +
        " for reading. " +
        "Please check if file exists! Program will terminate after closing any opened files."
      );

      // Close all scanners that were opened
      if (indexInputs != 0) { // Make sure there's something to close
        for (int i = indexInputs - 1; i >= 0; i--) {
          inputs[i].close();
        }
      }
      System.exit(0);
    }
    // Create the 3 json files to output bibliographies
    try {
      for (indexInputs = 0; indexInputs < inputs.length; indexInputs++) {
        jsonFileName = "Output/" + IEEE + (indexInputs + 1) + ".json";
        outputs[indexInputs][indexOutputs] =
          new PrintWriter(new FileOutputStream(jsonFileName));
        indexOutputs++;

        jsonFileName = "Output/" + ACM + (indexInputs + 1) + ".json";
        outputs[indexInputs][indexOutputs] =
          new PrintWriter(new FileOutputStream(jsonFileName));
        indexOutputs++;

        jsonFileName = "Output/" + NJ + (indexInputs + 1) + ".json";
        outputs[indexInputs][indexOutputs] =
          new PrintWriter(new FileOutputStream(jsonFileName));

        indexOutputs = 0;
      }

      for (int i = 0; i < inputs.length; i++) {
        boolean incrementInvalid = processFilesForValidation(
          inputs[i],
          outputs[i],
          (i + 1)
        );
        if (incrementInvalid) counterInvalidFiles++;
      }
    } catch (FileNotFoundException e) {
      System.out.println("Could not create " + jsonFileName);
      System.out.println(
        "Program will terminate after deleting already created output files and all opened files."
      );
      // Close output printwriters
      for (int i = indexInputs; i >= 0; i--) {
        while (indexOutputs >= 0) {
          outputs[indexInputs][indexOutputs].close();
          indexOutputs--;
        }
      }
      // deleet all json files inputted in  directory
      deleteOutputted("Output/");
      // Close input scanners
      for (Scanner input : inputs) {
        input.close();
      }

      System.exit(0);
    } finally {
      // Close output printwriters
      for (Scanner input : inputs) {
        input.close();
      }
      // Close input scanners
      for (PrintWriter[] output : outputs) {
        for (int j = 0; j < outputs[0].length; j++) {
          output[j].close();
        }
      }
      System.out.println(
        "\nA total of " +
        counterInvalidFiles +
        " files were invalid, and could not be processed." +
        " All other " +
        (10 - counterInvalidFiles) +
        " \"valid\" files have been created."
      );
    }

    Scanner input = new Scanner(System.in);
    System.out.println(
      "Please enter the name of one of the files you need to review: "
    );
    String userFile = input.next().trim();
    BufferedReader bf = null;
    try {
      bf = new BufferedReader(new FileReader("Output/" + userFile));
      displayFile(bf);
    } catch (FileNotFoundException e) {
      // ask user to try again
      System.out.println(
        "Could not display file. File does not exist. You get one more try enter file name: "
      );
      userFile = input.next().trim();
      try {
        bf = new BufferedReader(new FileReader("Output/" + userFile));
        displayFile(bf);
      } catch (FileNotFoundException f) {
        System.out.println(
          "Could not open output file again! Either file does not exist or could not be created." +
          "Unable to display your desired files! Program will now exit!"
        );
        System.exit(0);
      } catch (IOException g) {
        System.out.println(
          "Error! Could not read content of the file. Program will now terminate"
        );
        System.exit(0);
      }
    } catch (IOException e) {
      System.out.println(
        "Error! Could not read content of the file. Program will now terminate"
      );
      System.exit(0);
    }
    input.close();
    System.out.println(
      "Thank you for using Antoine's Bibliography Manager :D "
    );
  } // End Main method

  /**
   * This method check if the file path directory exists and if it does then it
   * deletes outputted JSON files (deemed to be invalid)
   *
   * @param path Directory of the file that will be deleted
   */
  public static void deleteOutputted(String path) {
    File file = new File(path);

    if (file.isDirectory()) {
      String[] files = file.list();
      if (files.length > 0) {
        for (String fileName : files) {
          File deleteFile = new File(path + "/" + fileName); // Objects of files to get deleted
          deleteFile.delete();
        }
      }
    }
  } // End deleteOutputted method

  /**
   * This method generates thew bibliographies in IEEE, ACM and NJ formats
   *
   * @param bib    2D Array like a dictionary, containing element keys and corresponding values
   * @param number counter to keep track how many bibliographies are being created
   * @return String[] Array of strings that have the bibliography in the corresponding format
   */
  public static String[] generateBib(String[][] bib, int number) {
    // All articles have 9 fields but not all formats
    String[][] bibliography = new String[9][3];
    for (String[] strings : bibliography) {
      Arrays.fill(strings, "");
    }
    for (String[] value : bib) {
      String element = value[0].toLowerCase();
      switch (element) {
        case "author":
          String[] authors = value[1].split("and");
          bibliography[0][1] = "[" + number + "]\t"; // ACM
          for (int i = 0; i < authors.length; i++) {
            authors[i] = authors[i].trim();
            // IEEE and NJ
            if (i < authors.length - 1) {
              bibliography[0][0] += authors[i] + ",";
              bibliography[0][2] += authors[i] + " & ";
            } else {
              bibliography[0][2] += authors[i] + ". ";
              bibliography[0][2] += authors[i] + ". ";
            }
            if (authors.length == 1) bibliography[0][1] +=
              authors[i] + "."; else if (i == 0) bibliography[0][1] +=
              authors[i] + " et al. ";
          }
          break;
        case "title":
          bibliography[1][0] = "\"" + value[1] + "\", ";
          bibliography[2][1] = value[1] + ". ";
          bibliography[1][2] = value[1] + ". ";
          break;
        case "journal":
          bibliography[2][0] = value[1] + ", ";
          bibliography[3][1] = value[1] + ". ";
          bibliography[2][2] = value[1] + ". ";
          break;
        case "volume":
          bibliography[3][0] = "vol. " + value[1] + ", ";
          bibliography[4][1] = value[1] + ", ";
          bibliography[3][2] = value[1] + ", ";
          break;
        case "number":
          bibliography[4][0] = "no. " + value[1] + ", ";
          bibliography[5][1] = value[1] + " ";
          bibliography[4][2] = "";
          break;
        case "year":
          bibliography[7][0] = value[1] + ".\n\n";
          bibliography[1][1] = value[1] + ". ";
          bibliography[6][1] = "(" + value[1] + "), ";
          bibliography[6][2] = "(" + value[1] + ").\n\n";
          break;
        case "pages":
          bibliography[5][0] = "p. " + value[1] + ", ";
          bibliography[7][1] = value[1] + ". ";
          bibliography[5][2] = value[1];
          break;
        case "doi":
          bibliography[8][1] = "DOI:https://doi.org/" + value[1] + ".\n\n";
          break;
        case "month":
          bibliography[6][0] = value[1] + " ";
          break;
        default:
          break;
      }
    }

    StringBuilder IEEEOut = new StringBuilder();
    StringBuilder ACMOut = new StringBuilder();
    StringBuilder NJOut = new StringBuilder();

    for (String[] strings : bibliography) {
      IEEEOut.append(strings[0]);
      ACMOut.append(strings[1]);
      NJOut.append(strings[2]);
    }
    return new String[] {
      IEEEOut.toString(),
      ACMOut.toString(),
      NJOut.toString(),
    };
  }
} // End BibFactory Class
