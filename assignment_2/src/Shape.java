// Exercise 12.1, Page 125
import tester.*;

interface Ishape {
  // to compute the area of a this shape
  double area();
  // to compute the distance of this shape to the origin
  double distanceToOrigin();
  // is the given point within the bounds of this shape?
  boolean in(CartPt pt);
  // compute the bounding box for this shape
  Square boundingBox();
}

class dot implements Ishape {
  CartPt loc;

  dot(CartPt loc) {
    this.loc = loc;
  }

  public double area() {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return 0.0;
  }

  public double distanceToOrigin() {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return this.loc.distanceToOrigin();
  }

  public boolean in(CartPt pt) {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     *   pt -- CartPt
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     *   pt.distanceToOrigin() -- double
     *   pt.same(CartPt) -- boolean
     *   pt.distanceTo(CartPt) -- double
     */
    return this.loc.same(pt);
  }

  public Square boundingBox() {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return new Square(this.loc, 1);
  }
}

class Square implements Ishape {
  CartPt loc;
  int size;

  Square(CartPt loc, int size) {
    this.loc = loc;
    this.size = size;
  }

  public double area() {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     *   this.size -- int
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return this.size * this.size;
  }

  public double distanceToOrigin() {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     *   this.size -- int
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return this.loc.distanceToOrigin();
  }

  boolean between(int a, int b, int da) {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     *   this.size -- int
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     *   this.between(int,int,int) -- boolean
     * Parameters:
     *   a -- int
     *   b -- int
     *   da -- int
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return (a <= b) && (b <= a + da);
  }

  public boolean in(CartPt pt) {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     *   this.size -- int
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     *   this.between(int,int,int) -- boolean
     * Parameters:
     *   pt -- CartPt
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     *   pt.distanceToOrigin() -- double
     *   pt.same(CartPt) -- boolean
     *   pt.distanceTo(CartPt) -- double
     */
    return between(this.loc.x, pt.x, this.size) &&
           between(this.loc.y, pt.y, this.size);
  }

  public Square boundingBox() {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     *   this.size -- int
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return this;
  }
}

class Circle implements Ishape {
  CartPt loc;
  int radius;

  Circle(CartPt loc, int radius) {
    this.loc = loc;
    this.radius = radius;
  }

  public double area() {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     *   this.radius -- int
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return Math.PI * this.radius * this.radius;
  }

  public double distanceToOrigin() {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     *   this.radius -- int
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return this.loc.distanceToOrigin() - this.radius;
  }

  public boolean in(CartPt pt) {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     *   this.radius -- int
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     *   pt -- CartPt
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     *   pt.distanceToOrigin() -- double
     *   pt.same(CartPt) -- boolean
     *   pt.distanceTo(CartPt) -- double
     */
    return this.loc.distanceTo(pt) <= this.radius;
  }

  public Square boundingBox() {
    /* Template:
     * Fields:
     *   this.loc -- CartPt
     *   this.radius -- int
     * Methods on this:
     *   this.area() -- double
     *   this.distanceToOrigin() -- double
     *   this.in(CartPt) -- boolean
     *   this.boundingBox() -- Square
     * Parameters:
     * Methods on fields:
     *   this.loc.distanceToOrigin() -- double
     *   this.loc.same(CartPt) -- boolean
     *   this.loc.distanceTo(CartPt) -- double
     * Methods on parameters:
     */
    return new Square(this.loc, 2 * this.radius);
  }
}

class CartPt {
  int x;
  int y;

  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  double distanceToOrigin() {
    /* Template:
     * Fields:
     *   this.x -- int
     *   this.y -- int
     * Methods on this:
     *   this.distanceToOrigin() -- double
     *   this.same(CartPt) -- boolean
     *   this.distanceTo(CartPt) -- double
     * Parameters:
     * Methods on fields:
     * Methods on parameters:
     */
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }

  boolean same(CartPt that) {
    /* Template:
     * Fields:
     *   this.x -- int
     *   this.y -- int
     * Methods on this:
     *   this.distanceToOrigin() -- double
     *   this.same(CartPt) -- boolean
     *   this.distanceTo(CartPt) -- double
     * Parameters:
     *   that -- CartPt
     * Methods on fields:
     * Methods on parameters:
     *   that.distanceToOrigin() -- double
     *   that.same(CartPt) -- boolean
     *   that.distanceTo(CartPt) -- double
     */
    return this.x == that.x && this.y == that.y;
  }

  double distanceTo(CartPt that) {
    /* Template:
     * Fields:
     *   this.x -- int
     *   this.y -- int
     * Methods on this:
     *   this.distanceToOrigin() -- double
     *   this.same(CartPt) -- boolean
     *   this.distanceTo(CartPt) -- double
     * Parameters:
     *   that -- CartPt
     * Methods on fields:
     * Methods on parameters:
     *   that.distanceToOrigin() -- double
     *   that.same(CartPt) -- boolean
     *   that.distanceTo(CartPt) -- double
     */
    return Math.sqrt(Math.pow(this.x - that.x, 2) +
                     Math.pow(this.y - that.y, 2));
  }
}

class ExamplesShape {
  Ishape dot1 = new dot(new CartPt(0, 0));
  Ishape square1 = new Square(new CartPt(1, 1), 4);
  Ishape circle1 = new Circle(new CartPt(2, 2), 3);

  boolean testArea(Tester t) {
    return t.checkExpect(dot1.area(), 0.0) &&
           t.checkExpect(square1.area(), 16.0) &&
           t.checkExpect(circle1.area(), Math.PI * 9);
  }

  boolean testDistanceToOrigin(Tester t) {
    return t.checkExpect(dot1.distanceToOrigin(), 0.0) &&
           t.checkExpect(square1.distanceToOrigin(), Math.sqrt(2)) &&
           t.checkExpect(circle1.distanceToOrigin(), Math.sqrt(8) - 3);
  }

  boolean testIn(Tester t) {
    return t.checkExpect(dot1.in(new CartPt(0, 0)), true) &&
           t.checkExpect(square1.in(new CartPt(2, 2)), true) &&
           t.checkExpect(circle1.in(new CartPt(3, 3)), true) &&
           t.checkExpect(circle1.in(new CartPt(6, 6)), false);
  }

  boolean testBoundingBox(Tester t) {
    return t.checkExpect(dot1.boundingBox(), new Square(new CartPt(0, 0), 1)) &&
           t.checkExpect(square1.boundingBox(), square1) &&
           t.checkExpect(circle1.boundingBox(), new Square(new CartPt(2, 2), 6));
  }

}
