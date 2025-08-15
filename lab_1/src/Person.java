class Person {
  String name;
  int age;
  String gender;
  Address address;

  Person(String name, int age, String gender, Address address) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.address = address;
  }
}

class ExamplesPersons {
  Address timAddress = new Address("Boston", "MA");
  Address kateAddress = new Address("Warwick", "RI");
  Address rebeccaAddress = new Address("Nashua", "NH");
  Person tim = new Person("Tim", 23, "Male", timAddress);
  Person kate = new Person("Kate", 22, "Female", kateAddress);
  Person rebecca = new Person("Rebecca", 31, "Non-binary", rebeccaAddress);
}

class Address {
  String city;
  String state;

  Address(String city, String state) {
    this.city = city;
    this.state = state;
  }
}