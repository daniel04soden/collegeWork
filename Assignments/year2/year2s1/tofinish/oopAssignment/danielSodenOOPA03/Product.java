public abstract class Product extends Agent {
  public double productPrice;
  protected int currentStock;
  protected int amountOnOrder;

  public Product(int productID, double _productPrice, int _currentStock, int _amountOnOrder) {
    super(productID);
    this.productPrice = _productPrice;
    this.currentStock = _currentStock;
    this.amountOnOrder = _amountOnOrder;
  }

  public double getProductPrice() {
    return this.productPrice;
  }

  public void changeProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }

  public int getCurrentStock() {
    return this.currentStock;
  }

  public void setCurrentStock(int currentStock) {
    this.currentStock = currentStock;
  }

  public int getAmountOnOrder() {
    return this.amountOnOrder;
  }

  public void setAmountOnOrder(int amountOnOrder) {
    this.amountOnOrder = amountOnOrder;
  }

  // Extra functionality

  public void orderMore(int orderAmount) {
    this.amountOnOrder += orderAmount;
  }

  public void receiveMore() {
    this.currentStock += this.amountOnOrder;
    this.amountOnOrder = 0;
  }

  public abstract void sellItem();

}
