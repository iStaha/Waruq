package com.waruq.models;

import java.util.ArrayList;
import java.util.List;

/**
 * User.java This is a model class represents a User entity
 * 
 * @author Taha Latief
 *
 */
public class User {
	protected int id;
	protected String name;
	protected String email;
	protected String country;
	protected String item;
	List<Product> list = new ArrayList<>();

	public User() {
	}

	public User(String name, String email, String country, List<Product> list ) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
		this.list = list;
	}

	public User(int id, String name, String email, String country, List<Product> list ) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", country=" + country + ", list=" + list + "]";
	}
	
	

	/*
	 * public String getItem() { return item; }
	 * 
	 * public void setItem(String item) { this.item = item; }
	 */

}