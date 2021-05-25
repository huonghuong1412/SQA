package com.example.demo.dto;

import com.example.demo.entity.Thanhtoan;

public class ThanhtoanDto extends AbstractDTO<ThanhtoanDto> {

	private String noiDung;
	private String ngayThanhToan;
	private Double soTien;
	private String nganHang;
	private String chiNhanhNganHang;
	private Integer loaiGiaoDich;
	private UserDto user;

	public ThanhtoanDto() {
		// TODO Auto-generated constructor stub
	}

	public ThanhtoanDto(Thanhtoan entity) {
		super();
		this.setId(entity.getId());
		this.noiDung = entity.getNoiDung();
		this.ngayThanhToan = entity.getNgayThanhToan();
		this.soTien = entity.getSoTien();
		this.nganHang = entity.getNganHang();
		this.chiNhanhNganHang = entity.getChiNhanhNganHang();
		this.loaiGiaoDich = entity.getLoaiGiaoDich();
		this.user = new UserDto(entity.getUser());
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

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
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
