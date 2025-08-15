// Exercise 2.4, Page 17

// represents a car
class Automobile {
  String model;
  int price; // in dollars
  double mileage; // in miles per gallon
  boolean used;

  // the constructor
  Automobile(String model, int price, double mileage, boolean used) {
    this.model = model;
    this.price = price;
    this.mileage = mileage;
    this.used = used;
  }
}

class ExamplesAutomobile {
  Automobile car1 = new Automobile("Toyota Camry", 24000, 28.0, false);
  Automobile car2 = new Automobile("Honda Accord", 22000, 30.0, false);
  Automobile car3 = new Automobile("Ford Focus", 18000, 32, true);
}
