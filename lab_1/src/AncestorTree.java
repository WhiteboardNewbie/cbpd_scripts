interface IAncestorTree {
}

class Unknown implements IAncestorTree {
}

class PersonTree implements IAncestorTree {
  String name;
  IAncestorTree parentOne;
  IAncestorTree parentTwo;

  PersonTree(String name, IAncestorTree parentOne, IAncestorTree parentTwo) {
    this.name = name;
    this.parentOne = parentOne;
    this.parentTwo = parentTwo;
  }
}

class ExamplesAncestorTree {
  IAncestorTree unknown = new Unknown();
  IAncestorTree alice = new PersonTree("Alice", unknown, unknown);
  IAncestorTree bob = new PersonTree("Bob", unknown, unknown);
  IAncestorTree charlie = new PersonTree("Charlie", alice, bob);
  IAncestorTree dave = new PersonTree("Dave", alice, bob);
  IAncestorTree eve = new PersonTree("Eve", charlie, dave);
}
