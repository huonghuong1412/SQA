package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_thanhtoan")
public class Thanhtoan extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "noi_dung")
	private String noiDung;

	@Column(name = "ngay_thanh_toan")
	private String ngayThanhToan;

	@Column(name = "so_tien")
	private Double soTien;

	@Column(name = "ngan_hang")
	private String nganHang;

	@Column(name = "chi_nhanh_ngan_hang")
	private String chiNhanhNganHang;

	@Column(name = "loai_giao_dich")
	private Integer loaiGiaoDich;

	public Thanhtoan() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(String ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public Double getSoTien() {
		return soTien;
	}

	public void setSoTien(Double soTien) {
		this.soTien = soTien;
	}

	public String getNganHang() {
		return nganHang;
	}

	public void setNganHang(String nganHang) {
		this.nganHang = nganHang;
	}

	public String getChiNhanhNganHang() {
		return chiNhanhNganHang;
	}

	public void setChiNhanhNganHang(String chiNhanhNganHang) {
		this.chiNhanhNganHang = chiNhanhNganHang;
	}

	public Integer getLoaiGiaoDich() {
		return loaiGiaoDich;
	}

	public void setLoaiGiaoDich(Integer loaiGiaoDich) {
		this.loaiGiaoDich = loaiGiaoDich;
	}

}
