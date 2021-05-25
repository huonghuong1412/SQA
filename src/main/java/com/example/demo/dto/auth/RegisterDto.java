package com.example.demo.dto.auth;

import java.util.Set;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.User;

public class RegisterDto extends AbstractDTO<RegisterDto> {

	private String username;
	private String cccd;
	private String fullName;
	private String password;
	private String dateOfBirth;
	private String phone;
	private String email;
	private Integer typeUser;
	private String city;
	private String district;
	private String ward;
	private String house;

	private Set<String> role;
	
	public RegisterDto() {
		super();
	}

	public RegisterDto(User user) {
		super();
		this.setId(user.getId());
		this.username = user.getUsername();
		this.cccd = user.getCccd();
		this.fullName = user.getFullName();
		this.dateOfBirth = user.getDateOfBirth();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.typeUser = user.getTypeUser();
		this.city = user.getAddress().getCity();
		this.district = user.getAddress().getDistrict();
		this.ward = user.getAddress().getWard();
		this.house = user.getAddress().getHouse();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(Integer typeUser) {
		this.typeUser = typeUser;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}
	
	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

}
