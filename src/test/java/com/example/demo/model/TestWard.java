package com.example.demo.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.tinhthanh.District;
import com.example.demo.entity.tinhthanh.Province;
import com.example.demo.entity.tinhthanh.Ward;
import com.example.demo.repository.tinhthanh.DistrictRepository;
import com.example.demo.repository.tinhthanh.ProvinceRepository;
import com.example.demo.repository.tinhthanh.WardRepository;

@SpringBootTest()
@TestMethodOrder(OrderAnnotation.class)
public class TestWard {

	@Autowired
	private ProvinceRepository provinceRepos;
	
	@Autowired
	private DistrictRepository districtRepos;
	
	@Autowired
	private WardRepository wardRepos;
	
	// Test tạo đối tượng Ward
		@Test
		@Order(1)
		public void testCreate() {
			
			Province province = new Province("01TTT", "Thành phố Hà Nội");
			District district = new District("01HHH", "Quận Ba Đình", "01TTT");
			Ward ward = new Ward();
			ward.setWardid("0001");
			ward.setName("Phường Phúc Xá 1");
			try {
				provinceRepos.save(province);
				districtRepos.save(district);
				wardRepos.save(ward);
				assertNotNull(wardRepos.findById("0001").get());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		// Test lấy tất cả các dòng trong bảng
		@Test
		@Order(2)
		public void testReadAll() {
			List<Ward> list = new ArrayList<>();
			try {
				list = (List<Ward>) wardRepos.findAll();
				assertNotNull(list);
				assertNotEquals(list.size(), 0);
			} catch (Exception e) {
				e.getMessage();
			}
		}

		// Test lấy dòng vừa thêm vào bảng
		@Test
		@Order(3)
		public void testSingleRow() {
			Ward ward = new Ward();
			try {
				ward = wardRepos.findById("0001").get();
				assertEquals(ward.getName(), "Phường Phúc Xá 1");
			} catch (Exception e) {
				e.getMessage();
			}
		}

		// Test sửa dữ liệu trong bảng
		@Test
		@Order(4)
		public void testUpdate() {
			Ward ward = new Ward();
			try {
				ward = wardRepos.findById("0001").get();
				ward.setName("Phường Phúc Xá 2");
				wardRepos.save(ward);
				assertNotEquals("Phường Phúc Xá 1", wardRepos.findById("0001").get().getName());
				assertEquals("Phường Phúc Xá 2", wardRepos.findById("0001").get().getName());
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}

		}

		// Test xóa dữ liệu trong bảng
		@Test
		@Order(5)
		public void testDelete() {
			try {
				wardRepos.deleteById("0001");
				assertEquals(wardRepos.existsById("0001"), false);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

}
