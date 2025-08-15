// Problem 2

// represents an ice cream item
interface IceCreamP2 {}

// represents an empty serving
class EmptyServing implements IceCreamP2 {
  boolean cone; // cup, if false

  // constructor
  EmptyServing(boolean cone) {
    this.cone = cone;
  }
}

class Scooped implements IceCreamP2 {
  IceCreamP2 more;
  String flavour; // forms a flavour on top of more

  // constructor
  Scooped(IceCreamP2 more, String flavour) {
    this.more = more;
    this.flavour = flavour;
  }
}

class ExamplesIceCreamP2 {

  IceCreamP2 serving1 = new EmptyServing(true);
  IceCreamP2 serving2 = new Scooped(this.serving1, "Chocolate");
  IceCreamP2 serving3 = new Scooped(this.serving2, "Vanilla");
  IceCreamP2 serving4 = new Scooped(this.serving3, "Strawberry");
}