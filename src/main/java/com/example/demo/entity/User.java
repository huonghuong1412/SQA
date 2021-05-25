package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {

	@Column(name = "username")
	private String username;

	@Column(name = "cccd")
	private String cccd;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@ManyToMany
	@JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address address = new Address();

	// Thong tin khai bao

	@Column(name = "type_user") // 1: Hop dong, 2: tu do
	private Integer typeUser;

	@Column(name = "cqbh_thanh_pho")
	private String coQuanBaoHiemThanhPho;

	@Column(name = "cqbh_quan_huyen")
	private String coQuanBaoHiemQuanHuyen;

	@Column(name = "salary")
	private Long salary;

	@Column(name = "ma_so_thue")
	private String maSoThue;

	@Column(name = "ten_don_vi")
	private String tenDonVi;

	@Column(name = "ma_don_vi")
	private String maDonVi;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Thanhtoan> thanhtoans = new ArrayList<>();

	public User() {
	}

	public User(String username, String cccd, String fullName, String dateOfBirth, String password, String phone,
			String email, Address address, Integer typeUser, String coQuanBaoHiemThanhPho,
			String coQuanBaoHiemQuanHuyen, Long salary, String maSoThue, String tenDonVi, String maDonVi) {
		super();
		this.username = username;
		this.cccd = cccd;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.typeUser = typeUser;
		this.coQuanBaoHiemThanhPho = coQuanBaoHiemThanhPho;
		this.coQuanBaoHiemQuanHuyen = coQuanBaoHiemQuanHuyen;
		this.salary = salary;
		this.maSoThue = maSoThue;
		this.tenDonVi = tenDonVi;
		this.maDonVi = maDonVi;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(Integer typeUser) {
		this.typeUser = typeUser;
	}

	public String getCoQuanBaoHiemThanhPho() {
		return coQuanBaoHiemThanhPho;
	}

	public void setCoQuanBaoHiemThanhPho(String coQuanBaoHiemThanhPho) {
		this.coQuanBaoHiemThanhPho = coQuanBaoHiemThanhPho;
	}

	public String getCoQuanBaoHiemQuanHuyen() {
		return coQuanBaoHiemQuanHuyen;
	}

	public void setCoQuanBaoHiemQuanHuyen(String coQuanBaoHiemQuanHuyen) {
		this.coQuanBaoHiemQuanHuyen = coQuanBaoHiemQuanHuyen;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	public String getTenDonVi() {
		return tenDonVi;
	}

	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}

	public String getMaDonVi() {
		return maDonVi;
	}

	public void setMaDonVi(String maDonVi) {
		this.maDonVi = maDonVi;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Thanhtoan> getThanhtoans() {
		return thanhtoans;
	}

	public void setThanhtoans(List<Thanhtoan> thanhtoans) {
		this.thanhtoans = thanhtoans;
	}

}
