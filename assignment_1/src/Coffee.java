// Exercise 10.6, Page 102

import tester.*;
// represents a coffee sale
class Coffee {
  String kind;
  double price;
  double weight;

  // constructor
  Coffee(String kind, int price, int weight) {
    this.kind = kind;
    this.price = price; // per weight unit
    this.weight = weight;
  }

  // calculates the total cost of a coffee sale, with discount
  double cost() {
    if (this.weight < 5000) {
      return this.price * this.weight;
    } else if (5000 <= this.weight && this.weight < 20000) {
      return 0.9 * this.price * this.weight; // 10% discount
    } else {
      return 0.75 * this.price * this.weight; // 25% discount
    }
  }
}

class ExamplesCoffee {
 Coffee coffee1 = new Coffee("Espresso", 10, 300);
 Coffee coffee2 = new Coffee("Latte", 15, 1000);
 Coffee coffee3 = new Coffee("Cappuccino", 18, 25000);

 boolean testCost(Tester t) {
  return t.checkExpect(coffee1.cost(), 3000.0) &&
         t.checkExpect(coffee2.cost(), 15000.0) &&
         t.checkExpect(coffee3.cost(), 337500.0);
 }
}
