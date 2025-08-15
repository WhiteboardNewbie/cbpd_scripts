interface IMenu {}

class Soup implements IMenu {
  String name;
  String price;
  Boolean isVegetarian;

  Soup(String name, String price, Boolean isVegetarian) {
    this.name = name;
    this.price = price;
    this.isVegetarian = isVegetarian;
  }
}

class Salad implements IMenu {
  String name;
  String price;
  Boolean isVegetarian;
  String dressing;

  Salad(String name, String price, Boolean isVegetarian, String dressing) {
    this.name = name;
    this.price = price;
    this.isVegetarian = isVegetarian;
    this.dressing = dressing;
  }
}

class Sandwich implements IMenu {
  String name;
  String price;
  String breadType;
  String fillingOne;
  String fillingTwo;

  Sandwich(String name, String price, String breadType, String fillingOne, String fillingTwo) {
    this.name = name;
    this.price = price;
    this.breadType = breadType;
    this.fillingOne = fillingOne;
    this.fillingTwo = fillingTwo;
  }
}

class ExamplesMenu {
  IMenu soup = new Soup("Tomato Soup", "$5.99", true);
  IMenu salad = new Salad("Caesar Salad", "$7.99", false, "Caesar Dressing");
  IMenu sandwich = new Sandwich("Club Sandwich", "$8.99", "Whole Wheat", "Turkey", "Lettuce");
  IMenu vegetarianSoup = new Soup("Vegetable Soup", "$5.49", true);
  IMenu vegetarianSalad = new Salad("Greek Salad", "$6.99", true, "Olive Oil");
  IMenu vegetarianSandwich = new Sandwich("Veggie Delight", "$7.49", "Multigrain", "Hummus", "Cucumber");
}