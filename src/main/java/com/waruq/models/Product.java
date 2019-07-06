package com.waruq.models;

public class Product {

	protected int id;
	protected String item;
	protected String user_id;
	
	public Product( String item, String user_id) {
		super();
		this.item = item;
		this.user_id = user_id;
	}
	public Product(int id,  String item, String user_id) {
		super();
		this.id = id;
		this.item = item;
		this.user_id = user_id;
	}
	public Product(int id,  String item) {
		super();
		this.id = id;
		this.item = item;
		
	}
	
	public Product( String item) {
		super();
		this.item = item;
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", item=" + item + ", user_id=" + user_id + "]";
	}
	
	
	
	
}
