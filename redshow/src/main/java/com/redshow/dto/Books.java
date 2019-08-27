package com.redshow.dto;

public class Books {
	
	private Integer id;
	
	private String books_names;
	
	private Double books_price;
	
	private String books_another;
	
	private String books_remake;
	
	private String books_imgtypes;
	
	private String books_url;
	
	private Integer books_typeid;
	
	private Integer state;
	
	private Integer user_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBooks_names() {
		return books_names;
	}

	public void setBooks_names(String books_names) {
		this.books_names = books_names;
	}

	public Double getBooks_price() {
		return books_price;
	}

	public void setBooks_price(Double books_price) {
		this.books_price = books_price;
	}

	public String getBooks_another() {
		return books_another;
	}

	public void setBooks_another(String books_another) {
		this.books_another = books_another;
	}

	public String getBooks_remake() {
		return books_remake;
	}

	public void setBooks_remake(String books_remake) {
		this.books_remake = books_remake;
	}

	public String getBooks_imgtypes() {
		return books_imgtypes;
	}

	public void setBooks_imgtypes(String books_imgtypes) {
		this.books_imgtypes = books_imgtypes;
	}

	public String getBooks_url() {
		return books_url;
	}

	public void setBooks_url(String books_url) {
		this.books_url = books_url;
	}

	public Integer getBooks_typeid() {
		return books_typeid;
	}

	public void setBooks_typeid(Integer books_typeid) {
		this.books_typeid = books_typeid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", books_names=" + books_names + ", books_price=" + books_price + ", books_another="
				+ books_another + ", books_remake=" + books_remake + ", books_imgtypes=" + books_imgtypes
				+ ", books_url=" + books_url + ", books_typeid=" + books_typeid + ", state=" + state + ", user_id="
				+ user_id + "]";
	}

	
}
