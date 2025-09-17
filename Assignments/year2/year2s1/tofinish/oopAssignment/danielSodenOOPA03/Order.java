import java.util.Arrays;

public abstract class Order extends Agent {
  public double orderTotal;
  public String[] orderProducts;
  public double[] pricePerProduct;
  private String customerID;

  public Order(int orderID, double[] _pricePerProduct, String[] _orderProducts) {
    super(orderID);
    this.pricePerProduct = _pricePerProduct;
    this.orderProducts = _orderProducts;
    this.orderTotal = Arrays.stream(_pricePerProduct).sum();
  }

  public double getOrderTotal() {
    return this.orderTotal;
  }

  public void setOrderTotal(double orderTotal) {
    this.orderTotal = orderTotal;
  }

  public String[] getOrderProducts() {
    return this.orderProducts;
  }

  public void setOrderProducts(String[] orderProducts) {
    this.orderProducts = orderProducts;
  }

  public double[] getPricePerProduct() {
    return this.pricePerProduct;
  }

  public void setPricePerProduct(double[] pricePerProduct) {
    this.pricePerProduct = pricePerProduct;
  }

  public String getCustomerID() {
    return this.customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }

  // Extra functionality
  public abstract Order placeOrder();

  public abstract void cancelOrder();
}
