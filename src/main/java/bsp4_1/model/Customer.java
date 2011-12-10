package bsp4_1.model;

public class Customer {

	private Address address;
	private String firstName;
	private String lastName;

	public Customer(Address address, String firstName, String lastName) {
		super();
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
