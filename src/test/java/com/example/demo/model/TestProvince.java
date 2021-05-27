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

import com.example.demo.entity.tinhthanh.Province;
import com.example.demo.repository.tinhthanh.ProvinceRepository;

@SpringBootTest()
@TestMethodOrder(OrderAnnotation.class)
public class TestProvince {

	@Autowired
	public ProvinceRepository provinceRepos;
	
	 //Test tạo đối tượng Province
	@Test
	@Order(1)
	public void testCreate() {
		List<Province> list = new ArrayList<>();
		list.add(new Province("02TTT", "Thành phố Hà Nội 1"));
//		list.add(new Province("80TTT", "Thành phố Hồ Chí Minh 1"));
		for (Province item : list) {
			item.setName(item.getName());
			try {
				provinceRepos.save(item);
			} catch (Exception e) {
			}
		}
		assertNotEquals(list.size(), 0);
		try {
			assertNotNull(provinceRepos.findById("02TTT").get());
//			assertNotNull(provinceRepos.findById("80TTT").get());
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Test lấy tất cả các dòng trong bảng
	@Test
	@Order(2)
	public void testReadAll() {
		List<Province> list = new ArrayList<Province>();
		try {
			list = (List<Province>) provinceRepos.findAll();
			assertNotNull(list);
			assertNotEquals(list.size(), 0);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	//Test lấy dòng vừa thêm vào bảng
	@Test
	@Order(3)
	public void testSingleRow() {
		Province province = new Province();
		try {
			province = provinceRepos.findById("02TTT").get();
			assertEquals(province.getName(), "Thành phố Hà Nội 1");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	//Test sửa dữ liệu trong bảng
	@Test
	@Order(4)
	public void testUpdate() {
		Province province = new Province();
		try {
			province = provinceRepos.findById("02TTT").get();
			province.setName("Thành phố Hà Nội 2");
			provinceRepos.save(province);
			assertNotEquals("Thành phố Hà Nội 1", provinceRepos.findById("02TTT").get().getName());
			assertEquals("Thành phố Hà Nội 2", provinceRepos.findById("02TTT").get().getName());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

	}
	
	 //Test xóa dữ liệu trong bảng
    @Test
    @Order(5)
    public void testDelete(){
        try {
        	provinceRepos.deleteById("02TTT");
            assertEquals(provinceRepos.existsById("02TTT"), false);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

}
