public class FileInvalidException extends Exception {

  /**
   * Constructs a new FileInvalidException object with a default.
   */
  public FileInvalidException() {
    super(
      "Error- Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)"
    );
  }

  /**
   * Constructs a new FileInvalidException object and takes a customized message.
   * @param message Error message
   */
  public FileInvalidException(String message) {
    super(message);
  }

  /**
   * Returns a String representing the warning message
   * @Override toString in class Object
   * @return String - returns warning message
   */
  public String toString() {
    return getMessage();
  }

  /**
   *
   * @return String - Warning message
   */
  public String getMessage() {
    return super.getMessage();
  }
}
