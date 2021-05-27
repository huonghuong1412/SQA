package com.example.demo.controllerandservice;

import static org.junit.Assert.assertNotNull;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest()
@AutoConfigureMockMvc
public class TestKhaiBaoController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private PasswordEncoder encoder;

	@Test
	public void testKhaiBaoSuccess() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
		}
		;
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
		}

		try {
			String token = JwtUtils.createToken(1L, "0000000001", "123456789", "test@gmail.com");
			assertNotNull(token);
			// Sau khi dang nhap thi khai bao them thong tin
			test.setCoQuanBaoHiemThanhPho("Thanh pho Ha Noi");
			test.setCoQuanBaoHiemQuanHuyen("BHXH Huyen Phu Xuyen");
			test.setSalary(20000000L);
			test.setMaSoThue("9999");
			test.setTenDonVi("VinGroup");
			test.setMaDonVi("vin999");

			mockMvc.perform(put("http://localhost:8085/api/auth/1L", 42L).contentType("application/json")
					.content(objectMapper.writeValueAsString(test))).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

		try {
			userRepos.deleteById(1L);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testKhaiBaoFailure1() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
		}
		;
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
		}

		try {
			// Sau khi dang nhap thi khai bao them thong tin
			test.setCoQuanBaoHiemThanhPho("Thanh pho Ha Noi");
			test.setCoQuanBaoHiemQuanHuyen("BHXH Huyen Phu Xuyen");
			test.setSalary(20000000L);
			test.setMaSoThue("9999");
			test.setTenDonVi("VinGroup");
			test.setMaDonVi("vin999");

			// them truong vao user co id la 1
			mockMvc.perform(MockMvcRequestBuilders.put("/api/auth/1L"))
					.andExpect(status().isUnauthorized());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		// Test thành công, trả về lỗi 401: Unauthorized
	}
	
	@Test
	public void testKhaiBaoFailure2() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
		}
		;
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
		}

		try {
			String token = JwtUtils.createToken(1L, "0000000001", "123456789", "test@gmail.com");
			assertNotNull(token);
			// Sau khi dang nhap thi khai bao them thong tin
			test.setCoQuanBaoHiemThanhPho("Thanh pho Ha Noi");
			test.setCoQuanBaoHiemQuanHuyen("BHXH Huyen Phu Xuyen");
			// không khai báo lương
			test.setSalary(null);
			test.setMaSoThue("9999");
			test.setTenDonVi("VinGroup");
			test.setMaDonVi("vin999");

			mockMvc.perform(put("http://localhost:8085/api/auth/1L", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isNotAcceptable());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

		try {
			userRepos.deleteById(1L);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testKhaiBaoFailure3() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
		}
		;
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
		}

		try {
			String token = JwtUtils.createToken(1L, "0000000001", "123456789", "test@gmail.com");
			assertNotNull(token);
			// Sau khi dang nhap thi khai bao them thong tin
			test.setCoQuanBaoHiemThanhPho("Thanh pho Ha Noi");
			test.setCoQuanBaoHiemQuanHuyen("BHXH Huyen Phu Xuyen");
			test.setSalary(20000000L);
			// không khai báo mã số thuế
			test.setMaSoThue("");
			test.setTenDonVi("VinGroup");
			test.setMaDonVi("vin999");

			mockMvc.perform(put("http://localhost:8085/api/auth/1L", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isNotAcceptable());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	
	@Test
	public void testKhaiBaoFailure4() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
		}
		;
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
		}

		try {
			String token = JwtUtils.createToken(1L, "0000000001", "123456789", "test@gmail.com");
			assertNotNull(token);
			// Sau khi dang nhap thi khai bao them thong tin
			test.setCoQuanBaoHiemThanhPho("Thanh pho Ha Noi");
			test.setCoQuanBaoHiemQuanHuyen("BHXH Huyen Phu Xuyen");
			test.setSalary(20000000L);
			test.setMaSoThue("9999");
			// không khai báo tên đơn vị làm việc
			test.setTenDonVi("");
			test.setMaDonVi("vin999");

			mockMvc.perform(put("http://localhost:8085/api/auth/1L", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isNotAcceptable());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	
	@Test
	public void testKhaiBaoFailure5() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
		}
		;
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
		}

		try {
			String token = JwtUtils.createToken(1L, "0000000001", "123456789", "test@gmail.com");
			assertNotNull(token);
			// Sau khi dang nhap thi khai bao them thong tin
			test.setCoQuanBaoHiemThanhPho("Thanh pho Ha Noi");
			test.setCoQuanBaoHiemQuanHuyen("BHXH Huyen Phu Xuyen");
			test.setSalary(20000000L);
			test.setMaSoThue("9999");
			test.setTenDonVi("VinGroup");
			// không khai báo mã đơn vị làm việc
			test.setMaDonVi("");

			mockMvc.perform(put("http://localhost:8085/api/auth/1L", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isNotAcceptable());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	
	@Test
	public void testKhaiBaoFailure6() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
		}
		;
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
		}

		try {
			String token = JwtUtils.createToken(1L, "0000000001", "123456789", "test@gmail.com");
			assertNotNull(token);
			// Sau khi dang nhap thi khai bao them thong tin
			// không khai báo cơ quan BHXH thành phố @ cơ quan BHXH Quận huyện
			test.setCoQuanBaoHiemThanhPho("");
			test.setCoQuanBaoHiemQuanHuyen("");
			test.setSalary(20000000L);
			test.setMaSoThue("9999");
			test.setTenDonVi("VinGroup");
			test.setMaDonVi("vin999");

			mockMvc.perform(put("http://localhost:8085/api/auth/1L", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isNotAcceptable());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	
	@Test
	public void testKhaiBaoFailure7() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
		}
		;
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
		}

		try {
			String token = JwtUtils.createToken(1L, "0000000001", "123456789", "test@gmail.com");
			assertNotNull(token);
			// Sau khi dang nhap thi khai bao them thong tin
			// không khai báo bảo hiểm xã hội quận huyện
			test.setCoQuanBaoHiemThanhPho("Thanh pho Ha Noi");
			test.setCoQuanBaoHiemQuanHuyen("");
			test.setSalary(20000000L);
			test.setMaSoThue("9999");
			test.setTenDonVi("VinGroup");
			test.setMaDonVi("vin999");

			mockMvc.perform(put("http://localhost:8085/api/auth/1L", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isNotAcceptable());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	
	@Test
	public void testKhaiBaoFailure8() throws Exception {
		// tạo mới thong tin tai khoan
		User test = new User();
		test.setId(1L);
		test.setUsername("0000000002");
		test.setCccd("000000000002");
		test.setFullName("Nguyen Van A");
		try {
			test.setPassword(encoder.encode("123456789"));
		} catch (Exception e) {
		}
		;
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
		}

		try {
			String token = JwtUtils.createToken(1L, "0000000001", "123456789", "test@gmail.com");
			assertNotNull(token);
			// Sau khi dang nhap thi khai bao them thong tin
			// không khai báo bảo hiểm xã hội thành phố
			test.setCoQuanBaoHiemThanhPho("");
			test.setCoQuanBaoHiemQuanHuyen("BHXH Huyen Phu Xuyen");
			test.setSalary(20000000L);
			test.setMaSoThue("9999");
			test.setTenDonVi("VinGroup");
			test.setMaDonVi("vin999");

			mockMvc.perform(put("http://localhost:8085/api/auth/1L", 42L)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(test)))
					.andExpect(status().isNotAcceptable());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}

}
