package org.example;

public class Computer extends Product{

	public int gbRam;
	public int gbStorage;
	public String CPU;
	public String GPU;

	public Computer(int computerID,double _price, int _currentStock, int _amountOnOrder, int _gbRAM, int _gbStorage, String _CPU, String _GPU){
		super(computerID,_price,_currentStock,_amountOnOrder);
		this.gbRam = _gbRAM;
		this.gbStorage = _gbStorage;
		this.GPU = _GPU;
		this.CPU = _CPU;
	}

	public int getGbRam() {
		return gbRam;
	}

	public void setGbRam(int gbRam) {
		this.gbRam = gbRam;
	}

	public int getGbStorage() {
		return gbStorage;
	}

	public void setGbStorage(int gbStorage) {
		this.gbStorage = gbStorage;
	}

	public String getCPU() {
		return CPU;
	}

	public void setCPU(String cPU) {
		this.CPU = cPU;
	}

	public String getGPU() {
		return GPU;
	}

	public void setGPU(String gPU) {
		this.GPU = gPU;
	}


}
