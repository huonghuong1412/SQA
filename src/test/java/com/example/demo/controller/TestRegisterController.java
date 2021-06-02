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
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.auth.RegisterDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest()
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestRegisterController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PasswordEncoder encoder;

	@Test
	public void testRegisterSuccess() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("Đăng ký tài khoản thành công")));
		} catch (Exception e) {
			e.getMessage();
		}
		// Status trả về là 200: OK,
	}
	
	// Username đã có trong csdl
	@Test
	public void testRegisterFailure1() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000001");
		test.setCccd("000000000003");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Mã số BHXH đã được đăng ký!")));
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	// CMND/CCCD đã có trong csdl
	@Test
	public void testRegisterFailure2() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000003");
		test.setCccd("000000000001");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("CMND/CCCD đã được đăng ký!")));
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	// email đã có trong csdl
	@Test
	public void testRegisterFailure3() throws Exception {
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
		test.setPhone("0123456789");
		test.setEmail("test@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Email đã được đăng ký!")));
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	// số điện thoại đã có trong csdl
	@Test
	public void testRegisterFailure4() throws Exception {
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
		test.setPhone("0999999999");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Số điện thoại đã được đăng ký!")));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Trong username
	@Test
	public void testRegisterFailure5() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername(null);
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Mã BHXH không được để trống")));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Trong cccd
	@Test
	public void testRegisterFailure6() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000003");
		test.setCccd(null);
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("CMND/CCCD không được để trống")));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Trong cccd & username
	@Test
	public void testRegisterFailure7() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("");
		test.setCccd("");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Mã BHXH không được để trống")))
					.andExpect(content().string(containsString("CMND/CCCD không được để trống")));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Trong ho ten
	@Test
	public void testRegisterFailure8() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000001");
		test.setCccd("000000000001");
		test.setFullName("");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Họ tên không được để trống")));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Trong mat khau
	@Test
	public void testRegisterFailure9() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000001");
		test.setCccd("000000000001");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode(""));
		} catch (Exception e) {
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Mật khẩu không được để trống")));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Trong SDT
	@Test
	public void testRegisterFailure10() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000001");
		test.setCccd("000000000001");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("");
		test.setEmail("abc@gmail.com");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Số điện thoại liên hệ không được để trống")));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Trong email
	@Test
	public void testRegisterFailure12() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000001");
		test.setCccd("000000000001");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
			e.getMessage();
		}
		test.setDateOfBirth("1999-12-14");
		test.setPhone("0123456789");
		test.setEmail("");
		test.setTypeUser(1);
		test.setCity("Ha Noi");
		test.setDistrict("Phu Xuyen");
		test.setWard("Khai Thai");
		test.setHouse("Khai Thai");
		try {
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Email liên hệ không được để trống")));
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	// Trong thanh pho
		@Test
		public void testRegisterFailure13() throws Exception {
			RegisterDto test = new RegisterDto();
			test.setUsername("0000000001");
			test.setCccd("000000000001");
			test.setFullName("Nguyen Van A");
			try {
				test.setPassword(encoder.encode("123456789"));
			} catch (Exception e) {
				e.getMessage();
			}
			test.setDateOfBirth("1999-12-14");
			test.setPhone("0123456789");
			test.setEmail("abc@gmail.com");
			test.setTypeUser(1);
			test.setCity("");
			test.setDistrict("Phu Xuyen");
			test.setWard("Khai Thai");
			test.setHouse("Khai Thai");
			try {
				mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(test)))
						.andExpect(status().isBadRequest())
						.andExpect(content().string(containsString("Thành phố liên hệ không được để trống")));
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
		// Trong quan huyen
		@Test
		public void testRegisterFailure14() throws Exception {
			RegisterDto test = new RegisterDto();
			test.setUsername("0000000001");
			test.setCccd("000000000001");
			test.setFullName("Nguyen Van A");
			try {
				test.setPassword(encoder.encode("123456789"));
			} catch (Exception e) {
				e.getMessage();
			}
			test.setDateOfBirth("1999-12-14");
			test.setPhone("0123456789");
			test.setEmail("abc@gmail.com");
			test.setTypeUser(1);
			test.setCity("Ha Noi");
			test.setDistrict("");
			test.setWard("Khai Thai");
			test.setHouse("Khai Thai");
			try {
				mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(test)))
						.andExpect(status().isBadRequest())
						.andExpect(content().string(containsString("Quận huyện liên hệ không được để trống")));
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
		// Trong xa phuong
		@Test
		public void testRegisterFailure15() throws Exception {
			RegisterDto test = new RegisterDto();
			test.setUsername("0000000001");
			test.setCccd("000000000001");
			test.setFullName("Nguyen Van A");
			try {
				test.setPassword(encoder.encode("123456789"));
			} catch (Exception e) {
				e.getMessage();
			}
			test.setDateOfBirth("1999-12-14");
			test.setPhone("0123456789");
			test.setEmail("abc@gmail.com");
			test.setTypeUser(1);
			test.setCity("Ha Noi");
			test.setDistrict("Phu Xuyen");
			test.setWard("");
			test.setHouse("Khai Thai");
			try {
				mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(test)))
						.andExpect(status().isBadRequest())
						.andExpect(content().string(containsString("Xã phường liên hệ không được để trống")));
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
		// Trong dia chi nha
		@Test
		public void testRegisterFailure16() throws Exception {
			RegisterDto test = new RegisterDto();
			test.setUsername("0000000001");
			test.setCccd("000000000001");
			test.setFullName("Nguyen Van A");
			try {
				test.setPassword(encoder.encode("123456789"));
			} catch (Exception e) {
				e.getMessage();
			}
			test.setDateOfBirth("1999-12-14");
			test.setPhone("0123456789");
			test.setEmail("abc@gmail.com");
			test.setTypeUser(1);
			test.setCity("Ha Noi");
			test.setDistrict("Phu Xuyen");
			test.setWard("Khai Thai");
			test.setHouse("");
			try {
				mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L)
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(test)))
						.andExpect(status().isBadRequest())
						.andExpect(content().string(containsString("Số nhà liên hệ không được để trống")));
			} catch (Exception e) {
				e.getMessage();
			}
		}

}
