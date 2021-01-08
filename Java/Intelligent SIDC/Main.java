public class Main {

  public static void main(String[] args) {
    IntelligentSIDC intelligentSIDC = new IntelligentSIDC(100);
    IntelligentSIDC intelligentSIDC2 = new IntelligentSIDC(1000);
    IntelligentSIDC intelligentSIDC3 = new IntelligentSIDC(2500);
    IntelligentSIDC intelligentSIDC4 = new IntelligentSIDC(5000);
    IntelligentSIDC intelligentSIDC5 = new IntelligentSIDC(10000);

    // MyArrayList<Integer> list = intelligentSIDC.generate(99);
    /* for (int i = 0; i < list.size(); i++){
            int key = list.get(i);
            Student student = new Student(key, "test" + key);
            intelligentSIDC.add(key, student);
        }*/
    for (int key : intelligentSIDC.generate(99)) {
      Student student = new Student(key, "Student");
      intelligentSIDC.add(key, student);
    }
    for (int key : intelligentSIDC2.generate(999)) {
      Student student = new Student(key, "Student");
      intelligentSIDC2.add(key, student);
    }
    for (int key : intelligentSIDC3.generate(2499)) {
      Student student = new Student(key, "Student");
      intelligentSIDC3.add(key, student);
    }
    for (int key : intelligentSIDC4.generate(4999)) {
      Student student = new Student(key, "Student");
      intelligentSIDC4.add(key, student);
    }
    for (int key : intelligentSIDC5.generate(9999)) {
      Student student = new Student(key, "Student");
      intelligentSIDC5.add(key, student);
    }
    System.out.println("Data Set 1");
    System.out.println("ADT type being used now is : " + intelligentSIDC.ADT());
    System.out.println("Current Size is : " + intelligentSIDC.getSize());
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC.allKeys()
    );
    System.out.println(
      "The 8th element is: " +
      intelligentSIDC.getValues(intelligentSIDC.allKeys().get(7))
    );
    System.out.println(
      "Now lets test getting the next one, the next key is : " +
      intelligentSIDC.nextKey(intelligentSIDC.allKeys().get(7))
    );
    System.out.println(
      "Now lets test getting the previous one, the previous key is : " +
      intelligentSIDC.previousKey(intelligentSIDC.allKeys().get(7))
    );
    System.out.println("********************************************");

    System.out.println(
      "Add 1 more so it reaches threshold the threshold we set"
    );
    int extraKey = intelligentSIDC.generate(1).get(0);
    intelligentSIDC.add(extraKey, new Student(extraKey, "reee")); // still a sequence
    System.out.println("Just added : " + extraKey + " into the ADT.");
    System.out.println("Current Size is : " + intelligentSIDC.getSize()); // now getsize is called from AVL
    System.out.println("ADT type being used now is : " + intelligentSIDC.ADT()); // adt type is AVL
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC.allKeys()
    ); // allkeys called from AVL
    System.out.println("Current Size is : " + intelligentSIDC.getSize()); // now getsize is called from AVL

    System.out.println("Data Set 2");
    System.out.println(
      "ADT type being used now is : " + intelligentSIDC2.ADT()
    );
    System.out.println("Current Size is : " + intelligentSIDC2.getSize());
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC2.allKeys()
    );
    System.out.println(
      "The 80th element is: " +
      intelligentSIDC2.getValues(intelligentSIDC2.allKeys().get(79))
    );
    System.out.println(
      "Now lets test getting the next one, the next key is : " +
      intelligentSIDC2.nextKey(intelligentSIDC2.allKeys().get(79))
    );
    System.out.println(
      "Now lets test getting the previous one, the previous key is : " +
      intelligentSIDC2.previousKey(intelligentSIDC2.allKeys().get(79))
    );
    System.out.println("********************************************");

    System.out.println(
      "Add 1 more so it reaches threshold the threshold we set"
    );
    int extraKey2 = intelligentSIDC2.generate(1).get(0);
    intelligentSIDC2.add(extraKey, new Student(extraKey2, "reee")); // still a sequence
    System.out.println("Just added : " + extraKey2 + " into the ADT.");
    System.out.println("Current Size is : " + intelligentSIDC2.getSize()); // now getsize is called from AVL
    System.out.println(
      "ADT type being used now is : " + intelligentSIDC2.ADT()
    ); // adt type is AVL
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC2.allKeys()
    ); // allkeys called from AVL
    System.out.println("Current Size is : " + intelligentSIDC2.getSize()); // now getsize is called from AVL

    System.out.println("Data Set 3");
    System.out.println(
      "ADT type being used now is : " + intelligentSIDC3.ADT()
    );
    System.out.println("Current Size is : " + intelligentSIDC3.getSize());
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC3.allKeys()
    );
    System.out.println(
      "The 702nd element is: " +
      intelligentSIDC3.getValues(intelligentSIDC3.allKeys().get(701))
    );
    System.out.println(
      "Now lets test getting the next one, the next key is : " +
      intelligentSIDC3.nextKey(intelligentSIDC3.allKeys().get(701))
    );
    System.out.println(
      "Now lets test getting the previous one, the previous key is : " +
      intelligentSIDC3.previousKey(intelligentSIDC3.allKeys().get(701))
    );
    System.out.println("********************************************");

    System.out.println(
      "Add 1 more so it reaches threshold the threshold we set"
    );
    int extraKey3 = intelligentSIDC3.generate(1).get(0);
    intelligentSIDC3.add(extraKey, new Student(extraKey3, "reee")); // still a sequence
    System.out.println("Just added : " + extraKey3 + " into the ADT.");
    System.out.println("Current Size is : " + intelligentSIDC3.getSize()); // now getsize is called from AVL
    System.out.println(
      "ADT type being used now is : " + intelligentSIDC3.ADT()
    ); // adt type is AVL
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC3.allKeys()
    ); // allkeys called from AVL
    System.out.println("Current Size is : " + intelligentSIDC3.getSize()); // now getsize is called from AVL

    System.out.println("Data Set 4");
    System.out.println(
      "ADT type being used now is : " + intelligentSIDC4.ADT()
    );
    System.out.println("Current Size is : " + intelligentSIDC4.getSize());
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC4.allKeys()
    );
    System.out.println(
      "The 702nd element is: " +
      intelligentSIDC4.getValues(intelligentSIDC4.allKeys().get(701))
    );
    System.out.println(
      "Now lets test getting the next one, the next key is : " +
      intelligentSIDC4.nextKey(intelligentSIDC4.allKeys().get(701))
    );
    System.out.println(
      "Now lets test getting the previous one, the previous key is : " +
      intelligentSIDC4.previousKey(intelligentSIDC4.allKeys().get(701))
    );
    System.out.println("********************************************");

    System.out.println(
      "Add 1 more so it reaches threshold the threshold we set"
    );
    int extraKey4 = intelligentSIDC4.generate(1).get(0);
    intelligentSIDC4.add(extraKey, new Student(extraKey4, "reee")); // still a sequence
    System.out.println("Just added : " + extraKey4 + " into the ADT.");
    System.out.println("Current Size is : " + intelligentSIDC4.getSize()); // now getsize is called from AVL
    System.out.println(
      "ADT type being used now is : " + intelligentSIDC4.ADT()
    ); // adt type is AVL
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC4.allKeys()
    ); // allkeys called from AVL
    System.out.println("Current Size is : " + intelligentSIDC4.getSize()); // now getsize is called from AVL

    System.out.println("Data Set 5");
    System.out.println(
      "ADT type being used now is : " + intelligentSIDC5.ADT()
    );
    System.out.println("Current Size is : " + intelligentSIDC5.getSize());
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC5.allKeys()
    );
    System.out.println(
      "The 702nd element is: " +
      intelligentSIDC5.getValues(intelligentSIDC5.allKeys().get(701))
    );
    System.out.println(
      "Now lets test getting the next one, the next key is : " +
      intelligentSIDC5.nextKey(intelligentSIDC5.allKeys().get(701))
    );
    System.out.println(
      "Now lets test getting the previous one, the previous key is : " +
      intelligentSIDC5.previousKey(intelligentSIDC5.allKeys().get(701))
    );
    System.out.println("********************************************");

    System.out.println(
      "Add 1 more so it reaches threshold the threshold we set"
    );
    int extraKey5 = intelligentSIDC5.generate(1).get(0);
    intelligentSIDC5.add(extraKey, new Student(extraKey5, "reee")); // still a sequence
    System.out.println("Just added : " + extraKey5 + " into the ADT.");
    System.out.println("Current Size is : " + intelligentSIDC5.getSize()); // now getsize is called from AVL
    System.out.println(
      "ADT type being used now is : " + intelligentSIDC5.ADT()
    ); // adt type is AVL
    System.out.println(
      "Here is the list of keys being used:\n" + intelligentSIDC5.allKeys()
    ); // allkeys called from AVL
    System.out.println("Current Size is : " + intelligentSIDC5.getSize()); // now getsize is called from AVL
  }
}
