package com.example.demo.controllerandservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.common.JwtUtils;
import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest()
@AutoConfigureMockMvc
public class TestGetAuthController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private PasswordEncoder encoder;

	@Test
	public void testGetAuthSuccess() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {};
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		Address address = new Address();
		address.setCity("Ha Noi");
		address.setDistrict("Phu Xuyen");
		address.setWard("Khai Thai");
		address.setHouse("Khai Thai");
		test.setAddress(address);
		try {
			userRepos.save(test);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			String token = JwtUtils.createToken(1L, "0000000001", "123456789", "test@gmail.com");
			assertNotNull(token);
			mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/info")
					.header("Authorization", "Bearer " + token))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1)))
					// Dữ liệu trả về có số phần tử là 1
					.andExpect(jsonPath("$[0].fullName", is("Nguyen Van A")));
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

		// Xoá thong tin vua tao
		try {
			userRepos.deleteById(1L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
    public void testGetAuthFailure() throws Exception{
        //Không sử dụng token
       try {
    	   mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/info"))
           .andExpect(status().isUnauthorized());
	} catch (Exception e) {
		// TODO: handle exception
	}
        // Test thành công, trả về lỗi 401: Unauthorized
    }

}
