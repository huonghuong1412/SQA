package com.example.demo.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.common.Erole;
import com.example.demo.entity.Address;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest()
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUpdateUser {

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private RoleRepository roleRepos;

	@Autowired
	private PasswordEncoder encoder;

	// Rollback PUT
	// Test tạo đối tượng user
	@Test
	@Order(5)
	public void testCreate1() {
		Address address = new Address("Thành phố 1", "Quận Huyện 1", "Phường Xã 1", "Số nhà 1");
		User user = new User();
		user.setUsername("0000000002");
		user.setCccd("000000000002");
		user.setFullName("Nguyễn Văn B");
		user.setDateOfBirth("1999-12-14");
		try {
			user.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		user.setTypeUser(1);
		user.setPhone("0123456789");
		user.setEmail("abc@gmail.com");

		Set<Role> roles = new HashSet<>();
		Role userRole = new Role();
		try {
			userRole = roleRepos.findByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		roles.add(userRole);
		user.setRoles(roles);
		user.setAddress(address);
		address.setUser(user);
		Set<User> setUser = new HashSet<User>();
		setUser.add(user);
		userRole.setUsers(setUser);
		try {
			userRepos.save(user);
			assertNotNull(userRepos.findById(user.getId()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test lấy dòng vừa thêm vào bảng
	@Test
	@Order(6)
	public void testSingleRow2() {
		User user = new User();
		try {
			user = userRepos.getOne(userRepos.getNextSeriesId());
			assertNotNull(userRepos.getOne(userRepos.getNextSeriesId()));
			assertEquals(user.getUsername(), "0000000002");
			assertEquals(user.getCccd(), "000000000002");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test sửa dữ liệu trong bảng
	@Test
	@Order(6)
	public void testUpdate() {
		User user = new User();
		try {
			user = userRepos.getOne(userRepos.getNextSeriesId());
			user.setFullName("Nguyễn Văn C");
			user.setPhone("0999999999");
			user.setEmail("test@gmail.com");
			userRepos.save(user);

			assertNotEquals("Nguyễn Văn B", userRepos.getOne(userRepos.getNextSeriesId()).getFullName());
			assertEquals("Nguyễn Văn C", userRepos.getOne(2L).getFullName());

			assertNotEquals("0123456789", userRepos.getOne(userRepos.getNextSeriesId()).getPhone());
			assertEquals("0999999999", userRepos.getOne(2L).getPhone());

			assertNotEquals("abc@gmail.com", userRepos.getOne(userRepos.getNextSeriesId()).getEmail());
			assertEquals("test@gmail.com", userRepos.getOne(2L).getEmail());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test lấy dòng vừa thêm vào bảng
	@Test
	@Order(7)
	public void testSingleRow3() {
		User user = new User();
		try {
			user = userRepos.getOne(userRepos.getNextSeriesId());
			assertNotNull(userRepos.getOne(userRepos.getNextSeriesId()));
			assertEquals(user.getUsername(), "0000000002");
			assertEquals(user.getCccd(), "000000000002");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test xóa dữ liệu vừa thêm vào bảng
	@Test
	@Order(8)
	public void testDelete2() {
		try {
			userRepos.deleteById(userRepos.getNextSeriesId());
			assertEquals(userRepos.existsById(userRepos.getNextSeriesId()), false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test lấy dòng vừa thêm vào bảng, nếu null là đúng
	@Test
	@Order(9)
	public void testSingleRow4() {
		User user = new User();
		try {
			user = userRepos.getOne(userRepos.getNextSeriesId());
			assertNull(userRepos.getOne(userRepos.getNextSeriesId()));
			assertNotEquals(user.getUsername(), "0000000002");
			assertNotEquals(user.getCccd(), "000000000002");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
