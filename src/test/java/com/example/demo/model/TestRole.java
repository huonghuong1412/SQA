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

import com.example.demo.common.Erole;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

@SpringBootTest()
@TestMethodOrder(OrderAnnotation.class)
public class TestRole {

	@Autowired
	private RoleRepository roleRepos;

	// Test tạo đối tượng Reole
	@Test
	@Order(1)
	public void testCreate() {
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

	// Test lấy tất cả các dòng trong bảng
	@Test
	@Order(2)
	public void testReadAll() {
		List<Role> list = new ArrayList<Role>();
		try {
			list = roleRepos.findAll();
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
		Role role = new Role();
		try {
			role = roleRepos.getOne(1L);
			assertEquals(role.getName(), "ROLE_USER");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test sửa dữ liệu trong bảng
	@Test
	@Order(4)
	public void testUpdate() {
		Role role = new Role();
		try {
			role = roleRepos.getOne(1L);
			role.setName(Erole.ROLE_ADMIN);
			roleRepos.save(role);
			assertNotEquals("ROLE_USER", roleRepos.getOne(1L).getName());
			assertEquals("ROLE_ADMIN", roleRepos.getOne(1L).getName());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Test xóa dữ liệu trong bảng
	@Test
	@Order(5)
	public void testDelete() {
		try {
			roleRepos.deleteById(1L);
			assertEquals(roleRepos.existsById(1L), false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
