package com.registration.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String address;
	
	private String city;

	private String phoneNo;
	
	private String name;
	
	
	public User() {
		
	}

	/**
	 *
	 * @param name
	 * @param id
	 * @param address
	 * @param city
	 * @param phoneNo
	 */
	public User(long id, String name, String address, String city, String phoneNo) {
		this.address = address;
		this.city = city;
		this.phoneNo = phoneNo;
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 *
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 *
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 *
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 *
	 * @return
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 *
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Overridden toString() method that return String presentation of the
	 * Object
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return new StringBuilder("{id: ").append(id).append(", name: ").append(name).append(", address: ")
				.append(address).append(", city: ").append(city).append(", phoneNo: ").append(phoneNo).append("}")
				.toString();
	}
}
