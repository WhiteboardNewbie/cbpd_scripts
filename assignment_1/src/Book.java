// Exercise 11.2, Page 113

import tester.*;

// represents info about a book
class Book {
  String title;
  int price;
  int yearOfPublication;
  Author author;

  // constructor
  Book(String title, int price, int yop, Author author) {
    this.title = title;
    this.price = price;
    this.yearOfPublication = yop;
    this.author = author;
  }

  // TEMPLATE
  // fields: this.title, this.price, this.yearOfPublication, this.author
  // methods: this.currentBook - boolean
  //          this.thisAuthor - boolean
  //          this.sameAuthor - bolean

  // checks whether this book appeared during a given year
  boolean currentBook(int year) {
    return this.yearOfPublication == year;
  }

  // checks whether this book is written by a given author
  boolean thisAuthor(Author auth) {
    return this.author.name.equals(auth.name) &&
           this.author.yearOfBirth == auth.yearOfBirth;
  }

  // checks whether this book and that book have the same author
  boolean sameAuthor(Book that) {
    return this.author.name.equals(that.author.name) &&
           this.author.yearOfBirth == that.author.yearOfBirth;
  }
}

// represents info about a book's author
class Author {
  String name;
  int yearOfBirth;

  // constructor
  Author(String name, int yearOfBirth) {
    this.name = name;
    this.yearOfBirth = yearOfBirth;
  }
}

class ExamplesBook {
  
  Author author1 = new Author("J.D. Salinger", 1919);
  Author author2 = new Author("Harper Lee", 1926);
  Author author3 = new Author("George Orwell", 1903);
  Book book1 = new Book("The Catcher in the Rye", 10, 1951, author1);
  Book book2 = new Book("To Kill a Mockingbird", 15, 1960, author2);
  Book book3 = new Book("1984", 20, 1949, author3);

  boolean testCurrentBook(Tester t) {
    return t.checkExpect(book1.currentBook(1951), true) &&
           t.checkExpect(book2.currentBook(1960), true) &&
           t.checkExpect(book3.currentBook(1949), true) &&
           t.checkExpect(book1.currentBook(2000), false);
  }

  boolean testThisAuthor(Tester t) {
    return t.checkExpect(book1.thisAuthor(author1), true) &&
           t.checkExpect(book2.thisAuthor(author2), true) &&
           t.checkExpect(book3.thisAuthor(author3), true) &&
           t.checkExpect(book1.thisAuthor(author2), false);
  }

  boolean testSameAuthor(Tester t) {
    return t.checkExpect(book1.sameAuthor(book2), false) &&
           t.checkExpect(book1.sameAuthor(new Book("New Book", 10, 1951, author1)), true) &&
           t.checkExpect(book2.sameAuthor(new Book("Another Book", 15, 1960, author2)), true) &&
           t.checkExpect(book3.sameAuthor(new Book("Different Book", 20, 1949, author3)), true);
  }
}