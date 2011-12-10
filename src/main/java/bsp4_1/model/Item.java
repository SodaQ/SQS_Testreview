package bsp4_1.model;

public abstract class Item {

	private static long idCount = 0;

	private Long id;

	private Double price;

	public Item() {
	}

	public Item(Double price) {
		this.price = price;
		this.id = idCount++;
	}

	public Long getId() {
		return id;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

}
