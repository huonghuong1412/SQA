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

import com.example.demo.entity.tinhthanh.District;
import com.example.demo.entity.tinhthanh.Province;
import com.example.demo.repository.tinhthanh.DistrictRepository;
import com.example.demo.repository.tinhthanh.ProvinceRepository;

@SpringBootTest()
@TestMethodOrder(OrderAnnotation.class)
public class TestDistrict {

	@Autowired
	private DistrictRepository districtRepos;

	@Autowired
	private ProvinceRepository provinceRepos;

	// Test tạo đối tượng District
	@Test
	@Order(1)
	public void testCreate() {
		District district = new District();
		Province province = new Province("01TTT", "Thành phố Hà Nội");
		district.setDistrictid("02HHH");
		district.setName("Quận Ba Đình");
		district.setProvinceid("01TTT");
		try {
			provinceRepos.save(province);
			districtRepos.save(district);
			assertNotNull(provinceRepos.findById("02TTT").get());
			assertNotNull(districtRepos.findById("02HHH").get());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test lấy tất cả các dòng trong bảng
	@Test
	@Order(2)
	public void testReadAll() {
		List<District> list = new ArrayList<>();
		try {
			list = (List<District>) districtRepos.findAll();
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
		District district = new District();
		try {
			district = districtRepos.findById("02HHH").get();
			assertEquals(district.getName(), "Quận Ba Đình");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Test sửa dữ liệu trong bảng
	@Test
	@Order(4)
	public void testUpdate() {
		District district = new District();
		try {
			district = districtRepos.findById("02HHH").get();
			district.setName("Quận Hà Đông");
			districtRepos.save(district);
			assertNotEquals("Quận Ba Đình", districtRepos.findById("02HHH").get().getName());
			assertEquals("Quận Hà Đông", districtRepos.findById("02HHH").get().getName());
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
			districtRepos.deleteById("02HHH");
			assertEquals(districtRepos.existsById("02HHH"), false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
