package org.example;

public class Computer extends Product{

	public String compName;
	public int gbRam;
	public int gbStorage;
	public String CPU;
	public String GPU;

	public Computer(int computerID,String _compName,double _price, int _currentStock, int _amountOnOrder, int _gbRAM, int _gbStorage, String _CPU, String _GPU){
		super(computerID,_price,_currentStock,_amountOnOrder);
		this.compName = _compName;
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

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}
}
