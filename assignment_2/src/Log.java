// Exercise 15.1, 15.2, 15.3, Page 149


import tester.*;

interface Ilog {
  // computes the total number of miles run
  double miles();

  // compiles the log for a given month, in a given year
  Ilog oneMonth(int month, int year);
  
  // computes the total number of miles run in a given month and year
  double milesInMonth(int month, int year);

  // computes the length of the longest run
  int longestRun();
}

class MTLog implements Ilog {

  public double miles() {
    return 0.0;
  }

  public Ilog oneMonth(int month, int year) {
    return this;
  }

  public double milesInMonth(int month, int year) {
    return 0.0;
  }

  public int longestRun() {
    return 0;
  }
}

class ConsLog implements Ilog {
  Entry first;
  Ilog rest;

  ConsLog(Entry first, Ilog rest) {
    this.first = first;
    this.rest = rest;
  }

  public double miles() {
    /* Template
     * Fields:
     * - this.first: Entry
     * - this.rest: Ilog
     * Methods:
     * - miles(): double
     * Methods on fields:
     * - this.first.addDistance(double d): double
     * - this.rest.miles(): double
     */

     return this.first.addDistance(this.rest.miles());
  }

  public Ilog oneMonth(int month, int year) {
    /* Template
     * Fields:
     * - this.first: Entry
     * - this.rest: Ilog
     * Parameters:
     * - month: int
     * - year: int
     * Methods on Fields:
     * - this.first.sameMonthAndYear(int month, int year): boolean
     * - this.rest.oneMonth(int month, int year): Ilog
     */

     return this.first.sameMonthAndYear(month, year)
      ? new ConsLog(this.first, this.rest.oneMonth(month, year))
      : this.rest.oneMonth(month, year);
  }

  public double milesInMonth(int month, int year) {
    /* Template
     * Fields:
     * - this.first: Entry
     * - this.rest: Ilog
     * Methods:
     * - miles(): double
     * - OneMonth(int month, int year): Ilog
     * - milesInMonth(int month, int year): double
     * Parameters:
     * - month: int
     * - year: int
     */

     return this.oneMonth(month, year).miles();
  }

  public int longestRun() {
    /* Template
     * Fields:
     * - this.first: Entry
     * - this.rest: Ilog
     * Metods:
     * - longestRun(): int
     * Methods on Fields:
     * - this.first.longerRun(int acc): int
     * - this.rest.longestRun(): int
     * - Parameters:
     * - acc: int (the current length of the longest run)
     */

     return this.first.longerRun(this.rest.longestRun());
  }
}

class Entry {
  Date d;
  double distance; // miles
  int duration; // minutes
  String comment;

  Entry(Date d, double distance, int duration, String comment) {
    this.d = d;
    this.distance = distance;
    this.duration = duration;
    this.comment = comment;
  }

  double addDistance(double d) {
    /* Template
     * Fields:
     * - this.distance: double
     * Parameters:
     * - d: double
     */
    return this.distance + d;
  }

  boolean sameMonthAndYear(int month, int year) {
    /* Template
     * Fields:
     * - this.d: Date
     * Parameters:
     * - month: int
     * - year: int
     * Methods on Fields:
     * - this.d.sameMonthAndYear(int month, int year): boolean
     */ 

     return this.d.sameMonthAndYear(month, year);
  }

  int longerRun(int duration) {
    return Math.max(duration, this.duration);
  }
}

class Date {
  int day;
  int month;
  int year;

  Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  boolean sameMonthAndYear(int month, int year) {
    /* Template
     * Fields:
     * - this.month: int
     * - this.year: int
     * Parameters:
     * - month: int
     * - year: int
     */

     return this.month == month && this.year == year;
  }
}

class ExamplesLog {
  Ilog empty = new MTLog();
  Ilog log1 = new ConsLog(
    new Entry(new Date(1, 1, 2023), 5.0, 30, "Morning run"),
    new ConsLog(
      new Entry(new Date(15, 1, 2023), 3.0, 20, "Evening run"),
      new MTLog()
    )
  );
  Ilog log2 = new ConsLog(
    new Entry(new Date(1, 2, 2023), 4.0, 25, "Morning run"),
    new ConsLog(
      new Entry(new Date(15, 2, 2023), 6.0, 35, "Evening run"),
      new MTLog()
    )
  );

  boolean testMiles(Tester t) {
    return t.checkExpect(this.empty.miles(), 0.0) &&
           t.checkExpect(this.log1.miles(), 8.0) &&
           t.checkExpect(this.log2.miles(), 10.0);
  }

  boolean testOneMonth(Tester t) {
    return t.checkExpect(this.empty.oneMonth(1, 2023), this.empty) &&
           t.checkExpect(this.log1.oneMonth(1, 2023), this.log1) &&
           t.checkExpect(this.log1.oneMonth(2, 2023), this.empty) &&
           t.checkExpect(this.log2.oneMonth(2, 2023), this.log2) &&
           t.checkExpect(this.log2.oneMonth(1, 2023), this.empty);
  }

  boolean testMilesInMonth(Tester t) {
    return t.checkExpect(this.empty.milesInMonth(1, 2023), 0.0) &&
           t.checkExpect(this.log1.milesInMonth(1, 2023), 8.0) &&
           t.checkExpect(this.log1.milesInMonth(2, 2023), 0.0) &&
           t.checkExpect(this.log2.milesInMonth(2, 2023), 10.0) &&
           t.checkExpect(this.log2.milesInMonth(1, 2023), 0.0);
  }

  boolean testLongestRun(Tester t) {
    return t.checkExpect(this.empty.longestRun(), 0) &&
           t.checkExpect(this.log1.longestRun(), 30) &&
           t.checkExpect(this.log2.longestRun(), 35) &&
           t.checkExpect(new ConsLog(
             new Entry(new Date(1, 1, 2023), 5.0, 40, "Long run"),
             this.log1
           ).longestRun(), 40);
  }
}