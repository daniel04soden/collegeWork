
public class Computer extends Product {
  protected String processor;
  protected int baseStorage;
  protected int baseRam;

  public Computer(String _processor, int _baseStorage, int _baseRam, int _productID,
      double _price, int _stock, int _amountOnOrder) {
    super(_productID, _price, _stock, _amountOnOrder);
    this.processor = _processor;
    this.baseStorage = _baseStorage;
    this.baseRam = _baseRam;
  }

  public String getProcessor() {
    return processor;
  }

  public void setProcessor(String processor) {
    this.processor = processor;
  }

  public int getBaseStorage() {
    return baseStorage;
  }

  public void setBaseStorage(int baseStorage) {
    this.baseStorage = baseStorage;
  }

  public int getBaseRam() {
    return baseRam;
  }

  public void setBaseRam(int baseRam) {
    this.baseRam = baseRam;
  }

  // Extra functionality

}
