// Problem 2

import tester.*;

// represents a picture
interface IPicture {
  
  // produces the width of this picture
  double getWidth();

  // measures the nesting depth of operations in this picture
  int comboDepth();

  // leaves this image unchanged but flips Beside Combos
  IPicture mirror();

  // produces a string representing the contents of this picture where the recipe has been expanded only depth times
  String pictureRecipe(int depth); 
}


// represents a simple shape picture
class Shape implements IPicture {
  String kind;
  double size;

  Shape(String kind, double size) {
    this.kind = kind;
    this.size = size;
  }

  /* Template
   * ??? nnn() {
   *  ... this.kind ...
   *  ... this.size ...
   * }
   */

   public double getWidth() {
    return this.size;
   }

   public int comboDepth() {
    return 0;
   }

   public IPicture mirror() {
    return this;
   }

   public String pictureRecipe(int depth) {
    return this.kind;
   }
}


// represents a combination of pictures
class Combo implements IPicture {
  String name;
  IOperation operation;

  Combo(String name, IOperation operation) {
    this.name = name;
    this.operation = operation;
  }

  /* Template
   * ??? nnn() {
   *  ... this.name ...
   * ... this.operation.mmm() ...
   * }
   */

   public double getWidth() {
    return this.operation.getResultWidth();
   }

   public int comboDepth() {
    return this.operation.getNestingDepth();
   }

   public IPicture mirror() {
    return new Combo(this.name, this.operation.mirrorOperation());
   }

   public String pictureRecipe(int depth) {
    if (depth <= 0) {
      return this.name;
    } else {
      return this.operation.operationRecipe(depth);
    }
   }
}

// represents a picture Operation
interface IOperation {

  // calculates the width of the resulting image of this operation
  double getResultWidth();

  // calculates the nesting depth of this operation
  int getNestingDepth();

  // mirrors the operation
  IOperation mirrorOperation();

  // returns the formula of this operation to depth
  String operationRecipe(int depth);
}

// represents a scale operation
class Scale implements IOperation {
  IPicture picture;

  Scale(IPicture picture) {
    this.picture = picture;
  }

  /* Template
   * ??? nnn() {
   *  ... this.picture.mmm() ...
   * }
   */

   public double getResultWidth() {
    return this.picture.getWidth() * 2;
   }

   public int getNestingDepth() {
    return 1 + this.picture.comboDepth();
   }

   public IOperation mirrorOperation() {
    return new Scale(this.picture.mirror());
   }

   public String operationRecipe(int depth) {
      return "scale(" + this.picture.pictureRecipe(depth - 1) + ")";
   }
}

class Beside implements IOperation {
  IPicture pictureOne;
  IPicture pictureTwo;

  Beside(IPicture pictureOne, IPicture pictureTwo) {
    this.pictureOne = pictureOne;
    this.pictureTwo = pictureTwo;
  }

  /* Template
   * ??? nnn() {
   *  ... this.pictureOne.mmm()
   *  ... this.pictureTwo.mmm()
   * }
   */

  public double getResultWidth() {
    return this.pictureOne.getWidth() + this.pictureTwo.getWidth();
  }

  public int getNestingDepth() {
    return 1 + Math.max(this.pictureOne.comboDepth(), this.pictureTwo.comboDepth());
  }

  public IOperation mirrorOperation() {
    return new Beside(this.pictureTwo.mirror(), this.pictureOne.mirror());
  }

  public String operationRecipe(int depth) {
    return "beside(" 
      + this.pictureOne.pictureRecipe(depth - 1) + ", " 
      + this.pictureTwo.pictureRecipe(depth - 1) + ")";
  }
}

class Overlay implements IOperation {
  IPicture topPicture;
  IPicture bottomPicture;

  Overlay(IPicture topPicture, IPicture bottomPicture) {
    this.topPicture = topPicture;
    this.bottomPicture = bottomPicture;
  }

  /* Template
   * ??? nnn() {
   *  ... this.topPicture.mmm() ...
   *  ... this.bottomPicture.mmm() ...
   * }
   */

  public double getResultWidth() {
    double topPictureWidth = this.topPicture.getWidth();
    double bottomPictureWidth = this.bottomPicture.getWidth();
    if (topPictureWidth >= bottomPictureWidth) {
      return topPictureWidth;
    }
    return bottomPictureWidth;
  }

  public int getNestingDepth() {
    return 1 + this.topPicture.comboDepth() + this.bottomPicture.comboDepth();
  }

  public IOperation mirrorOperation() {
    return new Overlay(this.topPicture.mirror(), this.bottomPicture.mirror());
  }

  public String operationRecipe(int depth) {
    return "overlay(" 
      + this.topPicture.pictureRecipe(depth - 1) + ", " 
      + this.bottomPicture.pictureRecipe(depth - 1) + ")";
  }
}

class ExamplesPicture {
  IPicture circle = new Shape("circle", 20);
  IPicture square = new Shape("square", 30);
  IOperation scaleCircle = new Scale(circle);
  IPicture bigCircle = new Combo("bigCircle", scaleCircle);
  IOperation overlaySquareOnCircle = new Overlay(square, circle);
  IPicture squareOnCircle = new Combo("squareOnCircle", overlaySquareOnCircle);
  IOperation besideSquareOnCircle = new Beside(squareOnCircle, squareOnCircle);
  IPicture doubledSquareOnCircle = new Combo("doubledSquareOnCircle", besideSquareOnCircle);
  IOperation scaleSquare = new Scale(square);
  IPicture bigSquare = new Combo("bigSquare", scaleSquare);
  IOperation overlayCircleOnBigCircle = new Overlay(circle, bigCircle);
  IPicture circleOnBigCircle = new Combo("circleOnBigCircle", overlayCircleOnBigCircle);
  IOperation besideCircleAndSquare = new Beside(circle, square);
  IPicture circleBesideSquare = new Combo("circleBesideSquare", besideCircleAndSquare);
  IPicture smallCircle = new Shape("smallCircle", 10);
  IPicture smallSquare = new Shape("smallSquare", 15);
  IOperation overlayCircleOnSquare = new Overlay(smallCircle, smallSquare);
  IPicture circleOnSquare = new Combo("circleOnSquare", overlayCircleOnSquare);
  IOperation besideCircleOnSquareAndCircle = new Beside(circleOnSquare, smallCircle);
  IPicture complexPicture = new Combo("complexPicture", besideCircleOnSquareAndCircle);
  IOperation scaleComplexPicture = new Scale(complexPicture);
  IPicture threeLevelPicture = new Combo("threeLevelPicture", scaleComplexPicture);

  // tests for getWidth
  boolean testGetWidth(Tester t) {
    return
      t.checkExpect(this.circle.getWidth(), 20.0) &&
      t.checkExpect(this.square.getWidth(), 30.0) &&
      t.checkExpect(this.bigCircle.getWidth(), 40.0) && // 2 * 20
      t.checkExpect(this.squareOnCircle.getWidth(), 30.0) && // max(30,20)
      t.checkExpect(this.doubledSquareOnCircle.getWidth(), 60.0) && // 30 + 30
      t.checkExpect(this.bigSquare.getWidth(), 60.0) && // 2 * 30
      t.checkExpect(this.circleOnBigCircle.getWidth(), 40.0) && // max(20, 40)
      t.checkExpect(this.circleBesideSquare.getWidth(), 50.0); // 20 + 30
  }

  // tests for comboDepth
  boolean testComboDepth(Tester t) {
    return
      t.checkExpect(this.circle.comboDepth(), 0) &&
      t.checkExpect(this.square.comboDepth(), 0) &&
      t.checkExpect(this.bigCircle.comboDepth(), 1) &&
      t.checkExpect(this.bigSquare.comboDepth(), 1) &&
      t.checkExpect(this.squareOnCircle.comboDepth(), 1) &&
      t.checkExpect(this.circleOnBigCircle.comboDepth(), 2) &&
      t.checkExpect(this.doubledSquareOnCircle.comboDepth(), 2) &&
      t.checkExpect(this.circleBesideSquare.comboDepth(), 1) &&
      t.checkExpect(this.threeLevelPicture.comboDepth(), 3) &&
      t.checkExpect(this.threeLevelPicture.getWidth(), this.complexPicture.getWidth() * 2);
   }

  // tests for mirror
  boolean testMirror(Tester t) {
    return
      t.checkExpect(this.circle.mirror(), this.circle) &&

      t.checkExpect(
        this.circleBesideSquare.mirror(),
        new Combo("circleBesideSquare", new Beside(this.square, this.circle))) &&

      t.checkExpect(
        this.doubledSquareOnCircle.mirror(),
        new Combo("doubledSquareOnCircle", new Beside(this.squareOnCircle, this.squareOnCircle))) &&

      t.checkExpect(
        this.bigCircle.mirror(),
        new Combo("bigCircle", new Scale(this.circle.mirror()))) &&

      t.checkExpect(
        this.circleOnSquare.mirror(),
        new Combo("circleOnSquare", new Overlay(this.smallCircle.mirror(), this.smallSquare.mirror())));
  }

  boolean testPictureRecipe(Tester t) {
    return
      t.checkExpect(this.circle.pictureRecipe(0), "circle") &&
      t.checkExpect(this.square.pictureRecipe(0), "square") &&

      t.checkExpect(this.bigCircle.pictureRecipe(0), "bigCircle") &&
      t.checkExpect(this.bigCircle.pictureRecipe(1), "scale(circle)") &&

      t.checkExpect(this.squareOnCircle.pictureRecipe(1), "overlay(square, circle)") &&
      t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(1), "beside(squareOnCircle, squareOnCircle)") &&
      t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(2), "beside(overlay(square, circle), overlay(square, circle))") &&

      t.checkExpect(this.threeLevelPicture.pictureRecipe(0), "threeLevelPicture") &&
      t.checkExpect(this.threeLevelPicture.pictureRecipe(1), "scale(complexPicture)") &&
      t.checkExpect(this.threeLevelPicture.pictureRecipe(2), "scale(beside(circleOnSquare, smallCircle))") &&
      t.checkExpect(this.threeLevelPicture.pictureRecipe(3), "scale(beside(overlay(smallCircle, smallSquare), smallCircle))");
  }
}