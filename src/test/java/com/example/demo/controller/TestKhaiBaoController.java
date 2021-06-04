package com.example.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.auth.RegisterDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest()
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class TestKhaiBaoController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private PasswordEncoder encoder;

//	@Test
	public void testRegisterSuccess() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000003");
		test.setCccd("000000000003");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456788");
		test.setEmail("abc1@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().isOk())
					.andExpect(content().string(containsString("Đăng ký tài khoản thành công")));
		} catch (Exception e) {
			e.getMessage();
		}
		// Status trả về là 200: OK,
	}

	// Test khai báo thành công với người lao động hợp đồng
	@Test
	public void testKhaiBaoSuccess1() throws Exception {
		// tạo mới thong tin tai khoan
		UserDto test = new UserDto();
		User user = null;
		
		try {
			user = userRepos.findOneByUsername("0000000003");
			System.out.println("111 "+user.getCccd());
			System.out.println("222 "+user.getId());
//			Long id = user.getId();

//			String token = JwtUtils.createToken(user.getId(), "0000000003", "123456789", "abc1@gmail.com");
//			assertNotNull(token);

			test.setId(user.getId());
			test.setTypeUser(1);
			test.setUsername(user.getUsername());
			test.setCccd(user.getCccd());
			test.setFullName(user.getFullName());
			test.setPassword(user.getPassword());
			test.setDateOfBirth(user.getDateOfBirth());
			test.setPhone(user.getPhone());
			test.setEmail(user.getEmail());
			test.setCity("Ha Noi");
			test.setDistrict("Phu Xuyen");
			test.setWard("Khai Thai");
			test.setHouse("Khai Thai");
			
			// Sau khi dang nhap thi khai bao them thong tin
			test.setCoQuanBaoHiemThanhPho("Thanh pho Ha Noi");
			test.setCoQuanBaoHiemQuanHuyen("BHXH Huyen Phu Xuyen");
			test.setSalary(20000000L);
			test.setMaSoThue("9999");
			test.setTenDonVi("VinGroup");
			test.setMaDonVi("vin999");
			mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8085/api/auth/22")
					.contentType("application/json")
//					.header("Authorization", "Bearer " + token)
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("Khai báo thông tin thành công!")));
		} catch (Exception e) {
			// TODO: handle exception
			// Test thành công, trả về lỗi 200: thành công
			e.getMessage();
		}
	}

}
