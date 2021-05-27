package com.example.demo.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Thanhtoan;
import com.example.demo.entity.User;
import com.example.demo.repository.ThanhtoanRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest()
@TestMethodOrder(OrderAnnotation.class)
public class TestThanhtoan {

	@Autowired
	private ThanhtoanRepository ttRepos;

	@Autowired
	private UserRepository userRepos;

	// Test tạo đối tượng Thanhtoan
	@Test
	@Order(1)
	public void testCreate() {
		Thanhtoan tt = new Thanhtoan();
		User user = new User();
		user.setId(1000L);
		tt.setId(100L);
		tt.setUser(user);
		tt.setNoiDung("Thanh toán BHXH tháng 5 - 2020");
		tt.setNgayThanhToan("2020-05-24 10:03:54");
		tt.setSoTien(10000000D);
		tt.setNganHang("Ngân hàng Agribank");
		tt.setChiNhanhNganHang("Chi nhánh 1");
		tt.setCode("0123456789");
		tt.setLoaiGiaoDich(1);
		try {
			userRepos.save(user);
			ttRepos.save(tt);
			assertNotNull(ttRepos.getOne(100L));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test lấy tất cả các dòng trong bảng
	@Test
	@Order(2)
	public void testReadAll() {
		List<Thanhtoan> list = new ArrayList<Thanhtoan>();
		try {
			list = ttRepos.findAll();
			assertNotNull(list);
			assertNotEquals(list.size(), 0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test lấy dòng vừa thêm vào bảng
	@Test
	@Order(3)
	public void testSingleRow() {
		Thanhtoan tt = new Thanhtoan();
		try {
			tt = ttRepos.getOne(100L);
			assertNotNull(tt);
			assertEquals(tt.getCode(), "0123456789");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test sửa dữ liệu trong bảng
	@Test
	@Order(4)
	public void testUpdate() {
		Thanhtoan tt = new Thanhtoan();
		try {
			tt = ttRepos.getOne(100L);
			tt.setSoTien(20000000D);
			tt.setNoiDung("Thanh toán BHXH tháng 4 - 2020");
			assertNotNull(tt);
			assertNotEquals("Thanh toán BHXH tháng 5 - 2020", ttRepos.getOne(100L).getNoiDung());
			assertEquals("Thanh toán BHXH tháng 4 - 2020", ttRepos.getOne(100L).getNoiDung());

			assertNotEquals(10000000D, ttRepos.getOne(100L).getSoTien());
			assertEquals(20000000D, ttRepos.getOne(100L).getSoTien());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// Test xóa dữ liệu trong bảng
	@Test
	@Order(5)
	public void testDelete() {
		try {
			ttRepos.deleteById(100L);
			assertEquals(ttRepos.existsById(100L), false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
