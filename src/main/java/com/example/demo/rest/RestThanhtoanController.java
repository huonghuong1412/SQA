package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ThanhtoanDto;
import com.example.demo.service.ThanhtoanService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/thanhtoan")
public class RestThanhtoanController {

	@Autowired
	private ThanhtoanService service;

	@GetMapping("/all")
	public ResponseEntity<List<ThanhtoanDto>> getAll(@RequestParam("username") String username) {
		List<ThanhtoanDto> result = service.getHistoryThanhtoan(username);
		return new ResponseEntity<List<ThanhtoanDto>>(result, HttpStatus.OK);
	}

}
