// Exercise 15.8, Page 171

import tester.*;

// a river system
interface IRiver {
  
  // ??? nnn();
  
  // counts the number of soruces for this system
  int sources();

  // checks if a given location is part of this river system
  boolean onRiver(Location aloc);

  // finds the total length of this river system
  int length();

  // finds the max length along rivers of this system
  int maxLength();

  // counts the confluences in this system
  int confluences();

  // produces the list of all locations along this river system
  ILoLoc locations();

}

// a river source
class Source implements IRiver {
  int miles;
  Location loc;

  /* 
   * ??? nnn() {
   *  ... this.miles ...
   *  ... this.loc.mmm() ...
   * } 
   */

  Source(Location loc, int miles) {
    this.loc = loc;
    this.miles = miles;
  }
  
  public int sources() {
    return 1;
  }

  public boolean onRiver(Location aloc) {
    return this.loc.sameAs(aloc);
  }

  public int length() {
    return this.miles;
  }

  public int maxLength() {
    return this.miles; 
  }

  public int confluences() {
    return 0;
  }

  public ILoLoc locations() {
    return new ConsLoLoc(this.loc, new Empty());
  }
}

// a confluence of two rivers
class Confluence implements IRiver {
  int miles;
  Location loc;
  IRiver left;
  IRiver right;

  /*
   * ??? nnn() {
   *  ... this.miles ...
   *  ... this.loc.mmm()
   *  ... this.left.nnn() ...
   *  ... this.right.nnn() ...
   *  } 
   */

  Confluence(Location loc, IRiver left, IRiver right, int miles) {
    this.loc = loc;
    this.left = left;
    this.right = right;
    this.miles = miles; // assuming miles is the distance from the source to the confluence
  }
  
  public int sources() {
    return this.left.sources() + this.right.sources();
  }

  public boolean onRiver(Location aloc) {
    return this.loc.sameAs(aloc) || this.left.onRiver(aloc) || this.right.onRiver(aloc);
  }

  public int length() {
    return this.miles + this.left.length() + this.right.length();
  }

  public int maxLength() {
    return Math.max(this.miles + this.left.maxLength(), 
                    this.miles + this.right.maxLength());
  }

  public int confluences() {
    return 1 + this.left.confluences() + this.right.confluences();
  }

  public ILoLoc locations() {
    return new ConsLoLoc(this.loc, 
                         this.left.locations().append(this.right.locations()));
  }
}    

// a location of a river
class Location {
  int x;
  int y;
  String name;

  /*
   * ??? nnn() {
   *  ... this.miles ...
   *  ... this.x ... this.y ... this.name ...
   *  }
   */

  Location(int x, int y, String name) {
    this.x = x;
    this.y = y;
    this.name = name;
  }

  boolean sameAs(Location aloc) {
    return this.x == aloc.x && this.y == aloc.y && this.name.equals(aloc.name);
  }
}

// a river's end
class Mouth {
  Location loc;
  IRiver river;

  /*
   * ??? nnn() {
   *  ... this.loc.mmm() ...
   *  ... this.river.nnn() ...
   *  } 
   */

  Mouth(Location loc, IRiver river) {
    this.loc = loc;
    this.river = river;
  }

  // counts the number of sources feeding this mouth
  int sources() {
    return this.river.sources();
  }

  // checks if a given location is part of the system leading to this mouth
  boolean onRiver(Location aloc) {
    return this.loc.sameAs(aloc) || this.river.onRiver(aloc);
  }

  // calculates the total length of the river system leading to this mouth
  int length() {
    return this.river.length();
  }

  // finds the max length along rivers leading to this mouth
  int maxLength() {
    return this.river.maxLength();
  }

  // counts the number of confluences in the system feeding this mouth
  int confluences() {
    return this.river.confluences();
  }

  // produces the list of all locations along the river system leading to this mouth
  ILoLoc locations() {
    return new ConsLoLoc(this.loc, this.river.locations());
  }
}

// represents a list of locations
interface ILoLoc {
  // Append another list to this list
  ILoLoc append(ILoLoc that);
}

// an empty list of locations
class Empty implements ILoLoc {
  public ILoLoc append(ILoLoc that) {
    // Appending anything to an empty list just returns the other list
    return that;
  }
}

// a non-empty list of locations
class ConsLoLoc implements ILoLoc {
  Location first;
  ILoLoc rest;

  ConsLoLoc(Location first, ILoLoc rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoLoc append(ILoLoc that) {
    // Recursively append 'that' to the rest, then construct a new ConsLoLoc
    return new ConsLoLoc(this.first, this.rest.append(that));
  }
}

class ExamplesRiver {

  Location loc1 = new Location(0, 0, "Source1");
  Location loc2 = new Location(1, 1, "Source2");
  Location loc3 = new Location(2, 2, "Confluence");
  Location loc4 = new Location(3, 3, "Mouth");

  Source source1 = new Source(loc1, 0);
  Source source2 = new Source(loc2, 0);
  Source source3 = new Source(new Location(1, 0, "Source3"), 10);

  Confluence confluence = new Confluence(loc3, source1, source2, 5);
  Confluence confluence2 = new Confluence(new Location(2, 1, "Confluence2"), source3, confluence, 10);

  Mouth mouth = new Mouth(loc4, confluence);
  Mouth mouth2 = new Mouth(new Location(4, 4, "Mouth2"), confluence2);

  boolean testSources(Tester t) {
    return t.checkExpect(source1.sources(), 1) &&
           t.checkExpect(source2.sources(), 1) &&
           t.checkExpect(confluence.sources(), 2) &&
           t.checkExpect(mouth.sources(), 2);
  }

  boolean testOnRiver(Tester t) {
    return t.checkExpect(source1.onRiver(loc1), true) &&
           t.checkExpect(source1.onRiver(loc2), false) &&
           t.checkExpect(confluence.onRiver(loc3), true) &&
           t.checkExpect(confluence.onRiver(loc1), true) &&
           t.checkExpect(mouth.onRiver(loc4), true) &&
           t.checkExpect(mouth.onRiver(new Location(4, 4, "Not on River")), false);
  }

  boolean testLength(Tester t) {
    return t.checkExpect(source1.length(), 0) &&
           t.checkExpect(source2.length(), 0) &&
           t.checkExpect(confluence.length(), 5) &&
           t.checkExpect(mouth.length(), 5) &&
           t.checkExpect(mouth2.length(), 25);
  }

  boolean testMaxLength(Tester t) {
    return t.checkExpect(source1.maxLength(), 0) &&
           t.checkExpect(source2.maxLength(), 0) &&
           t.checkExpect(confluence.maxLength(), 5) &&
           t.checkExpect(mouth.maxLength(), 5) &&
           t.checkExpect(mouth2.maxLength(), 20);
  }

  boolean testConfluences(Tester t) {
    return t.checkExpect(source1.confluences(), 0) &&
           t.checkExpect(source2.confluences(), 0) &&
           t.checkExpect(confluence.confluences(), 1) &&
           t.checkExpect(mouth.confluences(), 1) &&
           t.checkExpect(mouth2.confluences(), 2);
  }

  boolean testLocations(Tester t) {
    ILoLoc expectedLocations1 = new ConsLoLoc(loc1, new Empty());
    ILoLoc expectedLocations2 = new ConsLoLoc(loc2, new Empty());
    ILoLoc expectedLocations3 = new ConsLoLoc(loc3, 
      new ConsLoLoc(loc1, new ConsLoLoc(loc2, new Empty())));
    ILoLoc expectedLocationsMouth = new ConsLoLoc(loc4, expectedLocations3);
    
    return t.checkExpect(source1.locations(), expectedLocations1) &&
      t.checkExpect(source2.locations(), expectedLocations2) &&
      t.checkExpect(confluence.locations(), expectedLocations3) &&
      t.checkExpect(mouth.locations(), expectedLocationsMouth);
  }
}

