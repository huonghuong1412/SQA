package com.example.demo.controllerandservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.tinhthanh.Province;
import com.example.demo.repository.tinhthanh.ProvinceRepository;

@SpringBootTest()
@AutoConfigureMockMvc
public class TestTinhThanhController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProvinceRepository provinceRepository;

	@Test
	public void testGetListProvinceSuccess() throws Exception {
		// Tao moi thong tin

		Province province = new Province();
		province.setProvinceid("01TTT");
		province.setName("Thanh pho Ha Noi");

		try {
			provinceRepository.save(province);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Get thong tin
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/api/tinhthanh/city")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1)))
					// Dữ liệu trả về có số phần tử là 1
					.andExpect(jsonPath("$[0].name", is("Thanh pho Ha Noi")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// Xoá hết trong CSDL
		try {
			provinceRepository.deleteAll();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
