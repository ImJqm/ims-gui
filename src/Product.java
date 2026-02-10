public class Product {

  // instance variables for a product
  private String name;
  private String productID;
  private double price;
  private int quantity;

  // product constructor that sets instance variables equal to the given
  // parametersl
  public Product(String name, String productID, double price, int quantity) {
    this.name = name;
    this.productID = productID;
    this.price = price;
    this.quantity = quantity;
  }

  // returns product's name
  public String getName() {
    return this.name;
  }

  // sets the name of the product
  public void setName(String name) {
    this.name = name;
  }

  // gets the product id
  public String getProductID() {
    return this.productID;
  }

  // sets the productID
  public void setProductID(String productID) {
    this.productID = productID;
  }

  // returns the price
  public double getPrice() {
    return this.price;
  }

  // sets the price
  public void setPrice(double price) {
    this.price = price;
  }

  // returns the quantity
  public int getQuantity() {
    return this.quantity;
  }

  // sets the quantity
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  // makes a product printable
  public String toString() {
    return ("ID: " + this.productID + " Name: " + this.name + " " + this.quantity + "x" + "$" + this.price);
  }
}
