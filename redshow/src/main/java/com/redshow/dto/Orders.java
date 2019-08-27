package com.redshow.dto;

public class Orders {

	private Integer id;
	
	private Integer book_id;
	
	private Integer user_id;
	
	private Integer booksnum;
	
	private Double books_totle_price;
	
	private Integer state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getBooksnum() {
		return booksnum;
	}

	public void setBooksnum(Integer booksnum) {
		this.booksnum = booksnum;
	}

	public Double getBooks_totle_price() {
		return books_totle_price;
	}

	public void setBooks_totle_price(Double books_totle_price) {
		this.books_totle_price = books_totle_price;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", book_id=" + book_id + ", user_id=" + user_id + ", booksnum=" + booksnum
				+ ", books_totle_price=" + books_totle_price + ", state=" + state + "]";
	}
	
}
