package com.example.demo.common;

import java.util.List;

public class TinhPhi {

	public static Double tinhPhiBaoHiemCaNhan(Long luong) {
		if (luong <= 0) {
			return 0.0;
		} else if (luong > 0 && luong <= 4000000) {
			return 1.0;
		} else {
			return luong * 0.08;
		}
	}
	
	public static Double tinhPhiBaoHiemDN(Long luong) {
		if (luong <= 0) {
			return 0.0;
		}
		return luong * 0.175;
	}
	
	public static Double tinhPhiBaoHiemTudo(Long luong) {
		if (luong <= 0) {
			return 0.0;
		}
		return luong * 0.22;
	}
	
	public static Long tongLuong(List<Long> list) {
		Long tong = 0L;
		for(Long i : list) {
			tong += i;
		}
		return tong;
	}

}
