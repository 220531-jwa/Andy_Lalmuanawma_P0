/*
 * This ClientAccountBoth class is a model for the
 * clients and accounts tables in the database which is used in 
 * AccountDAO, AccountService, and AccountController. 
 * This account has both the checking account amount and the savings account amount
 */

package BankingAppAPI.models;

public class ClientAccountBoth {
	private int id;
	private String firstName;
	private String lastName;
	private int accountNumber;
	private int checkingAmount;
	private int savingsAmount;
	
	public ClientAccountBoth() {
		super();
	}
	
	public ClientAccountBoth(int id, String firstName, String lastName, int accountNumber,
			int checkingAmount, int savingsAmount) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.checkingAmount = checkingAmount;
		this.savingsAmount = savingsAmount;
	}
	
	// This is a getter method for the id

	public int getId() {
		return id;
	}

	// This is a setter method for the id
	
	public void setId(int id) {
		this.id = id;
	}

	// This is a getter method for the firstName
	
	public String getFirstName() {
		return firstName;
	}

	// This is a setter method for the firstName
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// This is a getter method for the lastName
	
	public String getLastName() {
		return lastName;
	}

	// This is a setter method for the lastName
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// This is a getter method for the accountNumber
	
	public int getAccountNumber() {
		return accountNumber;
	}

	// This is a setter method for the accountNumber
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	// This ia getter method for the checkingAmount
	
	public int getCheckingAmount() {
		return checkingAmount;
	}

	/**
	 * This is a setter method for the checkingAmount
	 * 
	 * @retur
	 */
	public void setCheckingAmount(int checkingAmount) {
		this.checkingAmount = checkingAmount;
	}

	/**
	 * This is a getter method for the savingsAmount
	 * 
	 * @return
	 */
	public int getSavingsAmount() {
		return savingsAmount;
	}

	/**
	 * This is a setter method for the savingsAmount
	 * 
	 * @return
	 */
	public void setSavingsAmount(int savingsAmount) {
		this.savingsAmount = savingsAmount;
	}

	@Override
	public String toString() {
		return "ClientAccountBoth [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", accountNumber=" + accountNumber + ", checkingAmount=" + checkingAmount
				+ ", savingsAmount=" + savingsAmount + "]";
	}
	
	
}