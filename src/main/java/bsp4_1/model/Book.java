package bsp4_1.model;

public class Book extends Item {

	private String bookName;

	private int pages;

	private String author;

	public Book(Double price, String bookName, String author, int pages) {
		super(price);
		this.bookName = bookName;
		this.setAuthor(author);
		this.pages = pages;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

}
