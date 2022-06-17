package BankingAppAPI.models;

public class Client {
	private int id;
	private String firstName;
	private String lastName;
	
	public Client() {
		super();
	}
	
	public Client(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// This is a getter method for the id
	
	public int getId() {
		return id;
	}
	
	// This is a setter method for the id
	
	public void setID(int id) {
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

	@Override
	
	public String toString() {
		return "Client [id=" + id +  ", first_name=" + firstName + ", last_name=" + lastName + "]";
	}
}