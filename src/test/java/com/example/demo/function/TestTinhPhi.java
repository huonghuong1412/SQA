package com.example.demo.function;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.example.demo.common.TinhPhi;

public class TestTinhPhi {

	public TestTinhPhi() {
		// TODO Auto-generated constructor stub
	}
	
	// Test phần lao động có hợp đồng
	
	@Test
	public void testTH1() {		// Test cho trường hợp lương cá nhân < 0
		Long i = -10000L;
        Double expected = 0.0;
        Double actual = TinhPhi.tinhPhiBaoHiemCaNhan(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH2() {		// // Test cho trường hợp lương cá nhân = 0
		long i = 0;
        final double expected = 0;
        final double actual = TinhPhi.tinhPhiBaoHiemCaNhan(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH3() {		// // Test cho trường hợp lương cá nhân < 4 triệu 4 trăm 20 nghìn
		long i = 1000000;
        final double expected = 1.0;
        final double actual = TinhPhi.tinhPhiBaoHiemCaNhan(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH4() {		// // Test cho trường hợp lương cá nhân = 4 triệu 4 trăm 20 nghìn
		long i = 4420000;
        final double expected = 353600;
        final double actual = TinhPhi.tinhPhiBaoHiemCaNhan(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH5() {		// // Test cho trường hợp lương cá nhân > 4 triệu 4 trăm 20 nghìn & < 29 triệu 8 trăm nghìn
		long i = 5000000;
        final double expected = 400000;
        final double actual = TinhPhi.tinhPhiBaoHiemCaNhan(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH6() {		// // Test cho trường hợp lương cá nhân = 29 triệu 8 trăm nghìn
		long i = 29800000;
        final double expected = 2384000;
        final double actual = TinhPhi.tinhPhiBaoHiemCaNhan(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH7() {		// // Test cho trường hợp lương cá nhân > 29 triệu 8 trăm nghìn
		long i = 30000000;
        final double expected = 2.0;
        final double actual = TinhPhi.tinhPhiBaoHiemCaNhan(i);
        assertEquals(expected, actual);
	}
	
	// Test đóng bảo hiểm doanh nghiệp
	
	@Test
	public void testTH15() {		// Test cho trường hợp lương cá nhân < 0
		Long i = -10000L;
        Double expected = 0.0;
        Double actual = TinhPhi.tinhPhiBaoHiemDN(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH16() {		// // Test cho trường hợp lương cá nhân = 0
		long i = 0;
        final double expected = 0;
        final double actual = TinhPhi.tinhPhiBaoHiemDN(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH17() {		// // Test cho trường hợp lương cá nhân < 4 triệu 4 trăm 20 nghìn
		long i = 1000000;
        final double expected = 1.0;
        final double actual = TinhPhi.tinhPhiBaoHiemDN(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH18() {		// // Test cho trường hợp lương cá nhân = 4 triệu 4 trăm 20 nghìn
		long i = 4420000;
        final double expected = 773500;
        final double actual = TinhPhi.tinhPhiBaoHiemDN(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH19() {		// // Test cho trường hợp lương cá nhân > 4 triệu 4 trăm 20 nghìn & < 29 triệu 8 trăm nghìn
		long i = 5000000;
        final double expected = 875000;
        final double actual = TinhPhi.tinhPhiBaoHiemDN(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH20() {		// // Test cho trường hợp lương cá nhân = 29 triệu 8 trăm nghìn
		long i = 29800000;
        final double expected = 5215000;
        final double actual = TinhPhi.tinhPhiBaoHiemDN(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH21() {		// // Test cho trường hợp lương cá nhân > 29 triệu 8 trăm nghìn
		long i = 30000000;
        final double expected = 2.0;
        final double actual = TinhPhi.tinhPhiBaoHiemDN(i);
        assertEquals(expected, actual);
	}

	// Test phần lao động tự do
	@Test
	public void testTH8() {		// Test cho trường hợp lương cá nhân < 0
		long i = -10000;
        final double expected = 0;
        final double actual = TinhPhi.tinhPhiBaoHiemTudo(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH9() {		// // Test cho trường hợp lương cá nhân = 0
		long i = 0;
        final double expected = 0;
        final double actual = TinhPhi.tinhPhiBaoHiemTudo(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH10() {		// // Test cho trường hợp lương cá nhân > 0 và < 700 nghìn
		long i = 500000;
        final double expected = 2.0;
        final double actual = TinhPhi.tinhPhiBaoHiemTudo(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH11() {		// // Test cho trường hợp lương cá nhân = 700 nghìn
		long i = 700000;
        final double expected = 154000;
        final double actual = TinhPhi.tinhPhiBaoHiemTudo(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH12() {		// // Test cho trường hợp lương cá nhân = 29 triệu 8 trăm nghìn
		long i = 29800000;
        final double expected = 6556000;
        final double actual = TinhPhi.tinhPhiBaoHiemTudo(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH13() {		// // Test cho trường hợp lương cá nhân > 29 triệu 8 trăm nghìn
		long i = 30000000;
        final double expected = 1.0;
        final double actual = TinhPhi.tinhPhiBaoHiemTudo(i);
        assertEquals(expected, actual);
	}
	
	@Test
	public void testTH14() {		// // Test cho trường hợp lương cá nhân > 700 nghìn & < 29 triệu 8 trăm nghìn
		long i = 10000000;
        final double expected = 2200000;
        final double actual = TinhPhi.tinhPhiBaoHiemTudo(i);
        assertEquals(expected, actual);
	}
	
}
