package com.example.demo.controllerandservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.auth.RegisterDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest()
@AutoConfigureMockMvc
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
			// TODO: handle exception
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
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO: handle exception
		}
		// Status trả về là 200: OK,
	}

	// Trong username
	@Test
	public void testRegisterFailure1() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
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
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().is4xxClientError());
		} catch (Exception e) {
		}
		// Status trả về là 401: Failure,
	}

	// Trong cccd
	@Test
	public void testRegisterFailure2() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000001");
		test.setCccd("");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
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
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().is4xxClientError());
		} catch (Exception e) {
		}
		// Status trả về là 401: Failure,
	}

	// Trong cccd & username
	@Test
	public void testRegisterFailure3() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("");
		test.setCccd("");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
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
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().is4xxClientError());
		} catch (Exception e) {
		}
		// Status trả về là 401: Failure,
	}

	// Trong ho ten
	@Test
	public void testRegisterFailure4() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000001");
		test.setCccd("000000000001");
		test.setFullName("");
		try {
			test.setPassword(encoder.encode("123456789"));
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
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().is4xxClientError());
		} catch (Exception e) {
		}
		// Status trả về là 401: Failure,
	}

	// Trong mat khau
	@Test
	public void testRegisterFailure5() throws Exception {
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
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().is4xxClientError());
		} catch (Exception e) {
		}
		// Status trả về là 401: Failure,
	}

	// Trong SDT
	@Test
	public void testRegisterFailure6() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000001");
		test.setCccd("000000000001");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
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
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().is4xxClientError());
		} catch (Exception e) {
		}
		// Status trả về là 401: Failure,
	}

	// Trong email
	@Test
	public void testRegisterFailure7() throws Exception {
		RegisterDto test = new RegisterDto();
		test.setUsername("0000000001");
		test.setCccd("000000000001");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
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
			mockMvc.perform(post("http://localhost:8085/api/auth/register", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().is4xxClientError());
		} catch (Exception e) {
		}
		// Status trả về là 401: Failure,
	}

}
