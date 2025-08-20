// Exercise 10.6, Page 102

import tester.*;
// represent information about an image
class Image {
  int width; // in pixels
  int height; // in pixels
  String source;

  Image(int width, int height, String source) {
    this.width = width;
    this.height = height;
    this.source = source;
  }
  
  // TEMPLATE
  // fields: this.width, this.height, this.source
  // methods: this.size(), this.sizeString()

  int size() {
    return this.width * this.height;
  }

  String sizeString() {
    if (this.size() < 10000) {
      return "small";
    } else if (10000 <= this.size() && this.size() < 1000000) {
      return "medium";
    } else {
      return "large";
    }
  }
}

class ExamplesImage {
  Image img1 = new Image(100, 90, "photo1.jpg");
  Image img2 = new Image(1920, 50, "photo2.jpg");
  Image img3 = new Image(3840, 2160, "photo3.jpg");

  boolean testSize(Tester t) {
    return t.checkExpect(img1.size(), 9000) &&
           t.checkExpect(img2.size(), 96000) &&
           t.checkExpect(img3.size(), 8294400);
  }

  boolean testSizeString(Tester t) {
    return t.checkExpect(img1.sizeString(), "small") &&
           t.checkExpect(img2.sizeString(), "medium") &&
           t.checkExpect(img3.sizeString(), "large");
  }
}


