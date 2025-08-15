// Problem 3

interface Housing {
}

class Hut implements Housing {
  int capacity;
  int population; // population < capacity

  // constructor
  Hut(int capacity, int population) {
    this.capacity = capacity;
    this.population = population;
  }
}

class Inn implements Housing {
  String name;
  int capacity;
  int population; // population < capacity
  int stalls; // number of stalls available

  // constructor
  Inn(String name, int capacity, int population, int stalls) {
    this.name = name;
    this.capacity = capacity;
    this.population = population;
    this.stalls = stalls; // number of horses it can hold
  }
}

class Castle implements Housing {
  String name;
  String familyName; // of owners
  int population;
  int carriageHouse; // number of carriages it can hold

  // constructor
  Castle(String name, String familyName, int population, int carriageHouse) {
    this.name = name;
    this.familyName = familyName;
    this.population = population;
    this.carriageHouse = carriageHouse;
  }
}

class ExamplesHousing {
  Housing hovel = new Hut(5, 1);
  Housing winterfell = new Castle("Winterfell", "Stark", 500, 6);
  Housing crossroads = new Inn("Inn At The Crossroads", 40, 20, 12);
}

interface Transportation {
}

class Horse implements Transportation {
  String name;
  String color;
  Housing from;
  Housing to; // can only go to an inn if there is room in the stables

  // constructor
  Horse(String name, String color, Housing from, Housing to) {
    this.name = name;
    this.color = color;
    this.from = from;
    this.to = to;
  }
}

class Carriage implements Transportation {
  int tonnage;
  Housing from;
  Housing to; // can only go to a castle if there is room in the carriage house

  // constructor
  Carriage(int tonnage, Housing from, Housing to) {
    this.tonnage = tonnage;
    this.from = from;
    this.to = to;
  }
}

class ExamplesTransportation {
  Transportation horse1 = new Horse("Shadowfax", "Grey", new Hut(5, 1), new Inn("The Prancing Pony", 30, 15, 10));
  Transportation horse2 = new Horse("Bucephalus", "Black", new Castle("The Red Keep", "Lannister", 300, 5), new Inn("The Eyrie", 20, 10, 5));
  
  Transportation carriage1 = new Carriage(2, new Inn("The Inn of the Last Home", 50, 25, 15), new Castle("Dragonstone", "Targaryen", 200, 10));
  Transportation carriage2 = new Carriage(3, new Hut(10, 2), new Castle("Storm's End", "Baratheon", 150, 8));
}