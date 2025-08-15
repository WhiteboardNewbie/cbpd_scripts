// Exercise 4.4, Page 34

// represents a bank account
interface Account {

}

// represents a checking account
class Checking implements Account {
  int id;
  String name;
  int minimumBalance;

  // constructor
  Checking(int id, String name, int minimumBalance) {
    this.id = id;
    this.name = name;
    this.minimumBalance = minimumBalance;
  }
}

// represents a savings account
class Savings implements Account {
  int id;
  String name;
  double interestRate;

  // constructor
  Savings(int id, String name, double interestRate) {
    this.id = id;
    this.name = name;
    this.interestRate = interestRate;
  }
}

// represents a certificate of deposit account (CD)
class CD implements Account {
  int id;
  String name;
  double interestRate;
  Date maturityDate;

  // constructor
  CD(int id, String name, double interestRate, Date maturityDate) {
    this.id = id;
    this.name = name;
    this.interestRate = interestRate;
    this.maturityDate = maturityDate;
  }
}

class Date {
  int day;
  int month;
  int year;

  // constructor
  Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }
}

class ExamplesAccount {
  Account checkingAccount = new Checking(1, "John Doe", 500);
  Account savingsAccount = new Savings(2, "Jane Doe", 0.02);
  Account cdAccount = new CD(3, "Jim Doe", 0.03, new Date(15, 6, 2025));
}