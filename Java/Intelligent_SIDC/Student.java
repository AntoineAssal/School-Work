package Intelligent_SIDC;
public class Student {

  private String name;
  private int key;

  public Student(int key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  @Override
  public String toString() {
    return "Student {name = " + name + ", key = " + key + '}';
  }
} // End student Class
