// Exercise 14.7, Page 140

import tester.*;

// represents a grocery store item
interface Item {
  double unitPrice();
  boolean lowerUnitPrice(double price);
  boolean cheaperThan(Item that);
}

// represents an ice cream item
class IceCream implements Item {
  String brandName;
  double weight; // in grams
  double price; // in cents
  String flavour; 

  // constructor
  IceCream(String brandName, double weight, double price, String flavour) {
    this.brandName = brandName;
    this.weight = weight;
    this.price = price;
    this.flavour = flavour;
  }

  // calculates the unit price of the ice cream
  public double unitPrice() {
    return this.price / this.weight;
  }

  // checks if the unit price is lower than a given price
  public boolean lowerUnitPrice(double price) {
    return this.unitPrice() < price;
  }
  
  // checks if this ice cream is cheaper than another item
  public boolean cheaperThan(Item that) {
    return this.unitPrice() < that.unitPrice();
  }
}

// represents a coffee item
class ItemCoffee implements Item {
  String brandName;
  double weight; // in grams
  double price; // in cents
  String type; // regular or decaffeinated

  // constructor
  ItemCoffee(String brandName, double weight, double price, String type) {
    this.brandName = brandName;
    this.weight = weight;
    this.price = price;
    this.type = type;
  }

  // calculates the unit price of the coffee
  public double unitPrice() {
    return this.price / this.weight;
  }

  // checks if the unit price is lower than a given price
  public boolean lowerUnitPrice(double price) {
    return this.unitPrice() < price;
  }

  // checks if this coffee is cheaper than another item
  public boolean cheaperThan(Item that) {
    return this.unitPrice() < that.unitPrice();
  }
}

// represents a juice item
class Juice implements Item {
  String brandName;
  double weight; // in grams
  double price; // in cents
  String flavour;
  String Packaging; // frozen, fresh, bottled or canned

  // constructor
  Juice(String brandName, double weight, double price, String flavour, String Packaging) {
    this.brandName = brandName;
    this.weight = weight;
    this.price = price;
    this.flavour = flavour;
    this.Packaging = Packaging;
  }

  // calculates the unit price of the juice
  public double unitPrice() {
    return this.price / this.weight;
  }
  
  // checks if the unit price is lower than a given price
  public boolean lowerUnitPrice(double price) {
    return this.unitPrice() < price;
  }

  // checks if this juice is cheaper than another item
  public boolean cheaperThan(Item that) {
    return this.unitPrice() < that.unitPrice();
  }
}

class ExamplesItem {
  Item iceCream1 = new IceCream("BrandA", 500, 2500, "Vanilla");
  Item iceCream2 = new IceCream("BrandB", 300, 1800, "Chocolate");
  Item coffee1 = new ItemCoffee("CoffeeCo", 200, 1500, "Regular");
  Item juice1 = new Juice("JuiceBrand", 1000, 1200, "Orange", "Bottled");

  // tests the unit price calculation
  boolean testUnitPrice(Tester t) {
    return t.checkExpect(iceCream1.unitPrice(), 5.0) &&
           t.checkExpect(coffee1.unitPrice(), 7.5) &&
           t.checkExpect(juice1.unitPrice(), 1.2);
  }

  // tests the lower unit price method
  boolean testLowerUnitPrice(Tester t) {
    return t.checkExpect(iceCream1.lowerUnitPrice(6.0), true) &&
           t.checkExpect(coffee1.lowerUnitPrice(8.0), true) &&
           t.checkExpect(juice1.lowerUnitPrice(1.0), false);
  }

  // tests the cheaper than method
  boolean testCheaperThan(Tester t) {
    return t.checkExpect(iceCream1.cheaperThan(iceCream2), true) &&
           t.checkExpect(coffee1.cheaperThan(juice1), false) &&
           t.checkExpect(juice1.cheaperThan(iceCream2), true);
  }
}