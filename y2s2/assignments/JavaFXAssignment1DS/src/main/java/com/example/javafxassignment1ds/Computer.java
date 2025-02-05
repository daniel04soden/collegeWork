//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package com.example.javafxassignment1ds;

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

//--------------------------------------------------
//
//	CLASS Computer
//
//--------------------------------------------------

/**
 * This class models a computer based off the product parent class <br>
 */
public class Computer extends Product{
	//---------------------------------------
	//	Fields
	//---------------------------------------

	public String compName; // Name of computer - configuration for it like gaming pc or productivity pc
	public int gbRam; // Amount of ram in pc
	public int gbStorage; // Amount of storage in pc
	public String CPU; // CPU in pc
	public String GPU; // GPU in pc

	//---------------------------------------
	//	Constructor
	//---------------------------------------
	/**
	 * The constructor creates 1 instance (1 object) of the class Computer<br>
	 * Although my other classes insert it into sql, we will not be explicitly registering computers
	 * @param computerID - The ID of the computer spec
	 * @param _compName - The name of the computer.
	 * @param _price - The price of the computer.
	 * @param _currentStock - the amount of stock we have of this computer.
	 * @param _amountOnOrder - The amount more we have on order for this computer.
	 * @param _gbRAM - The gigabytes of ram in this computer.
	 * @param _gbStorage- The gigabytes of storage in this computer.
	 * @param _CPU - The CPU in this computer.
	 * @param _GPU - The GPU in this computer.
	 */
	public Computer(int computerID,String _compName,double _price, int _currentStock, int _amountOnOrder, int _gbRAM,
					int _gbStorage, String _CPU, String _GPU){
		// 1. Assigning id,price and stocks on the parent class

		super(computerID,_price,_currentStock,_amountOnOrder);

		// 2. Initialise Remaining fields
		this.compName = _compName;
		this.gbRam = _gbRAM;
		this.gbStorage = _gbStorage;
		this.GPU = _GPU;
		this.CPU = _CPU;
	}
	//---------------------------------------
	//	GET METHODS
	//---------------------------------------

	/**
	 * @return amountOnOrder - retrieve amount on order from database
	 */
	@Override
	public int getAmountOnOrder(){ // Not used but if done again implemented as an order more method
		var sql = "SELECT amountOnOrder FROM computers WHERE productNo = ?"; // Select statement grabbing amount on order

		try (var conn = DriverManager.getConnection(Database.url); // Connecting to database
			 var pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, this.getId()); // Set the product ID as the first parameter

			try (var rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("stock"); // Best case scenario
				} else { // Customer cannot be found
					System.err.println("Product " + this.getId() + " not found.");
					return 0; // No id
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage()); // SQLError
			return 0;
		}
	}
	//---------------------------------------
	//	getGbStorage
	//---------------------------------------
	/**
	 * Given a concrete computer, this method returns its gigabytes of storage.<br>
	 * @return Gigabytes of storage.
	 */
	public int getGbStorage() {
		return this.gbStorage;
	}

	//---------------------------------------
	//	getGbRam
	//---------------------------------------
	/**
	 * Given a concrete computer, this method returns its gigabytes of Ram.<br>
	 * @return Gigabytes of Ram.
	 */
	public int getGbRam() {
		return this.gbRam;
	}

	//---------------------------------------
	//	getCPU
	//---------------------------------------
	/**
	 * Given a concrete computer, this method returns its CPU.<br>
	 * @return Name of the computers CPU.
	 */
	public String getCPU() {
		return this.CPU;
	}

	//---------------------------------------
	//	getGPU
	//---------------------------------------
	/**
	 * Given a concrete computer, this method returns its graphics card.<br>
	 * @return Name of the computers GPU.
	 */
	public String getGPU() {
		return this.GPU;
	}

	//---------------------------------------
	//	getCompName
	//---------------------------------------
	/**
	 * Given a concrete computer, this method returns its name.<br>
	 * @return  Name of the computer configuration .
	 */
	public String getCompName() {
		return this.compName;
	}

	//---------------------------------------
	//	SET METHODS
	//---------------------------------------

	//---------------------------------------
	//	setGbStorage
	//---------------------------------------
	/**
	 * Given a concrete computer, this method sets the amount of storage in the computer.h<br>
	 * @param gbStorage - new gigabytes of storage in the computer
     */
	public void setGbStorage(int gbStorage) {
		this.gbStorage = gbStorage;
	}

	//---------------------------------------
	//	setGbRam
	//---------------------------------------
	/**
	 * Given a concrete computer, this method sets the amount of ram in the computer.<br>
	 * @param gbRam - New gigabytes of ram to set computer to have
     */
	public void setGbRam(int gbRam) {
		this.gbRam = gbRam;
	}


	//---------------------------------------
	//	setCPU
	//---------------------------------------
	/**
j	 * Given a concrete computer, this method sets the name of the cpu ie new CPU.<br>
	 * @param cPU - New cpu to set computer to have
     */
	public void setCPU(String cPU) {
		this.CPU = cPU;
	}


	//---------------------------------------
	//	setGPU
	//---------------------------------------
	/**
	 * Given a concrete computer, this method sets the name of the gpu ie new GPU.<br>
	 * @param gPU - New cpu to set computer to have
     */
	public void setGPU(String gPU) {
		this.GPU = gPU;
	}


	//---------------------------------------
	//	setCompName
	//---------------------------------------
	/**
	 * Given a concrete computer, this method sets its config name.<br>
	 * @param compName - New name to set computer name to
     */
	public void setCompName(String compName) {
		this.compName = compName;
	}

	//---------------------------------------
	//	EXTRA METHODS
	//---------------------------------------

	//---------------------------------------
	//	retrievePurchaseData
	//---------------------------------------
	/**
	 * This method creates a new arraylist of type objects in order
	 * to store any datatype. Then we grab sql data and add it to this arraylist
	 * to then index into in other methods
	 * @param prodId product id provided to get this data
	 * @return P ArrayList of various objects to store sql data
	 */
	public static ArrayList<Object> retrievePurchaseData(int prodId){
		ArrayList<Object> P = new ArrayList<>(); // Assigning a somewhat generic ArrayList
		var sqlProductLookup = "SELECT productNo,compName,price,stock FROM computers WHERE productNo=" +
				prodId + ";";
		try (var conn = DriverManager.getConnection(Database.url);
			 var prepStmt = conn.createStatement();
			 var rs = prepStmt.executeQuery(sqlProductLookup)
		){
			int sqlProductNo = rs.getInt("productNo");
			String sqlProductName = rs.getString("compName");
			double sqlProductCost= rs.getDouble("price");
			P.add(sqlProductNo); // Adding our values to the ArrayList P
			P.add(sqlProductCost);
			P.add(sqlProductName);
		} catch (SQLException e) { // Prevent SQL Errors
			System.err.println(e.getMessage());
		}
		return P; // Once array is filled with data, return it back
	}
}
