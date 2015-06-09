/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springjdbcexample.dao;

/**
 *
 * @author jupiter
 */
public class Contact {
	private String name;
	private String email;
	private String address;
	private String phone;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String telephone) {
		this.phone = telephone;
	}

        @Override
	public String toString() {
		return String.format("[%s - %s - %s - %s]", name, email, address, phone);
	}
}
