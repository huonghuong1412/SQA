package com.example.demo.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
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
public class TestCreateUser {

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private RoleRepository roleRepos;

	@Autowired
	private PasswordEncoder encoder;

	// Rollback POST
	// Test tạo đối tượng user
	@Test
	@Order(1)
	public void testCreate() {
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
			System.out.println("AAA " + userRepos.getNextSeriesId());
			assertNotNull(userRepos.findById(userRepos.getNextSeriesId()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test lấy dòng vừa thêm vào bảng
	@Test
	@Order(2)
	public void testSingleRow() {
		User user = new User();
		try {
			System.out.println("AAA " + userRepos.getNextSeriesId());
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
	@Order(3)
	public void testDelete() {
		try {
			System.out.println("BBB " + userRepos.getNextSeriesId());
			userRepos.deleteById(userRepos.getNextSeriesId());
			assertEquals(userRepos.existsById(userRepos.getNextSeriesId()), false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test lấy dòng vừa thêm vào bảng, nếu null là đúng
	@Test
	@Order(4)
	public void testSingleRow1() {
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
