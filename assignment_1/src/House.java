// Exercise 3.1, Page 25
// Exercise 5.3, Page 43

// represents a house
public class House {
  String kind;
  int rooms;
  Address address;
  int askingPrice;

  // constructor
  House(String kind, int rooms, Address address, int askingPrice) {
    this.kind = kind;
    this.rooms = rooms;
    this.address = address;
    this.askingPrice = askingPrice;
  }
}

class Address {
  int streetNumber;
  String streetName;
  String city;

  // constructor
  Address(int streetNumber, String streetName, String city) {
    this.streetNumber = streetNumber;
    this.streetName = streetName;
    this.city = city;
  }
}

class ExamplesHouse {
  House house1 = new House("Single Family", 4, new Address(123, "Main St", "Springfield"), 250000);
  House house2 = new House("Condo", 2, new Address(456, "Elm St", "Shelbyville"), 180000);
  House house3 = new House("Townhouse", 3, new Address(789, "Oak St", "Capital City"), 220000);
}

interface HouseListings {}

class EmptyHouse implements HouseListings {}

class ConsHouse implements HouseListings {
  House first;
  HouseListings rest;

  // constructor
  ConsHouse(House first, HouseListings rest) {
    this.first = first;
    this.rest = rest;
  }
}

class ExamplesHouseListings {
  HouseListings listings = new ConsHouse(
    new House("Single Family", 4, new Address(123, "Main St", "Springfield"), 250000),
    new ConsHouse(
      new House("Condo", 2, new Address(456, "Elm St", "Shelbyville"), 180000),
      new EmptyHouse()
    )
  );
}