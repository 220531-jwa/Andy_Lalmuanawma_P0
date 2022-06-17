package BankingAppAPI.models;

public class ClientAccountAlternate {
	private int id;
	private String firstName;
	private String lastName;
	private int accountNumber;
	private int whichAmount;
	
	public ClientAccountAlternate() {
		super();
	}
	
	public ClientAccountAlternate(int id, String firstName, String lastName, int accountNumber,
			int whichAmount) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.whichAmount = whichAmount;
	}
	
	//This is a getter method for the id

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

	// This is a getter method for whichAmount (checkingAmount or savingsAmount)
	
	public int getWhichAmount() {
		return whichAmount;
	}

	// This is a setter method for the whichAmount (checkingAmount or savingsAmount)
	
	public void setWhichAmount(int whichAmount) {
		this.whichAmount = whichAmount;
	}

	@Override
	public String toString() {
		return "ClientAccountAlternate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", accountNumber=" + accountNumber + ", whichAmount=" + whichAmount + "]";
	}
}