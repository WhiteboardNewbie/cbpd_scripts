// Problem 1

import tester.*;

// represents a piece of embroidery
class EmbroideryPiece {
  String name;
  IMotif motif;

  EmbroideryPiece(String name, IMotif motif) {
    this.name = name;
    this.motif = motif;
  }

  // Template
  /*
   * ??? nnn() {
   *  ... this.name ...
   *  ... this.motif.nnn() ...}
   */

  // calculates the average difficulty of all motifs on this piece
  double averageDifficulty() {
    return this.motif.averageDifficulty();
  }
  
  // produces the names of all cross and chain stitch motifs on this piece
  String allNames() {
    String motifNames = this.motif.allNames();
    return this.name + " " + motifNames.substring(0, motifNames.length() -2) + ".";
  }
}

// represents a type of motif
interface IMotif {

  // calculates the average difficulty of this motif
  double averageDifficulty();

  // counts the number of motifs in this motif
  double count();

  // calculates the total difficulty of this motif
  double totalDifficulty();

  // produces the name or if group the names of all motifs in this motif
  String allNames();
}

// represents a cross-stitch motif
class CrossStitch implements IMotif {
  String description;
  double difficulty; // from 0.0 to 5.0

  CrossStitch(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  /* Template
   * ??? nnn() {
   *  ... this.description ...
   *  ... this.difficulty ...
   *  }
   */

  public double averageDifficulty() {
    return this.difficulty;
  }

  public double totalDifficulty() {
    return this.difficulty;
  }

  public double count() {
    return 1;
  }

  public String allNames() {
    return this.description + " (cross stitch), ";
  }
}

// represents a chainStitch motif
class ChainStitch implements IMotif {
  String description;
  double difficulty; // from 0.0 to 5.0

  ChainStitch(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  /* Template
   * ??? nnn() {
   *  ... this.description ...
   *  ... this.difficulty ...
   *  }
   */

  public double averageDifficulty() {
    return this.difficulty;
  }


  public double totalDifficulty() {
    return this.difficulty;
  }

  public double count() {
    return 1;
  }

  public String allNames() {
    return this.description + " (chain stitch), ";
  }
}

// represents a group of motifs
class GroupMotif implements IMotif {
  String description;
  ILoMotif motifs;

  GroupMotif(String description, ILoMotif motifs) {
    this.description = description;
    this.motifs = motifs;
  }

  /* Template
   * ??? nnn() {
   *  this.description ...
   *  ... this.motifs.nnn() ...
   *  }
   */

  public double averageDifficulty() {
    return this.totalDifficulty() / this.count();
  }

  public double totalDifficulty() {
    return this.motifs.totalDifficulty();
  }

  public double count() {
    return this.motifs.count();
  }

  public String allNames() {
    return this.motifs.allNames();
  }
}

// represents a list of motifs
interface ILoMotif {

  // calculates the total difficulty of this list
  double totalDifficulty();

  // calculates the number of cross-stitch and chain-stitch motifs of this list;
  double count();

  // finds the name of all motifs in this list;
  String allNames();
}

// an empty list of motifs
class EmptyLoMotif implements ILoMotif {

  public double totalDifficulty() {
    return 0.0;
  }

  public double count() {
    return 0.0;
  }

  public String allNames() {
    return "";
  }
}

// a non-empty list of motifs
class ConsLoMotif implements ILoMotif {
  IMotif first;
  ILoMotif rest;

  ConsLoMotif(IMotif first, ILoMotif rest) {
    this.first = first;
    this.rest = rest;
  }

  /* Template
   * ??? nnn() {
   *  ... this.first.nnn() ...
   *  ... this.rest.nnn() ...}
   */

  public double totalDifficulty() {
    return this.first.totalDifficulty() + this.rest.totalDifficulty();
  }
  
  public double count() {
    return this.first.count() + this.rest.count();
  }

  public String allNames() {
    return this.first.allNames() + this.rest.allNames();
  }
}

class ExamplesEmbroidery {

  IMotif rose = new CrossStitch("rose", 5.0);
  IMotif poppy = new ChainStitch("poppy", 4.75);
  IMotif daisy = new CrossStitch("daisy", 3.2);
  ILoMotif flowersMotifs = new ConsLoMotif(this.rose,
                                new ConsLoMotif(this.poppy,
                                    new ConsLoMotif(this.daisy,
                                        new EmptyLoMotif())));
  IMotif flowersGroup = new GroupMotif("flowers", this.flowersMotifs);

  IMotif bird = new CrossStitch("bird", 4.5);
  IMotif tree = new ChainStitch("tree", 3.0);
  ILoMotif natureMotifs = new ConsLoMotif(this.bird,
                                new ConsLoMotif(this.tree,
                                    new ConsLoMotif(this.flowersGroup,
                                        new EmptyLoMotif())));
  IMotif natureGroup = new GroupMotif("nature", this.natureMotifs);

  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover", this.natureGroup);

  boolean testILoMotifCount(Tester t) {
    return
      t.checkExpect(this.flowersMotifs.count(), 3.0) &&
      t.checkExpect(this.natureMotifs.count(), 5.0) &&
      t.checkExpect(new EmptyLoMotif().count(), 0.0) &&
      t.checkExpect(new ConsLoMotif(this.rose, new EmptyLoMotif()).count(), 1.0);
  }

  boolean testILoMotifTotalDifficulty(Tester t) {
    return
      t.checkInexact(this.flowersMotifs.totalDifficulty(), 5.0 + 4.75 + 3.2, 0.001) &&
      t.checkInexact(this.natureMotifs.totalDifficulty(), 4.5 + 3.0 + 5.0 + 4.75 + 3.2, 0.001) &&
      t.checkExpect(new EmptyLoMotif().totalDifficulty(), 0.0);
  }

  boolean testIMotifAverageDifficulty(Tester t) {
    return
      t.checkExpect(this.rose.averageDifficulty(), 5.0) &&
      t.checkExpect(this.poppy.averageDifficulty(), 4.75) &&
      t.checkExpect(this.flowersGroup.averageDifficulty(), (5.0 + 4.75 + 3.2)/3.0) &&
      t.checkExpect(this.natureGroup.averageDifficulty(), (4.5 + 3.0 + 5.0 + 4.75 + 3.2)/5.0);
  }

  boolean testEmbroideryPiece(Tester t) {
    return
      t.checkExpect(this.pillowCover.averageDifficulty(),
                     (4.5 + 3.0 + 5.0 + 4.75 + 3.2)/5.0);
  }

  boolean testAllNames(Tester t) {
    return
      t.checkExpect(this.rose.allNames(), "rose (cross stitch), ") &&
      t.checkExpect(this.poppy.allNames(), "poppy (chain stitch), ") &&
      t.checkExpect(this.flowersGroup.allNames(),
                    "rose (cross stitch), poppy (chain stitch), daisy (cross stitch), ") &&
      t.checkExpect(this.natureGroup.allNames(),
                    "bird (cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch), daisy (cross stitch), ") &&
      t.checkExpect(this.pillowCover.allNames(),
                    "Pillow Cover bird (cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch), daisy (cross stitch).");
  }
}