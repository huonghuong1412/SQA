package com.example.demo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest()
@TestMethodOrder(OrderAnnotation.class)
public class TestAddress {

	@Autowired
	private AddressRepository adRepos;

	@Autowired
	private UserRepository userRepos;

	// Test tạo đối tượng địa chỉ
	@Test
	@Order(1)
	public void testCreate() {
		Address address = new Address();
		User user = new User();
		user.setId(1L);
		address.setId(1000L);
		address.setCity("Thành phố 1");
		address.setDistrict("Quận Huyện 1");
		address.setWard("Phường Xã 1");
		address.setHouse("Số nhà 1");
		address.setUser(user);
		try {
			userRepos.save(user);
			address = adRepos.save(address);
			assertNotNull(adRepos.findById(1000L).get());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// Test lấy tất cả các dòng trong bảng
	@Test
	@Order(2)
	public void testReadAll() {
		List<Address> list = new ArrayList<Address>();
		try {
			list = adRepos.findAll();
			assertNotNull(list);
			assertNotEquals(list.size(), 0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test get one
	@Test
	@Order(3)
	public void testGetOne() {
		try {
			Address address = adRepos.findOneByUserId(1000L);
			assertEquals(address.getCity(), "Thành phố 1");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test sửa dữ liệu trong bảng
	@Test
	@Order(4)
	public void testUpdate() {
		Address address = new Address();
		try {
			address.setCity("Thành phố 2");
			address.setDistrict("Quận Huyện 2");
			address.setWard("Phường Xã 2");
			address.setWard("Số nhà 2");
			adRepos.save(address);
			assertNotEquals("Thành phố 1", adRepos.getOne(1000L).getCity());
			assertEquals("Thành phố 2", adRepos.getOne(1000L).getCity());
			
			assertNotEquals("Quận Huyện 1", adRepos.getOne(1000L).getDistrict());
			assertEquals("Quận Huyện 2", adRepos.getOne(1000L).getDistrict());
			
			assertNotEquals("Phường Xã 1", adRepos.getOne(1000L).getWard());
			assertEquals("Phường Xã 2", adRepos.getOne(1000L).getWard());
			
			assertNotEquals("Số nhà 1", adRepos.getOne(1000L).getHouse());
			assertEquals("Số nhà 2", adRepos.getOne(1000L).getHouse());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	 //Test xóa dữ liệu trong bảng
    @Test
    @Order(5)
    public void testDelete(){
    	try {
			adRepos.deleteById(1000L);
			assertEquals(adRepos.existsById(1000L), false);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

}
