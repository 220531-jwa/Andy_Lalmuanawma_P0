package BankingAppAPI.models;

public class Account {
	private int clientId;
	private int accountNumber;
	private int checkingAmount;
	private int savingsAmount;
	
	public Account() {
		super();
	}
	
	public Account(int accountNumber, int clientId, int checkingAmount, int savingsAmount) {
		super();
		this.accountNumber = accountNumber;
		this.clientId = clientId;
		this.checkingAmount = checkingAmount;
		this.savingsAmount = savingsAmount;
	}
	
	// This is a getter method for the clientId
	
	public int getClientId() {
		return clientId;
	}
	
	// This is a setter method for the clientId
	
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	// This is a getter method for the accountNumber
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	// This is a setter method for the accountNumber

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	 // This is a getter method for the checkingAmount
	
	public int getCheckingAmount() {
		return checkingAmount;
	}
	
	// This is a setter method for the checkingAmount
	
	public void setCheckingAmount(int checkingAmount) {
		this.checkingAmount = checkingAmount;
	}
	
	//This is a getter method for the savingsAmount
	
	public int getSavingsAmount() {
		return savingsAmount;
	}
	
	// This is a setter method for the savingsAmount
	
	public void setSavingsAmount(int savingsAmount) {
		this.savingsAmount = savingsAmount;
	}
	
	@Override
	public String toString() {
		return "[account_number=" + accountNumber  + ", client_id=" + clientId + 
				", checking=" + checkingAmount + ", savings=" + savingsAmount + "]";
	}
}