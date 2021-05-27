package com.example.demo.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.common.Erole;
import com.example.demo.entity.Address;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest()
@TestMethodOrder(OrderAnnotation.class)
public class TestUser {

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private RoleRepository roleRepos;

	@Autowired
	private PasswordEncoder encoder;

	// Test tạo đối tượng Reole
	@Test
	@Order(1)
	public void testCreateRole() {
		Role role = new Role();
		role.setId(1L);
		role.setName(Erole.ROLE_USER);
		try {
			roleRepos.save(role);
			assertNotNull(roleRepos.getOne(1L));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test tạo đối tượng user
	@Test
	@Order(2)
	public void testCreate() {
		Address address = new Address("Thành phố 1", "Quận Huyện 1", "Phường Xã 1", "Số nhà 1");
		User user = new User();
		
		user.setUsername("0123456789");
		user.setCccd("0123456789");
		user.setFullName("Nguyễn Văn B");
		user.setDateOfBirth("1999-12-14");
		try {
			user.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		user.setTypeUser(1);
		user.setPhone("0393401266");
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
			assertNotNull(userRepos.findById(2L));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	@Order(3)
	public void testReadAll() {
		List<User> listFound = new ArrayList<User>();
		try {
			listFound = userRepos.findAll();
			assertNotNull(listFound);
			assertNotEquals(listFound.size(), 0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test lấy dòng vừa thêm vào bảng
	@Test
	@Order(4)
	public void testSingleRow() {
		User user = new User();
		try {
			user = userRepos.getOne(2L);
			assertNotNull(userRepos.getOne(2L));
			assertEquals(user.getCccd(), "0123456789");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test sửa dữ liệu trong bảng
	@Test
	@Order(4)
	public void testUpdate() {
		User user = new User();
		try {
			user = userRepos.getOne(2L);
			user.setFullName("Nguyễn Văn C");
			user.setPhone("0999999999");
			user.setEmail("test@gmail.com");
			userRepos.save(user);
			
			assertNotEquals("Nguyễn Văn B", userRepos.getOne(2L).getFullName());
			assertEquals("Nguyễn Văn C", userRepos.getOne(2L).getFullName());
			
			assertNotEquals("0123456789", userRepos.getOne(2L).getPhone());
			assertEquals("0999999999", userRepos.getOne(2L).getPhone());
			
			assertNotEquals("abc@gmail.com", userRepos.getOne(2L).getEmail());
			assertEquals("test@gmail.com", userRepos.getOne(2L).getEmail());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// Test xóa dữ liệu trong bảng
	@Test
	@Order(5)
	public void testDelete() {
		try {
			userRepos.deleteById(2L);
			assertEquals(userRepos.existsById(2L), false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
