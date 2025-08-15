// Problem 1

// represents a dog
class Dog {
  String name;
  String breed;
  int yob; // year of birth
  String state;
  boolean hypoallergenic;

  // constructor
  Dog(String name, String breed, int yob, String state, boolean hypoallergenic) {
    this.name = name;
    this.breed = breed;
    this.yob = yob;
    this.state = state;
    this.hypoallergenic = hypoallergenic;
  }
}

class ExamplesDog {
  Dog dog1 = new Dog("Buddy", "Golden Retriever", 2015, "California", false);
  Dog dog2 = new Dog("Max", "Poodle", 2018, "New York", true);
  Dog dog3 = new Dog("Bella", "Beagle", 2020, "Texas", false);
}
